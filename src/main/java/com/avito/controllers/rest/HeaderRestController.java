package com.avito.controllers.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/")
public class HeaderRestController {
    private static final Logger logger = LoggerFactory.getLogger(HeaderRestController.class);

    @GetMapping("/avitoNavigation")
    public ResponseEntity<List<String>> getAvitoNavigation() {
        List<String> list = new ArrayList<>();
        list.add("Объявления");
        list.add("Магазины");
        list.add("Бизнес");
        list.add("Бизнес");
        list.add("Помощь");
        return !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
