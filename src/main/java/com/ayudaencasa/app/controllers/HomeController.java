package com.ayudaencasa.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String index() {
        return "index.html";
    }

    @PostMapping("/buscar")
    public String search(@RequestParam(name = "trabajo") String type, ModelMap map, RedirectAttributes ra) {
        if (type == null) {
            map.put("error", "Inserte un parámetro de busqueda");
            return "index.html";
        }

        type = type.toLowerCase();
        String[] jobs = {"jardinero", "paseador", "cuidador", "limpiador"};
        for (String job : jobs) {
            if (like(job, type)) {
                return String.format("redirect:/%s/list", job);
            }
        }
        
        ra.addAttribute("type", type);
        return "redirect:/other/list";
    }

    private boolean like(String matcher, String input) {
        if (input == null) {
            return false;
        }
        return matcher.matches(String.format("(.*)?%s(.*)?", input));
    }
}
