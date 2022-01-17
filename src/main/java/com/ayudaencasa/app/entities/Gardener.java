package com.ayudaencasa.app.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.validation.constraints.Min;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE categories SET deleted_at = current_timestamp() WHERE id = ?")
@Where(clause = "deleted_at is null")
@Entity
public class Gardener extends Job implements Serializable{
    
    @Min(0)
    private Double surface;
    
    private Boolean tools;
    
    private Boolean poolCleaning;
    
    private Boolean gardenFence;
    
    private Boolean plantDisinfection;

//    public Gardener() {
//    }

//    
//
//    public Gardener(Double surface, Boolean tools, Boolean poolCleaning, Boolean gardenFence, Boolean plantDisinfection, String id, Integer salary, String workingZone, String description, LocalDate dateFrom, LocalDate dateTo, Boolean status, Date createdAt, Date updatedAt, Date deletedAt) {
//        super(id, salary, workingZone, description, dateFrom, dateTo, status, createdAt, updatedAt, deletedAt);
//        this.surface = surface;
//        this.tools = tools;
//        this.poolCleaning = poolCleaning;
//        this.gardenFence = gardenFence;
//        this.plantDisinfection = plantDisinfection;
//    }
//
//    public Double getSurface() {
//        return surface;
//    }
//
//    public void setSurface(Double surface) {
//        this.surface = surface;
//    }
//
//    public Boolean getTools() {
//        return tools;
//    }
//
//    public void setTools(Boolean tools) {
//        this.tools = tools;
//    }
//
//    public Boolean getPoolCleaning() {
//        return poolCleaning;
//    }
//
//    public void setPoolCleaning(Boolean poolCleaning) {
//        this.poolCleaning = poolCleaning;
//    }
//
//    public Boolean getGardenFence() {
//        return gardenFence;
//    }
//
//    public void setGardenFence(Boolean gardenFence) {
//        this.gardenFence = gardenFence;
//    }
//
//    public Boolean getPlantDisinfection() {
//        return plantDisinfection;
//    }
//
//    public void setPlantDisinfection(Boolean plantDisinfection) {
//        this.plantDisinfection = plantDisinfection;
//    }
//
//    
//    
    

    @Override
    public String getType() {
        return "Gardener";
    }
    
    public Boolean hasTools(){
        return tools;
    }
    
    
    
    
    
    
    
    
}
