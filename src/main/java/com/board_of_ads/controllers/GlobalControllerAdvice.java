package com.board_of_ads.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("user")
    public Object fillCurrentUser() {


        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}