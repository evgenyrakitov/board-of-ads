package com.board_of_ads.service.impl;

import com.board_of_ads.controllers.auth.VkAuthController;
import com.board_of_ads.models.Role;
import com.board_of_ads.models.User;
import com.board_of_ads.service.interfaces.RoleService;
import com.board_of_ads.service.interfaces.SocialNetworkService;
import com.board_of_ads.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Сервис для авторизации через Google и В контакте.
 * oAuth авторизация Google настроена в Security,
 * oAuth авторизация В контакте реализована
 * в REST контроллере {@link com.board_of_ads.controllers.auth.VkAuthController}.
 */

@Service
@AllArgsConstructor
public class OAuthNetworkServiceImpl implements SocialNetworkService {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationContext ctx;


    /**
     * Метод проверяет есть в базе пользователя с таким емейлом.
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
     * Метод проверяет есть в базе пользователя с таким емейлом.
     * Если нет, то обращется к АПИ ВК, получает данные о пользователе и создает его.
     *
     * @param token    - Токен для работы с ВК
     * @param vkUserId - Айди пользователя ВК
     * @param email    - Емейл пользователя ВК
     * @return - созданный или найденный по e-mail пользователь
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
     * Создает и возвращает пользователя с данными полученными с серверера ВК.
     * А именно: емейл, имя и фамилия. И ролью простого пользователя
     *
     * @param token    - Токен для работы с ВК
     * @param vkUserId - Айди пользователя ВК
     * @param email    - Емейл пользователя ВК
     * @return - созданных пользователь.
     */
    private User createUserByEmailAndVKUserInfo(String token, String vkUserId, String email) {
        //Обращение к методу контроллера, для получения данных пользователя, так как он не был найден в базе по e-mail.
        Map<String, String> usersInfo = ctx.getBean(VkAuthController.class).getVKUserInfo(token, vkUserId);
        String firstName = usersInfo.get("firstName");
        String lastName = usersInfo.get("lastName");
        String userPhoto = usersInfo.get("userPhoto");

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleService.findRoleByName("USER"));
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword("SuperStrongUnDecryptablePassword!!! x_X");
        user.setRoles(userRoles);
        user.setUserIcons(userPhoto);

        user = userService.addUser(user);
        return user;
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
