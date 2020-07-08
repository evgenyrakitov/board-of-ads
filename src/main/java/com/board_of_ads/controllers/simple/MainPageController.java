package com.board_of_ads.controllers.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    private static final Logger logger = LoggerFactory.getLogger(MainPageController.class);

    @GetMapping("/")
    public String getMainPage() {
        return "main-page";
    }
}
