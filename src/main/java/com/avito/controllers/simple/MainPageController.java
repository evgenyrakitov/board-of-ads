package com.avito.controllers.simple;

import com.avito.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    private static final Logger logger = LoggerFactory.getLogger(MainPageController.class);

    @GetMapping("/")
    public String getMainPage(ModelMap modelMap) {
        modelMap.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "main-page";
    }

    @GetMapping("/posting")
    public String getDetailsPage() {
        return "posting";
    }

}
