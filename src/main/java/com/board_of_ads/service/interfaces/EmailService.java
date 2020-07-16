package com.board_of_ads.service.interfaces;

import com.board_of_ads.models.User;

public interface EmailService {
    void sendMail(String subject, String body, User user);
}
