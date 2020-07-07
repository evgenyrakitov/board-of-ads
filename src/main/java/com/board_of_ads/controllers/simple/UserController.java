package com.board_of_ads.controllers.simple;

import com.board_of_ads.models.User;
import com.board_of_ads.service.interfaces.UserService;
import java.util.Locale;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final MessageSource messages;

    @GetMapping("/admin_page")
    public String getAdminPage(ModelMap modelMap) {
        return "admin-page";
    }

    @GetMapping("/profile")
    public String userProfile(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        modelMap.addAttribute("currentUser", authentication.getPrincipal());
        return "user-profile";
    }

    @GetMapping("/reset/changePassword")
    public String showChangePasswordPage(Locale locale, Model model,
            @RequestParam("token") String token) {
        String result = userService.validatePasswordResetToken(token);
        if (result != null) {
            model.addAttribute("message",
                    messages.getMessage("auth.message." + result, null, locale));
            return "redirect:/login?lang=" + locale.getLanguage();
        }
        model.addAttribute("token", token);
        return "updatePassword";
    }

    @RequestMapping(value = "/reset/savePassword", method = RequestMethod.POST)
    public String savePassword(@RequestParam("password") String password,
            @RequestParam("passwordConfirm") String passwordConfirm,
            @RequestParam("token") String token) {
        User user = userService.findByToken(token).getUser();
        user.setPassword(password);
        user.setPasswordConfirm(passwordConfirm);
        userService.updateUser(user);
        return "redirect:/";
    }
}