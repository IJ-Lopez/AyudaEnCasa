
package com.ayudaencasa.app.controllers;

import com.ayudaencasa.app.criteria.GardenerCriteria;
import com.ayudaencasa.app.dto.input.CreateGardenerDTO;
import com.ayudaencasa.app.dto.input.SearchGardenerDTO;
import com.ayudaencasa.app.entities.Gardener;
import com.ayudaencasa.app.exceptions.GardenerNotFoundException;
import com.ayudaencasa.app.services.GardenerService;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.StringFilter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Validated
@RequestMapping("/gardener")
public class GardenerController {

    @Autowired
    private GardenerService gardenerService;

    @GetMapping("/create")
    public String registry() {
        return "newGardenerForm";
    }

    @PostMapping("/create")
    public String create(Model model, CreateGardenerDTO inputGardener) {
        try {
            Gardener gardener = new Gardener();
            if (inputGardener.getWorkingHoursTo() != null) {
                gardener.setHoursTo(inputGardener.getWorkingHoursTo());
            }
            if (inputGardener.getWorkingHoursFrom() != null) {
                gardener.setHoursFrom(inputGardener.getWorkingHoursFrom());
            }
            BeanUtils.copyProperties(inputGardener, gardener);
            gardenerService.create(gardener);
            return "redirect:/home";
        } catch (GardenerNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
            return "newGardenerForm";
        }
    }

    @GetMapping("/list")
    public String findAll(Model model, @RequestParam(required = false) List<Gardener> gardeners) {
        if(gardeners != null){
        model.addAttribute("gardeners", gardeners);
        } else {
        model.addAttribute("gardeners", gardenerService.findAll());
        }
        return "gardenerList";
    }
    
    @PostMapping("/list")
    public String findByFilter(SearchGardenerDTO searchGardener, RedirectAttributes rt) {
        if(searchGardener.getWorkingHoursTo() != null){
            searchGardener.setHoursTo(searchGardener.getWorkingHoursTo());
        }
        if (searchGardener.getWorkingHoursFrom() != null) {
            searchGardener.setHoursFrom(searchGardener.getWorkingHoursFrom());
        }
        GardenerCriteria gardenerCriteria = createCriteria(searchGardener);
        List<Gardener> gardeners = gardenerService.findByCriteria(gardenerCriteria);
        
        if(searchGardener.getDay() != null && !searchGardener.getDay().isEmpty()) {
            List<Gardener> gar = new ArrayList<>();
            for (Gardener gardener : gardeners) {
                for (String day : gardener.getDays()) {
                    if (day.equalsIgnoreCase(searchGardener.getDay())) {
                        gar.add(gardener);
                    }
                }
            }
            gardeners = gar;
        }  
        rt.addAttribute("gardeners", gardeners);
        return "redirect:/jardinero/list";
    }

    private GardenerCriteria createCriteria(SearchGardenerDTO searchGardener) {
        GardenerCriteria gardenerCriteria = new GardenerCriteria();
        if (searchGardener != null) {
            if (searchGardener.getSurfaceFrom() != null || searchGardener.getSurfaceTo() != null) {
                DoubleFilter filter = new DoubleFilter();
                if (searchGardener.getSurfaceFrom() != null) {
                    filter.setGreaterThanOrEqual(searchGardener.getSurfaceFrom());
                    gardenerCriteria.setSurface(filter);
                }
                if (searchGardener.getSurfaceTo() != null) {
                    filter.setLessThanOrEqual(searchGardener.getSurfaceTo());
                    gardenerCriteria.setSurface(filter);
                }
            }
            if (!StringUtils.isBlank(searchGardener.getTools())) {
                BooleanFilter filter = new BooleanFilter();
                switch (searchGardener.getTools()) {
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
            if (!StringUtils.isBlank(searchGardener.getPoolCleaning())) {
                BooleanFilter filter = new BooleanFilter();
                switch (searchGardener.getPoolCleaning()) {
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
            if (!StringUtils.isBlank(searchGardener.getGardenFence())) {
                BooleanFilter filter = new BooleanFilter();
                switch (searchGardener.getGardenFence()) {
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
            if (!StringUtils.isBlank(searchGardener.getPlantDisinfection())) {
                BooleanFilter filter = new BooleanFilter();
                switch (searchGardener.getPlantDisinfection()) {
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
            if (searchGardener.getSalaryFrom() != null || searchGardener.getSalaryTo() != null) {
                IntegerFilter filter = new IntegerFilter();
                if (searchGardener.getSalaryFrom() != null) {
                    filter.setGreaterThanOrEqual(searchGardener.getSalaryFrom());
                    gardenerCriteria.setSalary(filter);
                }
                if (searchGardener.getSalaryTo() != null) {
                    filter.setLessThanOrEqual(searchGardener.getSalaryTo());
                    gardenerCriteria.setSalary(filter);
                }
            }
            if (!StringUtils.isBlank(searchGardener.getWorkingZone())) {
                StringFilter filter = new StringFilter();
                filter.setContains(searchGardener.getWorkingZone());
                gardenerCriteria.setWorkingZone(filter);
            }
            if (!StringUtils.isBlank(searchGardener.getDescription())) {
                StringFilter filter = new StringFilter();
                filter.setContains(searchGardener.getDescription());
                gardenerCriteria.setDescription(filter);
            }
            if (searchGardener.getWorkingHoursFrom() != null) {
                IntegerFilter filter = new IntegerFilter();
                filter.setLessThanOrEqual(searchGardener.getHoursFrom());
                gardenerCriteria.setHoursFrom(filter);
            }
            if (searchGardener.getWorkingHoursTo() != null) {
                IntegerFilter filter = new IntegerFilter();
                filter.setGreaterThanOrEqual(searchGardener.getHoursTo());
                gardenerCriteria.setHoursTo(filter);
            }
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
