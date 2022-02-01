package com.ayudaencasa.app.controllers;

import com.ayudaencasa.app.repositories.JobBeanRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    
    @Autowired
    JobBeanRepository jobRepo;
    
    @GetMapping
    public List findAll(@RequestParam String type){
        if(type==null){
            type = "";
        }
        System.out.println(type);
        return jobRepo.findByTypeIgnoreCaseStartsWith(type);
    }
    
}
