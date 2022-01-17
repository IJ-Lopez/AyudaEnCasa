package com.ayudaencasa.app.controllers;

import com.ayudaencasa.app.dtos.CreateCleaningDTO;
import com.ayudaencasa.app.entities.Cleaning;
import com.ayudaencasa.app.exceptions.CleaningNotFoundException;
import com.ayudaencasa.app.repositories.CleaningRepository;
import com.ayudaencasa.app.services.CleaningService;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/Cleaning")
public class CleaningController {
 
    @Autowired
    private CleaningService cleaningService;

    @PostMapping("/form")
    @ResponseStatus(HttpStatus.OK)
    public Cleaning create(@Valid @RequestBody CreateCleaningDTO inputCleaning) {
        Cleaning cleaning = new Cleaning();
        BeanUtils.copyProperties(inputCleaning, cleaning);

        return cleaningService.create(cleaning);
    }

    @PostMapping("/")
    public void update(String id, Cleaning newCleaning) {

        cleaningService.update(id, newCleaning);
    }

    @PostMapping
    public void delete(String id) throws CleaningNotFoundException {
        cleaningService.delete(id);
    }

    @GetMapping("/viewCleaning")
    public List<Cleaning> findAll() {
        return cleaningService.findAll();

    }

    @GetMapping("/{id}")
    public Cleaning findById(String id) throws CleaningNotFoundException {
        
        
        return cleaningService.findById(id);
    }

}
