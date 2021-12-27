
package com.ayudaencasa.app.controllers;

import com.ayudaencasa.app.entities.PetWalker;
import com.ayudaencasa.app.services.PetWalkerService;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author martina
 */
@RestController
@Validated
@RequestMapping ("/petWalker")
public class PetWalkerController {
    
    @Autowired
    private PetWalkerService petWalkerService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public PetWalker create (@Valid @RequestBody createPetWalkerDTO inputPetWalker){
        PetWalker petWalker = new PetWalker();
        BeanUtils.copyProperties(inputPetWalker,petWalker );
        return petWalkerService.create(petWalker);
    }
    
    public List <PetWalker> findAll (@RequestParam)
    
    
    
}
