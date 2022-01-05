package com.ayudaencasa.app.dtos;

import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
public class SearchGardenerDTO {
    
    private Double surfaceFrom;
    private Double surfaceTo; 
    private String tools;
    private String poolCleaning;
    private String gardenFence; 
    private String plantDisinfection;
    private Integer salaryFrom;
    private Integer salaryTo;
    private String workingZone;
    private String description;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    

    public Double getSurfaceFrom() {
        return surfaceFrom;
    }

    public void setSurfaceFrom(Double surfaceFrom) {
        this.surfaceFrom = surfaceFrom;
    }

    public Double getSurfaceTo() {
        return surfaceTo;
    }

    public void setSurfaceTo(Double surfaceTo) {
        this.surfaceTo = surfaceTo;
    }

    public String getTools() {
        return tools;
    }

    public void setTools(String tools) {
        this.tools = tools;
    }

    public String getPoolCleaning() {
        return poolCleaning;
    }

    public void setPoolCleaning(String poolCleaning) {
        this.poolCleaning = poolCleaning;
    }

    public String getGardenFence() {
        return gardenFence;
    }

    public void setGardenFence(String gardenFence) {
        this.gardenFence = gardenFence;
    }

    public String getPlantDisinfection() {
        return plantDisinfection;
    }

    public void setPlantDisinfection(String plantDisinfection) {
        this.plantDisinfection = plantDisinfection;
    }

    public Integer getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(Integer salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public Integer getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(Integer salaryTo) {
        this.salaryTo = salaryTo;
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
    
    
    
}
