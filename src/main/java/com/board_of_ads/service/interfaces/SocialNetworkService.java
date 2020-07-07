package com.board_of_ads.service.interfaces;

import com.board_of_ads.models.User;

import java.util.Map;

public interface SocialNetworkService {

    User loadUserInSocialNetwork(Map<String, Object> map);
}
