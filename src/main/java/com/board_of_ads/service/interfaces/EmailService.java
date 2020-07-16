package com.board_of_ads.service.interfaces;

import com.board_of_ads.models.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public interface EmailService {
    void sendMail(String subject, String body, User user);

    void sendHTMLEmail(String subject, String bodyHTML, User user) throws MessagingException;
}
