package com.ayudaencasa.app.controllers;

import com.ayudaencasa.app.entities.Caregiver;
import com.ayudaencasa.app.entities.Cleaning;
import com.ayudaencasa.app.entities.Gardener;
import com.ayudaencasa.app.entities.PetWalker;
import com.ayudaencasa.app.services.JobBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/home")
@Validated
public class HomeController {

    @Autowired
    private JobBeanService jobService;
    
    @GetMapping
    public String index(String error, ModelMap map) {
        if(error != null){
            map.addAttribute("error", error);
        }
        return "index.html";
    }

    @PostMapping("/buscar")
    public String search(@RequestParam(name = "trabajo") String type, RedirectAttributes ra ) {
        if (type == null || type.isEmpty()) {
            ra.addAttribute("error", "Inserte un parámetro de busqueda");
            return "redirect:/home";
        }
        if(type.length() < 4){
            ra.addAttribute("error", "Debe ingresar al menos 4 caracteres");
            return "redirect:/home";
        }
        type = type.toLowerCase();
        if(Caregiver.JOB_TYPE.toLowerCase().startsWith(type)){
            return "redirect:/cuidador/list";
        }
        if(Cleaning.JOB_TYPE.toLowerCase().startsWith(type)){
            return "redirect:/limpiador/list";
        }
        if(Gardener.JOB_TYPE.toLowerCase().startsWith(type)){
            return "redirect:/jardinero/list";
        }
        if(PetWalker.JOB_TYPE.toLowerCase().startsWith(type)){
            return "redirect:/paseador/list";
        }
        ra.addAttribute("type", type);
        return "redirect:/other/list";
    }

    private boolean startsWithIgnoreCase(String matcher, String input) {
        if (input == null) {
            return false;
        }
        return matcher.toLowerCase().matches(String.format("%s(.*)?", input));
    }
}
