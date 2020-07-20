package com.board_of_ads.service.impl;
import com.board_of_ads.models.User;
import com.board_of_ads.service.interfaces.EmailService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
@Data
@Service
@AllArgsConstructor
public class EmailServiceIml implements EmailService {

    private final JavaMailSender mailSender;
    private final Environment env;


    /*Метод для отправки почты
    String subject - Название сообщения
    String body - Тело сообщения
     */
    //отправка простого письма
    @Override
    public void sendMail(String subject, String body, User user){
        mailSender.send(constructEmail(subject, body, user));
    }
    // отправка письма с использованием HTML
    @Override
    public void sendHTMLEmail(String subject, String bodyHTML, User user) throws MessagingException {
        mailSender.send(constructMimeMessage(subject, bodyHTML, user));
    }

    private SimpleMailMessage constructEmail(String subject, String body, User user) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom(Objects.requireNonNull(env.getProperty("support.email")));
        return email;
    }

    private MimeMessage constructMimeMessage(String subject, String bodyHTML, User user) throws MessagingException {
        final MimeMessage mimeMessage = mailSender.createMimeMessage();
        mimeMessage.setFrom(Objects.requireNonNull(env.getProperty("support.email")));
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
        mimeMessage.setSubject(subject);
        mimeMessage.setContent(bodyHTML, "text/html");
        return mimeMessage;
    }



}
