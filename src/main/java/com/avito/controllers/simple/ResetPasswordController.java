package com.avito.controllers.simple;
import com.avito.models.reset_password.GenericResponse;

import com.avito.models.User;
import com.avito.models.reset_password.PasswordResetToken;
import com.avito.service.EmailService;
import com.avito.service.interfaces.PasswordResetService;
import com.avito.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.UUID;

@AllArgsConstructor
@Controller
public class ResetPasswordController {

    private final UserService userService;
    private final MessageSource messages;
    private final EmailService emailService;
    private final PasswordResetService passwordResetService;


   /* @PostMapping("/user/resetPassword")
    @ResponseBody
    public GenericResponse resetPassword(final HttpServletRequest request, @RequestParam("email") final String userEmail) {
        final User user = userService.findUserByEmail(userEmail);
        if (user != null) {
            final String token = UUID.randomUUID().toString();
            passwordResetService.createPasswordResetTokenForUser(user, token);
            String subject = "Reset Password";
            String body = getBody(getAppUrl(request), request.getLocale(), token);
            emailService .sendMail(subject, body, user);

            return new GenericResponse(messages.getMessage("message.resetPasswordEmail", null, request.getLocale()));
        } else {
            return new GenericResponse(messages.getMessage("message.resetPasswordNotEmail", new Object[]{"Paul Smith"}, request.getLocale()));
        }

    }*/
   @PostMapping("/user/resetPassword")
   @ResponseBody
   public ResponseEntity<User> resetPassword(final HttpServletRequest request, @RequestParam("email") final String userEmail) {
       final User user = userService.findUserByEmail(userEmail);
       if (user != null) {
           final String token = UUID.randomUUID().toString();
           passwordResetService.createPasswordResetTokenForUser(user, token);
           String subject = "Reset Password";
           String body = getBody(getAppUrl(request), request.getLocale(), token);
           emailService .sendMail(subject, body, user);
       }
       return new ResponseEntity<>(user, HttpStatus.OK);
   }

    private String getBody(final String contextPath, final Locale locale, final String token){
        final String url = contextPath + "/user/changePassword?token=" + token;
        final String message = messages.getMessage("message.resetPassword", null, locale);
        return message + " \r\n" + url;
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }



    @GetMapping("/user/changePassword")
    public String showChangePasswordPage(Locale locale, Model model,
            /*@RequestParam("id") long id, */@RequestParam("token") String token) {
        String result = passwordResetService.validatePasswordResetToken(token);
        if (result != null) {
            model.addAttribute("message",
                    messages.getMessage("auth.message." + result, null, locale));
            return "redirect:/login?lang=" + locale.getLanguage();
        }
        model.addAttribute("token", token);
        return "updatePassword";

    }

    @RequestMapping(value = "/user/savePassword", method = RequestMethod.POST)
    public String savePassword(@RequestParam("password") String password,
                               @RequestParam("passwordConfirm") String passwordConfirm,
                               @RequestParam("token") String token) {
        User user = passwordResetService.findByToken(token).getUser();
        user.setPassword(password);
        user.setPasswordConfirm(passwordConfirm);
        userService.updateUser(user);
        return "redirect:/";
    }
}
