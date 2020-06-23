package com.avito.controllers.simple;

import com.avito.configs.security.AuthProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    private static Logger logger = LoggerFactory.getLogger(MainPageController.class);

    @GetMapping("/main_page")
    String getMainPage() {
        return "index";
    }

    @GetMapping("/adDetails")
    String getDetailsPage() {
        return "adDetails";
    }

}
