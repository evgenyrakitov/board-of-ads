package com.board_of_ads.controllers.auth;

import com.board_of_ads.service.interfaces.RoleService;
import com.board_of_ads.service.interfaces.SocialNetworkService;
import com.board_of_ads.service.interfaces.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Контроллер для авторизации в вконтакте.
 * Информация взята от сюда https://vk.com/dev/auth_sites
 */
@Controller
@RequestMapping("/login/vk")
public class VkAuthController {
    private static final Logger logger = LoggerFactory.getLogger(VkAuthController.class);
    private final UserService userService;
    private final RoleService roleService;
    private final SocialNetworkService socialNetworkService;

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

    public VkAuthController(UserService userService, RoleService roleService, SocialNetworkService socialNetworkService) {
        this.userService = userService;
        this.roleService = roleService;
        this.socialNetworkService = socialNetworkService;
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

            socialNetworkService.doAutoLogin(socialNetworkService.findOrCreateUserVK(token, vkUserId, email));
        }

        return "redirect:/";
    }

    /**
     * Получает данные о пользователе от АПИ ВК.
     * Первым запросом достаёт данные, вторым аватар.
     *
     * @param token    - Токен для работы с ВК
     * @param vkUserId - Айди пользователя ВК
     * @return - map с данными пользователя
     */
    @SneakyThrows
    public Map<String, String> getVKUserInfo(String token, String vkUserId) {
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
}
