
package com.ayudaencasa.app.controllers;

import com.ayudaencasa.app.entities.Cleaning;
import com.ayudaencasa.app.services.CleaningService;
import java.util.Date;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class CleaningController {
    // Agregar pagina HTML

//    @GetMapping("")
//    public String Cleaning(){
//       return "" 
//    }
//    @Autowired
//    private CleaningService cleaningService;
//    
//    @PostMapping("/form")
//    public String create(@NonNull @RequestParam Cleaning cleaning){ 
////        try {
////            cleaningService.create(cleaning);
////        } catch (Exception ex){
////            System.out.println("Error en nombre o alta");
////            return "cleaning";
////        }
//        return "cleaning.HTML";
//        
//    }
//    @GetMapping("/viewCleaning")
//    public String findAll(Model model){
//        model.addAttribute("cleaning", cleaningService.findAll());
//        return "find.HTML";
//    }
}
