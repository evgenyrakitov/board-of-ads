/*package com.avito.controllers.rest;

import com.avito.models.User;
import com.avito.service.interfaces.EmailService;
import com.avito.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/rest/resetPassword")
public class ResetPasswordRestController {

    private final UserService userService;
    private final MessageSource messages;
    private final EmailService emailService;
    private final Environment env;

    @PostMapping
    public ResponseEntity<Boolean> resetPassword(@RequestParam("email") String userEmail,
                                                 Locale locale){
        User user = userService.findUserByEmail(userEmail);
        boolean boll = (user != null);
        if (boll) {
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
            String subject = messages.getMessage("message.resetPassword", null, locale);
            String body = messages.getMessage("message.resetPassword", null, locale)+
                    " \r\n http://"+
                    Objects.requireNonNull(env.getProperty("server.domain"))+":"+
                    Objects.requireNonNull(env.getProperty("server.port")) +
                    "/reset/changePassword?token=" +
                    token;
            emailService .sendMail(subject, body, user);
        }
            return new ResponseEntity<>(boll, HttpStatus.OK);
    }
}*/
