package com.ayudaencasa.app.controllers;

import com.ayudaencasa.app.criteria.CleaningCriteria;
import com.ayudaencasa.app.dto.input.CreateCleaningDTO;
import com.ayudaencasa.app.dto.input.SearchCleaningDTO;
import com.ayudaencasa.app.entities.Cleaning;
import com.ayudaencasa.app.exceptions.CleaningNotFoundException;
import com.ayudaencasa.app.services.CleaningService;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.StringFilter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Validated
@RequestMapping("/cleaning")
public class CleaningController {
 
    @Autowired
    private CleaningService cleaningService;
    
    @GetMapping("/create")
    public String registry(){
        return "cleaningForm";
    }
    
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public String create(RedirectAttributes redirectAttributes, CreateCleaningDTO inputCleaning, @RequestParam LocalTime timeFrom, @RequestParam LocalTime timeTo) {
        try{
            Cleaning cleaning = new Cleaning();
            if(timeFrom != null){
                cleaning.setHoursTo(timeFrom);    
            }
            if(timeTo != null){
                cleaning.setHoursFrom(timeTo);
            }
            BeanUtils.copyProperties(inputCleaning, cleaning);
            cleaningService.create(cleaning);
            return "index";
        }catch (CleaningNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "cleaningForm";
        }    
    }
    
    @PostMapping("/filter")
    public ResponseEntity<List<Cleaning>> findByFilter(@RequestBody SearchCleaningDTO searchCleaning) {    
        if(searchCleaning.getWorkingHoursTo() != null){
            searchCleaning.setHoursTo(searchCleaning.getWorkingHoursTo());    
        }
        if(searchCleaning.getWorkingHoursFrom() != null){
            searchCleaning.setHoursFrom(searchCleaning.getWorkingHoursFrom());
        }
        CleaningCriteria cleaningCriteria = createCriteria(searchCleaning);
        List<Cleaning> cleanings = cleaningService.findByCriteria(cleaningCriteria);
       
        if(searchCleaning.getDay() != null) {
            List<Cleaning> clean = new ArrayList<>();
            for (Cleaning cleaning : cleanings){
                for (String day : cleaning.getDays()) {
                    if(day.equalsIgnoreCase(searchCleaning.getDay())) {
                        clean.add(cleaning);
                    }
                } 
            }
            cleanings = clean;
        }  
        return new ResponseEntity<>(cleanings, HttpStatus.OK);
    }
    
    private CleaningCriteria createCriteria(SearchCleaningDTO searchCleaning){
        CleaningCriteria cleaningCriteria = new CleaningCriteria();
        if(searchCleaning != null){
            if(searchCleaning.getRoomsFrom() != null || searchCleaning.getRoomsTo() != null){
                IntegerFilter filter = new IntegerFilter();
                if(searchCleaning.getRoomsFrom() != null){
                    filter.setGreaterThanOrEqual(searchCleaning.getRoomsFrom());
                    cleaningCriteria.setRooms(filter);
                }
                if(searchCleaning.getRoomsTo() != null){
                    filter.setLessThanOrEqual(searchCleaning.getRoomsTo());
                    cleaningCriteria.setRooms(filter);
                }
            }
            if(!StringUtils.isBlank(searchCleaning.getExteriors())){
                BooleanFilter filter = new BooleanFilter();
                switch(searchCleaning.getExteriors()){
                    case "true":
                        filter.setEquals(true);
                        break;
                    case "false":
                        filter.setEquals(false);
                        break;
                    default:
                        filter.setEquals(false);        
                }
                cleaningCriteria.setExteriors(filter);
            }
            if(!StringUtils.isBlank(searchCleaning.getIroning())){
                BooleanFilter filter = new BooleanFilter();
                switch(searchCleaning.getIroning()){
                    case "true":
                        filter.setEquals(true);
                        break;
                    case "false":
                        filter.setEquals(false);
                        break;
                    default:
                        filter.setEquals(false);        
                }
                cleaningCriteria.setIroning(filter);
            }
            if(!StringUtils.isBlank(searchCleaning.getLaundry())){
                BooleanFilter filter = new BooleanFilter();
                switch(searchCleaning.getLaundry()){
                    case "true":
                        filter.setEquals(true);
                        break;
                    case "false":
                        filter.setEquals(false);
                        break;
                    default:
                        filter.setEquals(false);        
                }
                cleaningCriteria.setLaundry(filter);
            }
            if(!StringUtils.isBlank(searchCleaning.getCooking())){
                BooleanFilter filter = new BooleanFilter();
                switch(searchCleaning.getCooking()){
                    case "true":
                        filter.setEquals(true);
                        break;
                    case "false":
                        filter.setEquals(false);
                        break;
                    default:
                        filter.setEquals(false);        
                }
                cleaningCriteria.setCooking(filter);
            }
            if(searchCleaning.getSalaryFrom()!= null || searchCleaning.getSalaryTo()!= null){
                IntegerFilter filter = new IntegerFilter();
                if(searchCleaning.getSalaryFrom() != null){
                    filter.setGreaterThanOrEqual(searchCleaning.getSalaryFrom());
                    cleaningCriteria.setSalary(filter);
                }
                if(searchCleaning.getSalaryTo() != null){
                    filter.setLessThanOrEqual(searchCleaning.getSalaryTo());
                    cleaningCriteria.setSalary(filter);
                }
            }
            if(!StringUtils.isBlank(searchCleaning.getWorkingZone())){
                StringFilter filter = new StringFilter();
                filter.setContains(searchCleaning.getWorkingZone());
                cleaningCriteria.setWorkingZone(filter);
            }
            if(!StringUtils.isBlank(searchCleaning.getDescription())){
                StringFilter filter = new StringFilter();
                filter.setContains(searchCleaning.getDescription());
                cleaningCriteria.setDescription(filter);
            }
            if(searchCleaning.getWorkingHoursFrom() != null) {           
                IntegerFilter filter = new IntegerFilter();
                filter.setLessThanOrEqual(searchCleaning.getHoursFrom());
                cleaningCriteria.setHoursFrom(filter);
            }
            if(searchCleaning.getWorkingHoursTo() != null){               
                IntegerFilter filter = new IntegerFilter();
                filter.setGreaterThanOrEqual(searchCleaning.getHoursTo());
                cleaningCriteria.setHoursTo(filter);
            }
        }
        return cleaningCriteria;
    }

    @PostMapping("/update")
    public void update(String id, Cleaning newCleaning) {
        cleaningService.update(id, newCleaning);
    }

    @PostMapping("/delete")
    public void delete(String id) throws CleaningNotFoundException {
        cleaningService.delete(id);
    }

    @GetMapping("/list")
    public List<Cleaning> findAll(@RequestParam(required = false) String q) {
        return cleaningService.findAll();
    }

    @GetMapping("")
    public Cleaning findById(String id) throws CleaningNotFoundException {       
        return cleaningService.findById(id);
    }
}
