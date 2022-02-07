package com.ayudaencasa.app.controllers;

import com.ayudaencasa.app.entities.User;
import com.ayudaencasa.app.services.MailService;
import com.ayudaencasa.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/mail")
public class MailController {
    
    @Autowired
    private MailService mailService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/send")
    public String contact(Model model, @RequestParam(required = false) String id) {
        if (id != null) {
            User user = userService.findById(id);
            if (user != null) {
                model.addAttribute("email", user.getEmail());
            }
        }
        return "mail";
    }
    
    @PostMapping("/send")
    public String sendMail(@RequestParam String email, @RequestParam String subject, @RequestParam String message){
        mailService.sendMail(email, subject, message);
        return "redirect:/home";
    }
}