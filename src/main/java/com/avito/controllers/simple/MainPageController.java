package com.avito.controllers.simple;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping("/main_page")
    public String getMainPage() {
        return "index";
    }

    @GetMapping("/adDetails")
    public String getDetailsPage() {
        return "adDetails";
    }

}
