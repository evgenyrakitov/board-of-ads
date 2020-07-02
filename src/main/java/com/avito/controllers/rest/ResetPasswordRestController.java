package com.avito.controllers.rest;

import com.avito.models.User;
import com.avito.service.interfaces.EmailService;
import com.avito.service.interfaces.PasswordResetService;
import com.avito.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class ResetPasswordRestController {

    private final UserService userService;
    private final MessageSource messages;
    private final EmailService emailService;
    private final PasswordResetService passwordResetService;

    @PostMapping("/user/resetPassword")
    public ResponseEntity<Boolean> resetPassword(final HttpServletRequest request, @RequestParam("email") final String userEmail) {
        final User user = userService.findUserByEmail(userEmail);
        boolean boll = (user != null);
        if (boll) {
            final String token = UUID.randomUUID().toString();
            passwordResetService.createPasswordResetTokenForUser(user, token);
            String subject = "Reset Password";
            String body = getBody(getAppUrl(request), request.getLocale(), token);
            emailService .sendMail(subject, body, user);

            return new ResponseEntity<>(boll, HttpStatus.OK);
        }else {

            return new ResponseEntity<>(boll, HttpStatus.OK);
        }

    }
    private String getBody(final String contextPath, final Locale locale, final String token){
        final String url = contextPath + "/user/changePassword?token=" + token;
        final String message = messages.getMessage("message.resetPassword", null, locale);
        return message + " \r\n" + url;
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
