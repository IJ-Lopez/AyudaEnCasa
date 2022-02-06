package com.ayudaencasa.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GlobalController {

    @GetMapping
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }

    @GetMapping("/unauthorized")
    public String unauthorized() {
        return "redirect:/home";
    }
    
//        @GetMapping("/unauthorized")
//    public String unauthorized() {
//        return "redirect:/home";
//    }    

}
