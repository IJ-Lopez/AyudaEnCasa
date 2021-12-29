/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayudaencasa.app.controllers;


import com.ayudaencasa.app.criteria.GardenerCriteria;
import com.ayudaencasa.app.dtos.CreateGardenerDTO;
import com.ayudaencasa.app.dtos.SearchGardenerDTO;
import com.ayudaencasa.app.entities.Gardener;
import com.ayudaencasa.app.services.GardenerService;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import java.util.List;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
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
 * @author Usr
 */
@RestController
@Validated
@RequestMapping("/gardener")
public class GardenerController {
    
    @Autowired
    private GardenerService gardenerService;
    
    
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Gardener create(@Valid @RequestBody CreateGardenerDTO inputGardener) {
        Gardener gardener = new Gardener();
        BeanUtils.copyProperties(inputGardener, gardener);
        return gardenerService.create(gardener);
    }
    
    @GetMapping
    public List<Gardener> findAll(@RequestParam(required = false) String q) {
        return gardenerService.findAll();
    }
    
    @PostMapping
    public ResponseEntity<List<Gardener>> findByFilter(@RequestBody SearchGardenerDTO searchGardener) {
        GardenerCriteria gardenerCriteria = createCriteria(searchGardener);
        List<Gardener> gardeners = gardenerService.findByCriteria(gardenerCriteria);
        return new ResponseEntity<>(gardeners, HttpStatus.OK);
    }
    
    private GardenerCriteria createCriteria(SearchGardenerDTO searchGardener){
        GardenerCriteria gardenerCriteria = new GardenerCriteria();
        if(searchGardener != null){
            if(searchGardener.getSurfaceFrom() != null || searchGardener.getSurfaceTo() != null){
                DoubleFilter filter = new DoubleFilter();
                if(searchGardener.getSurfaceFrom() != null){
                    filter.setGreaterThanOrEqual(searchGardener.getSurfaceFrom());
                    gardenerCriteria.setSurface(filter);
                }
                if(searchGardener.getSurfaceTo() != null){
                    filter.setLessThanOrEqual(searchGardener.getSurfaceTo());
                    gardenerCriteria.setSurface(filter);
                }
            }
            if(!StringUtils.isBlank(searchGardener.getTools())){
                BooleanFilter filter = new BooleanFilter();
                switch(searchGardener.getTools()){
                    case "true":
                        filter.setEquals(true);
                        break;
                    case "false":
                        filter.setEquals(false);
                        break;
                    default:
                        filter.setEquals(false);        
                }
                gardenerCriteria.setTools(filter);
            }
            if(!StringUtils.isBlank(searchGardener.getPoolCleaning())){
                BooleanFilter filter = new BooleanFilter();
                switch(searchGardener.getPoolCleaning()){
                    case "true":
                        filter.setEquals(true);
                        break;
                    case "false":
                        filter.setEquals(false);
                        break;
                    default:
                        filter.setEquals(false);        
                }
                gardenerCriteria.setPoolCleaning(filter);
            }
            if(!StringUtils.isBlank(searchGardener.getGardenFence())){
                BooleanFilter filter = new BooleanFilter();
                switch(searchGardener.getGardenFence()){
                    case "true":
                        filter.setEquals(true);
                        break;
                    case "false":
                        filter.setEquals(false);
                        break;
                    default:
                        filter.setEquals(false);        
                }
                gardenerCriteria.setGardenFence(filter);
            }
            if(!StringUtils.isBlank(searchGardener.getPlantDisinfection())){
                BooleanFilter filter = new BooleanFilter();
                switch(searchGardener.getPlantDisinfection()){
                    case "true":
                        filter.setEquals(true);
                        break;
                    case "false":
                        filter.setEquals(false);
                        break;
                    default:
                        filter.setEquals(false);        
                }
                gardenerCriteria.setPlantDisinfection(filter);
            }
        }
        return gardenerCriteria;
    }
    
    @GetMapping
    public Gardener findById(String id) {
        return gardenerService.findById(id);
    }
    
//    @GetMapping
//    public List<Gardener> findByQuery(String q) {
//        return gardenerService.findByQuery(q);
//    }
    
    
    @GetMapping
    public void delete(String id) {
        gardenerService.delete(id);
    }
    
    @PostMapping
    public void update(String id, Gardener newGardener) {
        gardenerService.update(id, newGardener);
    }
    
}
