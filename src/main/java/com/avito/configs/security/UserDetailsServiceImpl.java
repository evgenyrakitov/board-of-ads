package com.avito.configs.security;

import com.avito.models.User;
import com.avito.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = userService.findUserByLogin(login);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
