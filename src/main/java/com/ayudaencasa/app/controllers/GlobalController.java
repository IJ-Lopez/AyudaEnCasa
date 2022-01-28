package com.ayudaencasa.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GlobalController {
    
    @GetMapping
    public String index(){
        return "redirect:/home";
    }
    
    @GetMapping("login")
    public String login(){
        return "login.html";
    }
}
