package com.board_of_ads.controllers.auth;

import com.board_of_ads.models.Role;
import com.board_of_ads.models.User;
import com.board_of_ads.service.interfaces.RoleService;
import com.board_of_ads.service.interfaces.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Контроллер для авторизации в вконтакте.
 * Информация взята от сюда https://vk.com/dev/auth_sites
 */
@Controller
@RequestMapping("/login/vk")
public class vkAuthController {
    private static final Logger logger = LoggerFactory.getLogger(vkAuthController.class);
    private final UserService userService;
    private final RoleService roleService;

    @Value("${vk.clientId}")
    private String clientId;
    @Value("${vk.clientSecret}")
    private String clientSecret;
    @Value("${vk.userAuthorizationUri}")
    private String userAuthorizationUri;
    @Value("${vk.accessTokenUri}")
    private String accessTokenUri;
    @Value("${vk.scope}")
    private String scope;
    @Value("${vk.userInfoUri}")
    private String userInfoUri;
    @Value("${vk.photoInfoUri}")
    private String photoInfoUri;
    @Value("${vk.photoSizeVK}")
    private String photoSizeVK;
    @Value("${vk.apiVersion}")
    private String apiVersion;

    public vkAuthController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    /**
     * Начальная страница, с которой начинается авторизация.
     * Подтягиваются данные для ВК и пользователь отправляется на сервер ВК для авторизации.
     */
    @GetMapping("/auth")
    public String authPage(HttpServletRequest request) {
        StringBuilder redirectURL = new StringBuilder(userAuthorizationUri).append("?")
                .append("client_id=").append(clientId).append("&")
                .append("scope=").append(scope).append("&")
                .append("redirect_uri=http://").append(request.getServerName()).append(":").append(request.getServerPort()).append("/login/vk").append("&")
                .append("response_type=code").append("&")
                .append("v=").append(apiVersion);

        return "redirect:" + redirectURL.toString();
    }

    /**
     * Посадочная страница куда сервер ВК перенаправляет пользователя с кодом для получения токена.
     * Получаем тут токен и емейл, проверям есть ли уже такой бользователь в бд, если нет, то создаем.
     * Далее авторизуем данного пользователя в секьюрити и отправляем на главную страницу.
     *
     * @param code - Код для получения токена, его даёт сервер ВК.
     */
    @SneakyThrows
    @GetMapping("")
    public String landingPage(HttpServletRequest request, @RequestParam String code) {

        StringBuilder getTokenURI = new StringBuilder(accessTokenUri).append("?")
                .append("client_id=").append(clientId).append("&")
                .append("client_secret=").append(clientSecret).append("&")
                .append("code=").append(code).append("&")
                .append("redirect_uri=http://").append(request.getServerName()).append(":").append(request.getServerPort()).append("/login/vk");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(getTokenURI.toString(), String.class);

        if (response.getStatusCode().equals(HttpStatus.OK)) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());

            String token = root.path("access_token").textValue();
            String vkUserId = String.valueOf(root.path("user_id").intValue());
            String email = root.path("email").textValue();

            doAutoLogin(findOrCreateUser(token, vkUserId, email));
        }

        return "redirect:/";
    }

    /**
     * Метод проверяет есть в базе пользователя с таким емейлом.
     * Если нет, то обращется к АПИ ВК, получает данные о пользователе и создает его.
     *
     * @param token    - Токен для работы с ВК
     * @param vkUserId - Айди пользователя ВК
     * @param email    - Емейл пользователя ВК
     * @return - созданный или найденный по емейлу пользователь
     */
    private User findOrCreateUser(String token, String vkUserId, String email) {
        User user = userService.findUserByEmail(email);
        if (user == null) {
            user = createUserByEmailAndVKUserInfo(token, vkUserId, email);
        }
        return user;
    }

    /**
     * Создает и возвращает пользователя с данными полученными с серверера ВК.
     * А именно: емейл, имя и фамилия. И ролью простого пользователя
     *
     * @param token    - Токен для работы с ВК
     * @param vkUserId - Айди пользователя ВК
     * @param email    - Емейл пользователя ВК
     * @return - созданных пользователь.
     */
    @SneakyThrows
    private User createUserByEmailAndVKUserInfo(String token, String vkUserId, String email) {

        Map<String, String> usersInfo = getVKUserInfo(token, vkUserId);
        String firstName = usersInfo.get("firstName");
        String lastName = usersInfo.get("lastName");
        String userPhoto = usersInfo.get("userPhoto");

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleService.findRoleByName("USER"));
        User user = new User(email, firstName, lastName, "SuperStrongUnDecryptablePassword!!! x_X", "SuperStrongUnDecryptablePassword!!! x_X", "", userRoles, "");
        user.setUserIcons(userPhoto);

        user = userService.addUser(user);
        return user;
    }

    /**
     * Получает данные о пользовале от АПИ ВК.
     *
     * @param token    - Токен для работы с ВК
     * @param vkUserId - Айди пользователя ВК
     * @return - map с данными пользователя
     */
    @SneakyThrows
    private Map<String, String> getVKUserInfo(String token, String vkUserId) {
        Map<String, String> map = new HashMap<>();

        StringBuilder getUserInfoURI = new StringBuilder(userInfoUri).append("?")
                .append("user_ids=").append(vkUserId).append("&")
                .append("access_token=").append(token).append("&")
                .append("v=").append(apiVersion);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(getUserInfoURI.toString(), String.class);

        if (response.getStatusCode().equals(HttpStatus.OK)) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode resp = root.path("response");

            map.put("firstName", resp.get(0).get("first_name").textValue());
            map.put("lastName", resp.get(0).get("last_name").textValue());
        }

        //  Апи ВК разрешает делать только 3 запроса в секунду для небольших приложений.
        Thread.sleep(333);

        StringBuilder getUserPhotoURI = new StringBuilder(photoInfoUri).append("?")
                .append("access_token=").append(token).append("&")
                .append("v=").append(apiVersion).append("&")
                .append("owner_id=").append(vkUserId).append("&")
                .append("album_id=profile");

        response = restTemplate.getForEntity(getUserPhotoURI.toString(), String.class);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode resp = root.path("response");

            int ItemCount = resp.get("count").intValue();
            if (ItemCount >= 1) {
                for (JsonNode node : resp.get("items").get(ItemCount - 1).get("sizes")) {
                    if (node.get("type").textValue().equals(photoSizeVK)) {
                        map.put("userPhoto", node.get("url").textValue());
                    }
                }
            }
        }

        return map;
    }

    /**
     * Метод который осуществляет авто-вход для указанного пользователя.
     *
     * @param user - пользователя которого надо залогинить.
     */
    private void doAutoLogin(User user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
