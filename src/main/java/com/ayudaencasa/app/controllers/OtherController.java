package com.ayudaencasa.app.controllers;

import com.ayudaencasa.app.criteria.OtherCriteria;
import com.ayudaencasa.app.dto.input.CreateOtherDTO;
import com.ayudaencasa.app.dto.input.SearchOtherDTO;
import com.ayudaencasa.app.entities.Other;
import com.ayudaencasa.app.exceptions.OtherNotFoundException;
import com.ayudaencasa.app.services.OtherService;
import com.ayudaencasa.app.services.S3Service;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.StringFilter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Validated
@RequestMapping("/other")
public class OtherController {
    
    @Autowired
    private OtherService otherService;
    @Autowired
    private S3Service s3service;
     @Autowired
    private ModelMapper modelmap;
    
    @GetMapping("/create")
    public String registry(Model model, @RequestParam(required = false) String id){
        if (id != null) {
            Other other = otherService.findById(id);
            if (other != null) {
                model.addAttribute("id", id);
                model.addAttribute("salary", other.getSalary());
                model.addAttribute("type", other.getType());
            } else {
                return "redirect:/other/list";
            }
        }
        return "otherForm";
    }
    
    @PostMapping("/create")
    public String create(RedirectAttributes redirectat, CreateOtherDTO inputOther) {
        try{
            Other other = new Other();
            System.out.println(inputOther);
            otherService.validated(inputOther);
            modelmap.map(inputOther, other);
            other.setCurriculum(s3service.save(inputOther.getCv()));
            other.setType(inputOther.getType());
            if(inputOther.getWorkingHoursTo() != null){
                other.setHoursTo(inputOther.getWorkingHoursTo());    
            }
            if(inputOther.getWorkingHoursFrom() != null){
                other.setHoursFrom(inputOther.getWorkingHoursFrom());
            }
            if (inputOther.getId() != null) {
                update(inputOther.getId(), other);
                redirectat.addFlashAttribute("success", "Se ha modificado con éxito su trabajo");
            } else {
                otherService.create(other);
                redirectat.addFlashAttribute("success", "Se ha registrado con éxito su trabajo");
            }
            return "redirect:/home";
        }catch (OtherNotFoundException ex) {
            redirectat.addFlashAttribute("error", ex.getMessage());
            redirectat.addFlashAttribute("id", inputOther.getId());
            redirectat.addFlashAttribute("salary", inputOther.getSalary());
            redirectat.addFlashAttribute("type", inputOther.getType());
            redirectat.addFlashAttribute("workingHoursFrom", inputOther.getWorkingHoursFrom());
            redirectat.addFlashAttribute("workingHoursTo", inputOther.getWorkingHoursTo());
            return "redirect:/other/create";
        }    
    }
    
    @GetMapping("/list")
    public String findAll(Model model, @RequestParam(required = false, name = "others") List<String> others, String type) {
        if(type == null){
            type = "";
        }
        if(others != null && !others.isEmpty()){
            System.out.println(others.toString());
            List<Other> jobs = new ArrayList();
            for(String id : others){
                jobs.add(otherService.findById(id));
            }
            model.addAttribute("others", jobs);
        } else {
            model.addAttribute("others", otherService.findByType(type));
        }
        return "otherList";
    }
    
    @PostMapping("/list")
    public String findByFilter(SearchOtherDTO searchOther, RedirectAttributes rt) {    
        if(searchOther.getWorkingHoursTo() != null){
            searchOther.setHoursTo(searchOther.getWorkingHoursTo());    
        }
        if(searchOther.getWorkingHoursFrom() != null){
            searchOther.setHoursFrom(searchOther.getWorkingHoursFrom());
        }
        OtherCriteria otherCriteria = createCriteria(searchOther);
        List<Other> others = otherService.findByCriteria(otherCriteria);
       
        if(searchOther.getDay() != null && !searchOther.getDay().isEmpty()) {
            List<Other> ot = new ArrayList<>();
            for (Other other : others){
                for (String day : other.getDays()) {
                    if(day.equalsIgnoreCase(searchOther.getDay())) {
                        ot.add(other);
                    }
                } 
            }
            others = ot;
        }
        rt.addAttribute("others", others);
        return "redirect:/other/list";
    }
    
    private OtherCriteria createCriteria(SearchOtherDTO searchOther){
        OtherCriteria otherCriteria = new OtherCriteria();
        if(searchOther != null){
            if(!StringUtils.isBlank(searchOther.getType())){
                StringFilter filter = new StringFilter();
                filter.setContains(searchOther.getType());
                otherCriteria.setType(filter);
            }         
            if(searchOther.getSalaryFrom()!= null || searchOther.getSalaryTo()!= null){
                IntegerFilter filter = new IntegerFilter();
                if(searchOther.getSalaryFrom() != null){
                    filter.setGreaterThanOrEqual(searchOther.getSalaryFrom());
                    otherCriteria.setSalary(filter);
                }
                if(searchOther.getSalaryTo() != null){
                    filter.setLessThanOrEqual(searchOther.getSalaryTo());
                    otherCriteria.setSalary(filter);
                }
            }
            if(!StringUtils.isBlank(searchOther.getWorkingZone())){
                StringFilter filter = new StringFilter();
                filter.setContains(searchOther.getWorkingZone());
                otherCriteria.setWorkingZone(filter);
            }
            if(!StringUtils.isBlank(searchOther.getDescription())){
                StringFilter filter = new StringFilter();
                filter.setContains(searchOther.getWorkingZone());
                otherCriteria.setDescription(filter);
            }
            if(searchOther.getWorkingHoursFrom() != null) {           
                IntegerFilter filter = new IntegerFilter();
                filter.setLessThanOrEqual(searchOther.getHoursFrom());
                otherCriteria.setHoursFrom(filter);
            }
            if(searchOther.getWorkingHoursTo() != null){               
                IntegerFilter filter = new IntegerFilter();
                filter.setGreaterThanOrEqual(searchOther.getHoursTo());
                otherCriteria.setHoursTo(filter);
            }
        }
        return otherCriteria;
    }
    
    @GetMapping("")
    public Other findById(@RequestParam String id) {
        return otherService.findById(id);
    }    
    
    @PostMapping("/delete")
    public void delete(@RequestParam String id) {
        otherService.delete(id);
    }
    
    @PostMapping("")
    public void update(@RequestParam String id, Other newOther) {
        otherService.update(id, newOther);
    }
    
}
