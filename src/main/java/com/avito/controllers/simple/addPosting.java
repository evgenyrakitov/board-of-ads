package com.avito.controllers.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class addPosting {
    private static final Logger logger = LoggerFactory.getLogger(addPosting.class);

    @GetMapping("/addNewPosting")
    public String login() {
        return "addPosting";
    }
}
