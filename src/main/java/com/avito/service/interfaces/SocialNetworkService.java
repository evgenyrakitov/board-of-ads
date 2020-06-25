package com.avito.service.interfaces;

import com.avito.models.User;

import java.util.Map;

public interface SocialNetworkService {

    User loadUserInSocialNetwork(Map<String, Object> map);
}
