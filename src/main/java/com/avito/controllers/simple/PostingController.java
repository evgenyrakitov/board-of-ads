package com.avito.controllers.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PostingController {
    private static final Logger logger = LoggerFactory.getLogger(PostingController.class);

    @GetMapping("/posting")
    public String getDetailsPage() {
        return "posting";
    }

    @GetMapping("/addNewPosting")
    public String getNewPostingPage() {
        return "addNewPosting";
    }

    @GetMapping("/newPosting/{name}")
    public String getMainPage(@PathVariable String name) {
        //для редиректа на будущие заготовки страниц для новых объявлений
        return "addNewPosting";
    }

}
