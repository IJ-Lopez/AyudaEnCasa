package com.ayudaencasa.app.controllers;

import com.ayudaencasa.app.entities.Caregiver;
import com.ayudaencasa.app.entities.Cleaning;
import com.ayudaencasa.app.entities.Gardener;
import com.ayudaencasa.app.entities.Job;
import com.ayudaencasa.app.entities.Other;
import com.ayudaencasa.app.entities.PetWalker;
import com.ayudaencasa.app.entities.User;
import com.ayudaencasa.app.services.JobBeanService;
import com.ayudaencasa.app.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jobs")
public class JobController {
    
    @Autowired
    private JobBeanService jobService;
    
    @Autowired
    private UserService userService;
    
//    @GetMapping("/myjobs")
//    public String findAll(Model model){
//        UserDetails userDetails;
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth instanceof AnonymousAuthenticationToken) {
//            throw new RuntimeException();
//        }
//        userDetails = (UserDetails)auth.getPrincipal();
//        model.addAttribute("jobs", jobService.findByUserEmail(userDetails.getUsername()));
//        return "misPublicaciones";
//    }
    
    @GetMapping("/myjobs")
    public String findAll(Model model){
        UserDetails userDetails;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            throw new RuntimeException();
        }
        userDetails = (UserDetails)auth.getPrincipal();
        List<Job> jobs = jobService.findByUserEmail(userDetails.getUsername());
        jobs.forEach((job) -> {
            if(job.getClass().equals(Caregiver.class)){
                model.addAttribute("caregiver", job);
            } else if (job.getClass().equals(Cleaning.class)){
                model.addAttribute("cleaning", job);
            } else if (job.getClass().equals(Gardener.class)){
                model.addAttribute("gardener", job);
            } else if (job.getClass().equals(PetWalker.class)){
                model.addAttribute("petwalker", job);
            } else if (job.getClass().equals(Other.class)){
                model.addAttribute("others", job);
            }
        });
        System.out.println(model);
        return "misPublicaciones";
    }
    
    @PostMapping("/delete/{id}")
    public String delete(Model model, @PathVariable String id){
        UserDetails userDetails;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            throw new RuntimeException("Logeate capo");
        }
        userDetails = (UserDetails)auth.getPrincipal();
        User user = userService.findByEmail(userDetails.getUsername());
        Job job = jobService.findById(id);
        if(job == null || !job.getUser().equals(user)){
            throw new RuntimeException("Deja de chorear capo");
        }
        jobService.delete(id);
        return "redirect:/jobs/myjobs";
    }
}
