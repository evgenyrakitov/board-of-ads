package com.avito.service.interfaces;

import com.avito.models.User;

public interface EmailService {
    void sendMail(String subject, String body, User user);
}
