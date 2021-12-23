/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayudaencasa.app.controllers;


import com.ayudaencasa.app.entities.Gardener;
import com.ayudaencasa.app.services.GardenerService;
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
    public Gardener create(@Valid @RequestBody createGardenerDTO inputGardener) {
        Gardener gardener = new Gardener();
        BeanUtils.copyProperties(inputGardener, gardener);
        return gardenerService.create(gardener);
    }
    
    @GetMapping
    public List<Gardener> findAll(@RequestParam(required = false) String q) {
        return gardenerService.findAll();
    }
    
    @GetMapping
    public Gardener findById(String id) {
        return gardenerService.findById(id);
    }
    
    
    @GetMapping
    public void delete(String id) {
        gardenerService.delete(id);
    }
    
    @PostMapping
    public void update(String id, Gardener newGardener) {
        gardenerService.update(id, newGardener);
    }
    
}
