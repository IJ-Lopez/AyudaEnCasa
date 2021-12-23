package com.ayudaencasa.app.controllers;

import com.ayudaencasa.app.dtos.CreateCleaningDTO;
import com.ayudaencasa.app.entities.Cleaning;
import com.ayudaencasa.app.repositories.CleaningRepository;
import com.ayudaencasa.app.services.CleaningService;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/")
public class CleaningController {
    // Agregar pagina HTML

//    @GetMapping("")
//    public String Cleaning(){
//       return "" 
//    }
    @Autowired
    private CleaningService cleaningService;
    @Autowired
    private CleaningRepository cleaningRepository;

    @PostMapping("/form")
    public Cleaning create(@Valid @RequestBody CreateCleaningDTO inputCleaning) {
        Cleaning cleaning = new Cleaning();
        BeanUtils.copyProperties(inputCleaning, cleaning);

//        try {
//            cleaningService.create(cleaning);
//        } catch (Exception ex){
//            System.out.println("Error en nombre o alta");
//            return "cleaning";
//        }
//        return "cleaning.HTML";
        return cleaningService.create(cleaning);
    }

    @GetMapping("/viewCleaning")
    public List<Cleaning> findAll() {
        return cleaningService.findAll();

//        model.addAttribute("cleaning", cleaningService.findAll());
//        return "find.HTML";
    }

    @GetMapping("/")
    public Cleaning findById(String id) throws Exception {
        return cleaningService.findById(id);
    }

}
