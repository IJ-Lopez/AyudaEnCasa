
package com.ayudaencasa.app.controllers;

import antlr.StringUtils;
import com.ayudaencasa.app.criteria.PetWalkerCriteria;
import com.ayudaencasa.app.dtos.CreatePetWalkerDTO;
import com.ayudaencasa.app.dtos.SearchPetWalkerDTO;
import com.ayudaencasa.app.entities.Gardener;
import com.ayudaencasa.app.entities.PetWalker;
import com.ayudaencasa.app.services.PetWalkerService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    
    @PostMapping ("/create")
    @ResponseStatus(HttpStatus.OK)
    public PetWalker create (@Valid @RequestBody CreatePetWalkerDTO inputPetWalker){
        PetWalker petWalker = new PetWalker();
        BeanUtils.copyProperties(inputPetWalker,petWalker );
        return petWalkerService.create(petWalker);
    }
    @GetMapping ("/list")
    public List <PetWalker> findAll (@RequestParam (required = false) String q){
        return petWalkerService.findAll();
    }
    @PostMapping ("/filter")
    public ResponseEntity <List<Gardener>> findByFilter (@RequestBody SearchPetWalkerDTO searchPetWalker){
        PetWalkerCriteria petWalkerCriteria = createCriteria (searchPetWalker);
        List <PetWalker> petWalkers = petWalkerService.findByIdCriteria(petWalkerCriteria);
        return new ResponseEntity<>(petWalkers, HttpStatus.OK);
    }
    
    private PetWalkerCriteria createCriteria (SearchPetWalkerDTO searchPetWalker){
        PetWalkerCriteria petWalkerCriteria = new PetWalkerCriteria();
        if (searchPetWalker != null){
          
            if (searchPetWalker.getPetQuantityFrom()!= null || searchPetWalker.getPetQuantityTo()!= null) {
                IntegerFilter filter = new IntegerFilter ();
                if (searchPetWalker.getPetQuantityFrom()!= null ) {
                    filter.setGreaterThanOrEqual (searchPetWalker.getPetQuantityFrom());
                    petWalkerCriteria.setPetQuantity(filter);
                    }
                if (searchPetWalker.getPetQuantityTo()!= null ) {
                    filter.setLessThanOrEqual (searchPetWalker.getPetQuantityTo());
                    petWalkerCriteria.setPetQuantity(filter);
                }
            }
            if (!StringUtils.isBlank (searchPetWalker.getPetRace())) {
                StringFilter filter = new StringFilter();
                filter.setContains (searchPetWalker.getPetRace());
                petWalkerCriteria.setPetRace (filter);
                 }
            if (!StringUtils.isBlank (searchPetWalker.getPetType())) {
                StringFilter filter = new StringFilter();
                filter.setContains (searchPetWalker.getPetType());
                petWalkerCriteria.setPetType (filter);
            }
        }
        return petWalkerCriteria;
    }
    @GetMapping("")
    public PetWalker findById (@RequestParam String id){
        return petWalkerService.findById(id);
    }
    
    @GetMapping ("/delete")
    public void delete (String id){
        petWalkerService.delete(id);
    }
    
    @PostMapping("/update")
    public void update (String id, PetWalker newPetWalker){
        petWalkerService.update(id, newPetWalker);
        
    }
}
