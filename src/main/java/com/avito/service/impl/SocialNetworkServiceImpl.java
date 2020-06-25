package com.avito.service.impl;

import com.avito.models.Role;
import com.avito.models.User;
import com.avito.service.interfaces.RoleService;
import com.avito.service.interfaces.SocialNetworkService;
import com.avito.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

@Service
public class SocialNetworkServiceImpl implements SocialNetworkService {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User loadUserInSocialNetwork(Map<String, Object> map) {

        User user = null;
        if (map.containsKey("sub")) { //sub есть у google
            String googleUsername = (String) map.get("email");
            user = userService.findUserByLogin(googleUsername);
            if (user == null) {
                user = new User();
                Role role = roleService.findRoleByName("USER");
               /* if (role == null) { DataInitializer исключает это
                    role = new Role(1L, "USER", new HashSet<>());
                }*/
                user.setRoles(Collections.singleton(role));
            }
            user.setLogin((String) map.get("email"));
            user.setPublicName((String) map.get("name"));
            user.setUserIcons((String) map.get("picture"));
            user.setPassword(passwordEncoder.encode("oauth2user"));

            return userService.addUser(user);
        }else { //у вк sub нет
            //здесь должна быть логика, связанная с вк
            return user;
        }
    }
}
