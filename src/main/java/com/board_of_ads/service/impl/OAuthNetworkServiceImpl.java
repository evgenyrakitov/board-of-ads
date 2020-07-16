package com.board_of_ads.service.impl;

import com.board_of_ads.models.Role;
import com.board_of_ads.models.User;
import com.board_of_ads.service.interfaces.RoleService;
import com.board_of_ads.service.interfaces.OAuthNetworkService;
import com.board_of_ads.service.interfaces.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Сервис для авторизации через Google и В Контакте.
 * oAuth авторизация Google настроена в Security,
 * oAuth авторизация В контакте реализована
 * в REST контроллере {@link com.board_of_ads.controllers.auth.VKoAuthController}.
 */

@Service
@AllArgsConstructor
public class OAuthNetworkServiceImpl implements OAuthNetworkService {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final Environment env;

    /**
     * Метод проверяет есть в базе пользователя с таким e-mail.
     * Если нет, то создаём его используя полученные от Google данные.
     *
     * @param map входные данные от Google.
     * @return готовый User для авторизации.
     */
    @Override
    public User findOrCreateUserGoogle(Map<String, Object> map) {
        String googleUsername = (String) map.get("email");
        User user = userService.findUserByEmail(googleUsername);
        if (user == null) {
            user = new User();
            Role role = roleService.findRoleByName("USER");
            user.setRoles(Collections.singleton(role));
            user.setEmail((String) map.get("email"));
            user.setFirstName((String) map.get("given_name"));
            user.setLastName((String) map.get("family_name"));
            user.setUserIcons((String) map.get("picture"));
            user.setPassword(passwordEncoder.encode("oauth2user"));
        }

        return userService.addUser(user);
    }


    /**
     * Метод проверяет есть в базе пользователя с таким e-mail.
     * Если нет, то обращается к API ВК, получает данные о пользователе и создает его.
     *
     * @param token    - Токен для работы с ВК.
     * @param vkUserId - ID пользователя ВК.
     * @param email    - E-mail пользователя ВК.
     * @return - созданный или найденный по e-mail пользователь.
     */
    @Override
    public User findOrCreateUserVK(String token, String vkUserId, String email) {
        User user = userService.findUserByEmail(email);
        if (user == null) {
            user = createUserByEmailAndVKUserInfo(token, vkUserId, email);
        }
        return user;
    }

    /**
     * Создает и возвращает пользователя с данными полученными с сервера ВК.
     * А именно: e-mail, имя и фамилия. И ролью простого пользователя.
     *
     * @param token    - Токен для работы с ВК.
     * @param vkUserId - ID пользователя ВК.
     * @param email    - У-ьфшд пользователя ВК.
     * @return - созданных пользователь.
     */
    private User createUserByEmailAndVKUserInfo(String token, String vkUserId, String email) {
        //Обращение к методу контроллера, для получения данных пользователя, так как он не был найден в базе по e-mail.
        Map<String, String> usersInfo = getVKUserInfo(token, vkUserId);
        String firstName = usersInfo.get("firstName");
        String lastName = usersInfo.get("lastName");
        String userPhoto = usersInfo.get("userPhoto");

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleService.findRoleByName("USER"));
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword("SuperStrongUnDecryptablePassword!!! x_X");
        user.setRoles(userRoles);
        user.setUserIcons(userPhoto);

        user = userService.addUser(user);
        return user;
    }

    /**
     * Получает данные о пользователе от АПИ ВК.
     * Первым запросом достаёт данные, вторым аватар.
     *
     * @param token    - Токен для работы с ВК.
     * @param vkUserId - ID пользователя ВК.
     * @return - map с данными пользователя.
     */
    @SneakyThrows
    private Map<String, String> getVKUserInfo(String token, String vkUserId) {
        Map<String, String> map = new HashMap<>();

        StringBuilder getUserInfoURI = new StringBuilder(env.getProperty("vk.userInfoUri")).append("?")
                .append("user_ids=").append(vkUserId).append("&")
                .append("access_token=").append(token).append("&")
                .append("v=").append(env.getProperty("vk.apiVersion"));

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

        StringBuilder getUserPhotoURI = new StringBuilder(env.getProperty("vk.photoInfoUri")).append("?")
                .append("access_token=").append(token).append("&")
                .append("v=").append(env.getProperty("vk.apiVersion")).append("&")
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
                    if (node.get("type").textValue().equals(env.getProperty("vk.photoSizeVK"))) {
                        map.put("userPhoto", node.get("url").textValue());
                    }
                }
            }
        }

        return map;
    }

    /**
     * Метод для ручного добавления User в Security.
     */
    @Override
    public void doAutoLogin(User user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
