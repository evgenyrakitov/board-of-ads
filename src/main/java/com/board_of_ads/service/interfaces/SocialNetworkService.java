package com.board_of_ads.service.interfaces;

import com.board_of_ads.models.User;

import java.util.Map;

public interface SocialNetworkService {

    User findOrCreateUserGoogle(Map<String, Object> map);

    User findOrCreateUserVK(String token, String vkUserId, String email);

    void doAutoLogin(User user);
}
