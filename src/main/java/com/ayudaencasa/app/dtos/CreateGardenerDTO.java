/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayudaencasa.app.dtos;

import java.time.LocalDate;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

//@Data
//@NoArgsConstructor
public class CreateGardenerDTO {
    
    private Double surface;
    
    private Boolean tools;
    
    private Boolean poolCleaning;
    
    private Boolean gardenFence;
    
    private Boolean plantDisinfection;
    
//    @NotNull
//    @NonNull
    private Integer salary;
    
    private String workingZone;
    
    private String description;
    
//    @NotNull
//    @NonNull
    private LocalDate dateFrom;
    
//    @NotNull
//    @NonNull
    private LocalDate dateTo;
    
    private Boolean status;

    public CreateGardenerDTO() {
    }

    public CreateGardenerDTO(Double surface, Boolean tools, Boolean poolCleaning, Boolean gardenFence, Boolean plantDisinfection, Integer salary, String workingZone, String description, LocalDate dateFrom, LocalDate dateTo, Boolean status) {
        this.surface = surface;
        this.tools = tools;
        this.poolCleaning = poolCleaning;
        this.gardenFence = gardenFence;
        this.plantDisinfection = plantDisinfection;
        this.salary = salary;
        this.workingZone = workingZone;
        this.description = description;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.status = status;
    }
    
    

    public Double getSurface() {
        return surface;
    }

    public void setSurface(Double surface) {
        this.surface = surface;
    }

    public Boolean getTools() {
        return tools;
    }

    public void setTools(Boolean tools) {
        this.tools = tools;
    }

    public Boolean getPoolCleaning() {
        return poolCleaning;
    }

    public void setPoolCleaning(Boolean poolCleaning) {
        this.poolCleaning = poolCleaning;
    }

    public Boolean getGardenFence() {
        return gardenFence;
    }

    public void setGardenFence(Boolean gardenFence) {
        this.gardenFence = gardenFence;
    }

    public Boolean getPlantDisinfection() {
        return plantDisinfection;
    }

    public void setPlantDisinfection(Boolean plantDisinfection) {
        this.plantDisinfection = plantDisinfection;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getWorkingZone() {
        return workingZone;
    }

    public void setWorkingZone(String workingZone) {
        this.workingZone = workingZone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    
    
}
