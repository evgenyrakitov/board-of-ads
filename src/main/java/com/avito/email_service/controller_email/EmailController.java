package com.avito.email_service.controller_email;

import com.avito.email_service.service_email.IUserService;
import com.avito.email_service.securyti_email.UserSecurityService;
import com.avito.email_service.model_email.GenericResponse;
import com.avito.models.User;
import com.avito.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@AllArgsConstructor
@Controller
public class EmailController {

    private final UserService userService;
    private final IUserService iUserService;
    private final JavaMailSender mailSender;
    private final MessageSource messages;
    private final Environment env;
    private final UserSecurityService userSecurityService;

    @PostMapping("/user/resetPassword")
    @ResponseBody
    public GenericResponse resetPassword(final HttpServletRequest request, @RequestParam("email") final String userEmail) {
        final User user = userService.findUserByLogin(userEmail);
        if (user != null) {
            final String token = UUID.randomUUID().toString();
            iUserService.createPasswordResetTokenForUser(user, token);
            mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, user));

        }
        return new GenericResponse(messages.getMessage("message.resetPasswordEmail", null, request.getLocale()));
    }
    private SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final User user) {
        final String url = contextPath + "/user/changePassword?token=" + token;
        final String message = messages.getMessage("message.resetPassword", null, locale);
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    private SimpleMailMessage constructEmail(String subject, String body, User user) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

    @GetMapping("/user/changePassword")
    public String showChangePasswordPage(Locale locale, Model model,
                                         /*@RequestParam("id") long id, */@RequestParam("token") String token) {
        String result = userSecurityService.validatePasswordResetToken(token);
        if (result != null) {
            model.addAttribute("message",
                    messages.getMessage("auth.message." + result, null, locale));
            return "redirect:/login?lang=" + locale.getLanguage();
        }
        model.addAttribute("token", token);
        return "updatePassword";

    }

    @RequestMapping(value = "/user/savePassword", method = RequestMethod.POST)
    //@ResponseBody
    public String savePassword(@RequestParam("password") String password,
                               @RequestParam("passwordConfirm") String passwordConfirm,
                               @RequestParam("token") String token) {
        User user = iUserService.findByToken(token).getUser();
        user.setPassword(password);
        user.setPasswordConfirm(passwordConfirm);
        userService.updateUser(user);
     return "redirect:/";
    }
    /*@GetMapping("/updatePassword")
    public String mainPage(Model model, @RequestParam("locale") Locale locale){

        return "updatePassword";
    }*/
}
