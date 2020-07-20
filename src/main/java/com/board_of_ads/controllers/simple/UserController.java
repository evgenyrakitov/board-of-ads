package com.board_of_ads.controllers.simple;

import com.board_of_ads.models.User;
import com.board_of_ads.models.kladr.Region;
import com.board_of_ads.service.interfaces.RegionService;
import com.board_of_ads.service.interfaces.UserService;

import java.util.List;
import java.util.Locale;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final MessageSource messages;
    private final RegionService regionService;

    @GetMapping("/admin_page")
    public String getAdminPage(ModelMap model, @ModelAttribute("passwordToken") String token, @ModelAttribute("passwordResetErrorMessage") String errorMessage) {
        List<Region> regions = regionService.getAllRegions();
        model.addAttribute("regions", regions);
        model.addAttribute("passwordToken", token);
        model.addAttribute("passwordResetErrorMessage", errorMessage);
        return "admin-page";
    }

    @GetMapping("/profile")
    public String userProfile() {
        return "user-profile";
    }

    @GetMapping("/reset/changePassword")
    public String showChangePasswordPage(
            Locale locale, RedirectAttributes attributes, @RequestParam("token") String token) {
        String result = userService.validatePasswordResetToken(token);
        if (result == null) {
            attributes.addFlashAttribute("passwordToken", token);
        } else {
            attributes.addFlashAttribute(
                    "passwordResetErrorMessage", messages.getMessage("auth.message." + result, null, locale));
        }
        return "redirect:/?locale=" + locale.getLanguage();
    }

    @RequestMapping(value = "/reset/savePassword", method = RequestMethod.POST)
    public String savePassword(
            @RequestParam("password") String password,
            @RequestParam("passwordConfirm") String passwordConfirm,
            @RequestParam("token") String token) {
        User user = userService.findByToken(token).getUser();
        user.setPassword(password);
        user.setPasswordConfirm(passwordConfirm);
        userService.updateUser(user);
        return "redirect:/";
    }
}
