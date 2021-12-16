
package com.ayudaencasa.app.controllers;

import com.ayudaencasa.app.services.CleaningService;
import java.util.Date;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    @Autowired
    private CleaningService cleaningService;
    @GetMapping("/form")
    public String create(@NonNull @RequestParam Integer rooms, @RequestParam Boolean exteriors, @RequestParam Boolean cooking, @RequestParam Boolean laundry, @RequestParam Boolean ironing, @RequestParam Integer salary, @RequestParam String workingZone, @RequestParam Date dateFrom, @RequestParam Date dateTo, @RequestParam Boolean status){
        try {
            cleaningService.create(rooms, exteriors, cooking, laundry, ironing, salary, workingZone, dateFrom, dateTo, status);
        } catch (Exception ex){
            System.out.println("Error en nombre o alta");
            return "cleaning";
        }
        return "cleaning";
    }
    
}
