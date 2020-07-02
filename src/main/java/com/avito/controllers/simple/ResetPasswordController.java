package com.avito.controllers.simple;
import com.avito.models.reset_password.GenericResponse;

import com.avito.models.User;
import com.avito.service.interfaces.EmailService;
import com.avito.service.interfaces.PasswordResetService;
import com.avito.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
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
@RequestMapping("/user")
public class ResetPasswordController {

    private final UserService userService;
    private final MessageSource messages;
    private final PasswordResetService passwordResetService;


    @GetMapping("/changePassword")
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

    @RequestMapping(value = "/savePassword", method = RequestMethod.POST)
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
