/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayudaencasa.app.controllers;


import com.ayudaencasa.app.criteria.GardenerCriteria;
import com.ayudaencasa.app.criteria.GardenerCriteria.LocalTimeFilter;

import com.ayudaencasa.app.dtos.CreateGardenerDTO;
import com.ayudaencasa.app.dtos.SearchGardenerDTO;
import com.ayudaencasa.app.entities.Gardener;
import com.ayudaencasa.app.enums.Day;
import com.ayudaencasa.app.services.GardenerService;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LocalDateFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;
import java.time.ZonedDateTime;
import java.util.ArrayList;
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
    
    
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Gardener create(@RequestBody CreateGardenerDTO inputGardener) {
        Gardener gardener = new Gardener();
        if(inputGardener.getWorkingHoursTo() != null){
            gardener.setHoursTo(inputGardener.getWorkingHoursTo());    
        }
        if(inputGardener.getWorkingHoursFrom() != null){
            gardener.setHoursFrom(inputGardener.getWorkingHoursFrom());
        }
        BeanUtils.copyProperties(inputGardener, gardener);
        return gardenerService.create(gardener);
    }
    
    @GetMapping("/list")
    public List<Gardener> findAll(@RequestParam(required = false) String q) {
        return gardenerService.findAll();
    }
    
//    @PostMapping("/filter")
//    public ResponseEntity<List<Gardener>> findByFilter(@RequestBody SearchGardenerDTO searchGardener) {
//        GardenerCriteria gardenerCriteria = createCriteria(searchGardener);
//        List<Gardener> gardeners = gardenerService.findByCriteria(gardenerCriteria);
//        
//        if(searchGardener.getDay() != null) {
//            List<Gardener> gar = new ArrayList<>();
//            for (Gardener gardener : gardeners){
//                for (String day : gardener.getDays()) {
//                    if(day.equalsIgnoreCase(searchGardener.getDay())) {
//                        gar.add(gardener);
//                    }
//                } 
//            }
//            gardeners = gar;
//        }
//        if(searchGardener.getWorkingHoursFrom() != null) {
//            List<Gardener> gar = new ArrayList<>();
//            for (Gardener gardener : gardeners){
//                if(gardener.getWorkingHoursFrom().isBefore(searchGardener.getWorkingHoursFrom()) || gardener.getWorkingHoursFrom().equals(searchGardener.getWorkingHoursFrom())) {
//                    gar.add(gardener);    
//                } 
//            }
//            gardeners = gar;
//        }
//        if(searchGardener.getWorkingHoursTo() != null) {
//            List<Gardener> gar = new ArrayList<>();
//            for (Gardener gardener : gardeners){
//                if(gardener.getWorkingHoursTo().isAfter(searchGardener.getWorkingHoursTo()) || gardener.getWorkingHoursTo().equals(searchGardener.getWorkingHoursTo())) {
//                    gar.add(gardener);    
//                } 
//            }
//            gardeners = gar;
//        }    
//        return new ResponseEntity<>(gardeners, HttpStatus.OK);
//    }
    
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
            if(searchGardener.getSalaryFrom()!= null || searchGardener.getSalaryTo()!= null){
                IntegerFilter filter = new IntegerFilter();
                if(searchGardener.getSalaryFrom() != null){
                    filter.setGreaterThanOrEqual(searchGardener.getSalaryFrom());
                    gardenerCriteria.setSalary(filter);
                }
                if(searchGardener.getSalaryTo() != null){
                    filter.setLessThanOrEqual(searchGardener.getSalaryTo());
                    gardenerCriteria.setSalary(filter);
                }
            }
            if(!StringUtils.isBlank(searchGardener.getWorkingZone())){
                StringFilter filter = new StringFilter();
                filter.setContains(searchGardener.getWorkingZone());
                gardenerCriteria.setWorkingZone(filter);
            }
            if(!StringUtils.isBlank(searchGardener.getDescription())){
                StringFilter filter = new StringFilter();
                filter.setContains(searchGardener.getDescription());
                gardenerCriteria.setDescription(filter);
            }
//            if(searchGardener.getWorkingHoursFrom() != null) {
//                IntegerFilter filter = new IntegerFilter();
//                int seconds = searchGardener.getWorkingHoursFrom().toSecondOfDay();
////                long hour = searchGardener.getWorkingHoursFrom().
//                filter.setLessThanOrEqual(seconds);
//                gardenerCriteria.setWorkingHoursFrom(filter);
//            }
//            if(searchGardener.getWorkingHoursTo()!= null){
//                IntegerFilter filter = new IntegerFilter();
//                int seconds = searchGardener.getWorkingHoursTo().toSecondOfDay();
////                long hour = searchGardener.getWorkingHoursFrom().getTime(); 
//                filter.setGreaterThanOrEqual(seconds);
//                gardenerCriteria.setWorkingHoursTo(filter);
//            }
        }
        return gardenerCriteria;
    }
    
    @GetMapping("")
    public Gardener findById(@RequestParam String id) {
        return gardenerService.findById(id);
    }    
    
    @PostMapping("/delete")
    public void delete(@RequestParam String id) {
        gardenerService.delete(id);
    }
    
    @PostMapping("/update")
    public void update(@RequestParam String id, Gardener newGardener) {
        gardenerService.update(id, newGardener);
    }
    
}
