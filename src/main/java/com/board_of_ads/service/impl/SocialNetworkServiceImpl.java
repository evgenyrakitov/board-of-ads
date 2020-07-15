package com.board_of_ads.service.impl;

import com.board_of_ads.models.Role;
import com.board_of_ads.models.User;
import com.board_of_ads.service.interfaces.RoleService;
import com.board_of_ads.service.interfaces.SocialNetworkService;
import com.board_of_ads.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
@AllArgsConstructor
public class SocialNetworkServiceImpl implements SocialNetworkService {

    private final UserService userService;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User loadUserInSocialNetwork(Map<String, Object> map) {

        User user = null;
        if (map.containsKey("sub")) { //sub есть у google
            String googleUsername = (String) map.get("email");
            user = userService.findUserByEmail(googleUsername);
            if (user == null) {
                user = new User();
                Role role = roleService.findRoleByName("USER");
               /* if (role == null) { DataInitializer исключает это
                    role = new Role(1L, "USER", new HashSet<>());
                }*/
                user.setRoles(Collections.singleton(role));
            }
            user.setEmail((String) map.get("email"));
            user.setFirstName((String) map.get("name"));
            user.setUserIcons((String) map.get("picture"));
            user.setPassword(passwordEncoder.encode("oauth2user"));

            return userService.addUser(user);
        } else { //у вк sub нет
            //здесь должна быть логика, связанная с вк
            return user;
        }
    }
}
