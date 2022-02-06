package com.ayudaencasa.app.controllers;

import com.ayudaencasa.app.criteria.CleaningCriteria;
import com.ayudaencasa.app.dto.input.CreateCleaningDTO;
import com.ayudaencasa.app.dto.input.SearchCleaningDTO;
import com.ayudaencasa.app.entities.Cleaning;
import com.ayudaencasa.app.entities.User;
import com.ayudaencasa.app.exceptions.CleaningNotFoundException;
import com.ayudaencasa.app.services.CleaningService;
import com.ayudaencasa.app.services.S3Service;
import io.github.jhipster.service.filter.BooleanFilter;
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
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Validated
@RequestMapping("/cleaning")
public class CleaningController {
 
    @Autowired
    private CleaningService cleaningService;
    @Autowired
    private S3Service s3service;
     @Autowired
    private ModelMapper modelmap;
    
    @GetMapping("/create")
    public String registry(){
        return "cleaningForm";
    }
    
    @PostMapping("/create")
    public String create(RedirectAttributes redirectat, CreateCleaningDTO inputCleaning) {
        try{
            Cleaning cleaning = new Cleaning();
            System.out.println(inputCleaning);
            cleaningService.validated(inputCleaning);
            modelmap.map(inputCleaning, cleaning);
            cleaning.setCurriculum(s3service.save(inputCleaning.getCv()));
            if(inputCleaning.getWorkingHoursTo() != null){
                cleaning.setHoursTo(inputCleaning.getWorkingHoursTo());    
            }
            if(inputCleaning.getWorkingHoursFrom() != null){
                cleaning.setHoursFrom(inputCleaning.getWorkingHoursFrom());
            }
//            BeanUtils.copyProperties(inputCleaning, cleaning);
            cleaningService.create(cleaning);
            redirectat.addFlashAttribute("success", "Se ha registrado con éxito en servicio doméstico");
            return "redirect:/home";
        }catch (CleaningNotFoundException ex) {
            redirectat.addFlashAttribute("error", ex.getMessage());
            redirectat.addFlashAttribute("salary", inputCleaning.getSalary());
            redirectat.addFlashAttribute("rooms", inputCleaning.getRooms());
            redirectat.addFlashAttribute("workingHoursFrom", inputCleaning.getWorkingHoursFrom());
            redirectat.addFlashAttribute("workingHoursTo", inputCleaning.getWorkingHoursTo());
            return "redirect:/cleaning/create";
        }    
    }
     
    @GetMapping("/list")
    public String findAll(Model model, @RequestParam(required = false) List<Cleaning> cleanings) {
        if (cleanings != null) {
            model.addAttribute("cleanings", cleanings);
        } else {
            model.addAttribute("cleanings", cleaningService.findAll());
        }
        return "cleaningList";
    }
    
    @PostMapping("/list")
    public String findByFilter(SearchCleaningDTO searchCleaning, RedirectAttributes rt) {    
        if(searchCleaning.getWorkingHoursTo() != null){
            searchCleaning.setHoursTo(searchCleaning.getWorkingHoursTo());    
        }
        if(searchCleaning.getWorkingHoursFrom() != null){
            searchCleaning.setHoursFrom(searchCleaning.getWorkingHoursFrom());
        }
        CleaningCriteria cleaningCriteria = createCriteria(searchCleaning);
        List<Cleaning> cleanings = cleaningService.findByCriteria(cleaningCriteria);
       
        if(searchCleaning.getDay() != null && !searchCleaning.getDay().isEmpty()) {
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
        rt.addAttribute("cleanings", cleanings);
        return "redirect:/cleaning/list";
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
    
    @GetMapping("/EditCleaning")
    public String editarClear(){
      //  User user= userService.
        return "editarMisPublicaciones";
        
    }

    @PostMapping("/updateCleaning")
    public void update(@PathVariable String id, Cleaning newCleaning) {
         UserDetails userDetails;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            throw new RuntimeException("Logeate capo");
        }
        userDetails = (UserDetails)auth.getPrincipal();
        User user = userService.findByEmail(userDetails.getUsername());
        newCleaning = cleaningService.findById(user.getId());
        if(newCleaning!=null){
            
        cleaningService.update(id, newCleaning);
        }
    }

    @PostMapping("/delete")
    public void delete(String id) throws CleaningNotFoundException {
        cleaningService.delete(id);
    }

    @GetMapping("")
    public Cleaning findById(String id) throws CleaningNotFoundException {       
        return cleaningService.findById(id);
    }
}
