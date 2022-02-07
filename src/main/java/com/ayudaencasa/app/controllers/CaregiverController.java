package com.ayudaencasa.app.controllers;

import com.ayudaencasa.app.criteria.CaregiverCriteria;
import com.ayudaencasa.app.dto.input.CreateCaregiverDTO;
import com.ayudaencasa.app.dto.input.SearchCaregiverDTO;
import com.ayudaencasa.app.entities.Caregiver;
import com.ayudaencasa.app.exceptions.CaregiverNotFoundException;
import com.ayudaencasa.app.services.CaregiverService;
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
@RequestMapping("/caregiver")
public class CaregiverController {

    @Autowired
    private CaregiverService caregiverService;
    @Autowired
    private S3Service s3service;
     @Autowired
    private ModelMapper modelmap;

    @GetMapping("/create")
    public String registry(Model model, @RequestParam(required = false) String id){
        if (id != null) {
            Caregiver caregiver = caregiverService.findById(id);
            if (caregiver != null) {
                model.addAttribute("salary", caregiver.getSalary());
                model.addAttribute("quantity", caregiver.getQuantity());
            } else {
                return "redirect:/caregiver/list";
            }
        }
        return "caregiverForm";
    }
        
    @PostMapping("/create")
    public String create(RedirectAttributes redirectat, @ModelAttribute CreateCaregiverDTO inputCaregiver) {
        try {
            Caregiver caregiver = new Caregiver();
            caregiverService.validated(inputCaregiver);
            modelmap.map(inputCaregiver, caregiver);
            caregiver.setCurriculum(s3service.save(inputCaregiver.getCv()));
            if (inputCaregiver.getWorkingHoursTo() != null) {
                caregiver.setHoursTo(inputCaregiver.getWorkingHoursTo());
            }
            if (inputCaregiver.getWorkingHoursFrom() != null) {
                caregiver.setHoursFrom(inputCaregiver.getWorkingHoursFrom());
            }
            switch (inputCaregiver.getAgeRange()) {
                case "a":
                    caregiver.setAgeFrom(0);
                    caregiver.setAgeTo(5);
                    break;
                case "b":
                    caregiver.setAgeFrom(6);
                    caregiver.setAgeTo(10);
                    break;
                case "c":
                    caregiver.setAgeFrom(11);
                    caregiver.setAgeTo(18);
                    break;
                case "d":
                    caregiver.setAgeFrom(60);
                    caregiver.setAgeTo(100);
                    break;
                case "e":
                    caregiver.setAgeFrom(0);
                    caregiver.setAgeTo(100);
            }
            caregiverService.create(caregiver);
            redirectat.addFlashAttribute("success", "Se ha registrado con éxito en cuidado de personas");
            return "redirect:/home";
        } catch (CaregiverNotFoundException ex) {
            redirectat.addFlashAttribute("error", ex.getMessage());
            redirectat.addFlashAttribute("salary", inputCaregiver.getSalary());
            redirectat.addFlashAttribute("quantity", inputCaregiver.getQuantity());
            redirectat.addFlashAttribute("workingHoursFrom", inputCaregiver.getWorkingHoursFrom());
            redirectat.addFlashAttribute("workingHoursTo", inputCaregiver.getWorkingHoursTo());
            return "redirect:/caregiver/create";
        }
    }
    
    @GetMapping("/list")
    public String findAll(Model model, @RequestParam(required = false) List<Caregiver> caregivers) {
        if (caregivers != null) {
            model.addAttribute("caregivers", caregivers);
        } else {
            model.addAttribute("caregivers", caregiverService.findAll());
        }
        return "caregiverList";
    }

    @PostMapping("/list")
        public String findByFilter(SearchCaregiverDTO searchCaregiver, RedirectAttributes rt) {
        if (searchCaregiver.getWorkingHoursTo() != null) {
            searchCaregiver.setHoursTo(searchCaregiver.getWorkingHoursTo());
        }
        if (searchCaregiver.getWorkingHoursFrom() != null) {
            searchCaregiver.setHoursFrom(searchCaregiver.getWorkingHoursFrom());
        }
        CaregiverCriteria caregiverCriteria = createCriteria(searchCaregiver);
        List<Caregiver> caregivers = caregiverService.findByCriteria(caregiverCriteria);

        if (searchCaregiver.getDay() != null && !searchCaregiver.getDay().isEmpty()) {
            List<Caregiver> car = new ArrayList<>();
            for (Caregiver caregiver : caregivers) {
                for (String day : caregiver.getDays()) {
                    if (day.equalsIgnoreCase(searchCaregiver.getDay())) {
                        car.add(caregiver);
                    }
                }
            }
            caregivers = car;
        }
        rt.addAttribute("caregivers", caregivers);
        return "redirect:/caregiver/list";
    }

