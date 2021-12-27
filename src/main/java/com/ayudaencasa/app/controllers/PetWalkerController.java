
package com.ayudaencasa.app.controllers;

import com.ayudaencasa.app.entities.PetWalker;
import com.ayudaencasa.app.services.PetWalkerService;
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
    @GetMapping
    public List <PetWalker> findAll (@RequestParam (required = false) String q){
        return petWalkerService.findAll();
    }
    @GetMapping
    public PetWalker findById (String id){
        return petWalkerService.findById(id);
    }
    
    @GetMapping
    public void delete (String id){
        petWalkerService.delete(id);
    }
    
    @PostMapping
    public void update (String id, PetWalker newPetWalker){
        petWalkerService.update(id, newPetWalker);
        
    }
}
