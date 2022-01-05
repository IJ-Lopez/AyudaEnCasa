package com.ayudaencasa.app.criteria;

import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LocalDateFilter;
import io.github.jhipster.service.filter.StringFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//Filtros de búsqueda
public class GardenerCriteria {
    
    private DoubleFilter surface;
    private BooleanFilter tools;
    private BooleanFilter poolCleaning;
    private BooleanFilter gardenFence;
    private BooleanFilter plantDisinfection;
    
    private IntegerFilter salary;
    private StringFilter workingZone;
    private StringFilter description;
    private LocalDateFilter dateFrom;
    private LocalDateFilter dateTo;

    public DoubleFilter getSurface() {
        return surface;
    }

    public void setSurface(DoubleFilter surface) {
        this.surface = surface;
    }

    public BooleanFilter getTools() {
        return tools;
    }

    public void setTools(BooleanFilter tools) {
        this.tools = tools;
    }

    public BooleanFilter getPoolCleaning() {
        return poolCleaning;
    }

    public void setPoolCleaning(BooleanFilter poolCleaning) {
        this.poolCleaning = poolCleaning;
    }

    public BooleanFilter getGardenFence() {
        return gardenFence;
    }

    public void setGardenFence(BooleanFilter gardenFence) {
        this.gardenFence = gardenFence;
    }

    public BooleanFilter getPlantDisinfection() {
        return plantDisinfection;
    }

    public void setPlantDisinfection(BooleanFilter plantDisinfection) {
        this.plantDisinfection = plantDisinfection;
    }

    public IntegerFilter getSalary() {
        return salary;
    }

    public void setSalary(IntegerFilter salary) {
        this.salary = salary;
    }

    public StringFilter getWorkingZone() {
        return workingZone;
    }

    public void setWorkingZone(StringFilter workingZone) {
        this.workingZone = workingZone;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public LocalDateFilter getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDateFilter dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDateFilter getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDateFilter dateTo) {
        this.dateTo = dateTo;
    }

    
    
   
}
