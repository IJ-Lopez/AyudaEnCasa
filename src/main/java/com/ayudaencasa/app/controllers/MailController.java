package com.ayudaencasa.app.controllers;

import com.ayudaencasa.app.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("")
public class MailController {
    
    @Autowired
    private MailService mailService;
    
    @GetMapping("/send")
    public String contact() {
        return "mail";
    }
    
    @PostMapping("/send")
    public String sendMail(@RequestParam String addressee, @RequestParam String subject, @RequestParam String message){
        mailService.sendMail(addressee, subject, message);
        return "index";
    }
}