    private CaregiverCriteria createCriteria(SearchCaregiverDTO searchCaregiver) {
        CaregiverCriteria caregiverCriteria = new CaregiverCriteria();
        if (searchCaregiver != null) {
            if (searchCaregiver.getQuantityFrom() != null || searchCaregiver.getQuantityTo() != null) {
                IntegerFilter filter = new IntegerFilter();
                if (searchCaregiver.getQuantityFrom() != null) {
                    filter.setGreaterThanOrEqual(searchCaregiver.getQuantityFrom());
                    caregiverCriteria.setQuantity(filter);
                }
                if (searchCaregiver.getQuantityTo() != null) {
                    filter.setLessThanOrEqual(searchCaregiver.getQuantityTo());
                    caregiverCriteria.setQuantity(filter);
                }
            }
            if (searchCaregiver.getAgeFrom() != null) {
                IntegerFilter filter = new IntegerFilter();
                filter.setGreaterThanOrEqual(searchCaregiver.getAgeFrom());
                caregiverCriteria.setAgeFrom(filter);
            }
            if (searchCaregiver.getAgeTo() != null) {
                IntegerFilter filter = new IntegerFilter();
                filter.setLessThanOrEqual(searchCaregiver.getAgeTo());
                caregiverCriteria.setAgeTo(filter);
            }
            if (!StringUtils.isBlank(searchCaregiver.getCooking())) {
                BooleanFilter filter = new BooleanFilter();
                switch (searchCaregiver.getCooking()) {
                    case "true":
                        filter.setEquals(true);
                        break;
                    case "false":
                        filter.setEquals(false);
                        break;
                    default:
                        filter.setEquals(false);
                }
                caregiverCriteria.setCooking(filter);
            }
            if (!StringUtils.isBlank(searchCaregiver.getCleaningPeople())) {
                BooleanFilter filter = new BooleanFilter();
                switch (searchCaregiver.getCleaningPeople()) {
                    case "true":
                        filter.setEquals(true);
                        break;
                    case "false":
                        filter.setEquals(false);
                        break;
                    default:
                        filter.setEquals(false);
                }
                caregiverCriteria.setCleaningPeople(filter);
            }
            if (!StringUtils.isBlank(searchCaregiver.getTranfering())) {
                BooleanFilter filter = new BooleanFilter();
                switch (searchCaregiver.getTranfering()) {
                    case "true":
                        filter.setEquals(true);
                        break;
                    case "false":
                        filter.setEquals(false);
                        break;
                    default:
                        filter.setEquals(false);
                }
                caregiverCriteria.setTranfering(filter);
            }
            if (searchCaregiver.getSalaryFrom() != null || searchCaregiver.getSalaryTo() != null) {
                IntegerFilter filter = new IntegerFilter();
                if (searchCaregiver.getSalaryFrom() != null) {
                    filter.setGreaterThanOrEqual(searchCaregiver.getSalaryFrom());
                    caregiverCriteria.setSalary(filter);
                }
                if (searchCaregiver.getSalaryTo() != null) {
                    filter.setLessThanOrEqual(searchCaregiver.getSalaryTo());
                    caregiverCriteria.setSalary(filter);
                }
            }
            if (!StringUtils.isBlank(searchCaregiver.getWorkingZone())) {
                StringFilter filter = new StringFilter();
                filter.setContains(searchCaregiver.getWorkingZone());
                caregiverCriteria.setWorkingZone(filter);
            }
            if (!StringUtils.isBlank(searchCaregiver.getDescription())) {
                StringFilter filter = new StringFilter();
                filter.setContains(searchCaregiver.getDescription());
                caregiverCriteria.setDescription(filter);
            }
            if (searchCaregiver.getWorkingHoursFrom() != null) {
                IntegerFilter filter = new IntegerFilter();
                filter.setLessThanOrEqual(searchCaregiver.getHoursFrom());
                caregiverCriteria.setHoursFrom(filter);
            }
            if (searchCaregiver.getWorkingHoursTo() != null) {
                IntegerFilter filter = new IntegerFilter();
                filter.setGreaterThanOrEqual(searchCaregiver.getHoursTo());
                caregiverCriteria.setHoursTo(filter);
            }
        }
        return caregiverCriteria;
    }

    @GetMapping("")
        public Caregiver findById(@RequestParam String id) throws Exception {
        return caregiverService.findById(id);
    }

    @PostMapping("/delete")
        public void delete(@RequestParam String id) throws Exception {
        caregiverService.delete(id);
    }

    @PostMapping("/update")
        public void update(@RequestParam String id, Caregiver newCaregiver) throws Exception {
        caregiverService.update(id, newCaregiver);
    }
}
