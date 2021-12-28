package com.ayudaencasa.app.controllers;

import com.ayudaencasa.app.entities.Caregiver;
import com.ayudaencasa.app.interfaz.CaregiverService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/gardener")
public class CaregiverController {

@Autowired
private CaregiverService caregiverService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Caregiver create(@Valid @RequestBody CaregiverController inputCaregiver) {
        Caregiver caregiver = new Caregiver();
        BeanUtils.copyProperties(inputCaregiver, caregiver);
        return caregiverService.create(caregiver);
    }
    
    @GetMapping
    public List<Caregiver> findAll(@RequestParam(required = false) String q) {
        return caregiverService.findAll();
    }
    
     @GetMapping
    public Caregiver findById(String id) throws Exception {
        return caregiverService.findById(id);
    }
     
    @GetMapping
    public void delete(String id) throws Exception {
        caregiverService.delete(id);
    }
    
     @PostMapping
    public void update(String id, Caregiver newCaregiver) throws Exception {
        caregiverService.update(id, newCaregiver);
    }
}
