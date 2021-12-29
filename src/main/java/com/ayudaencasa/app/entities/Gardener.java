/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayudaencasa.app.entities;

import java.util.List;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Entity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE categories SET deleted_at = current_timestamp() WHERE id = ?")
@Where(clause = "deleted_at is null")
@Entity
public class Gardener extends Job {
    @Min(0)
    private Double surface;
    
    private Boolean tools;
    
    private Boolean poolCleaning;
    
    private Boolean gardenFence;
    
    private Boolean plantDisinfection;

    @Override
    public String getType() {
        return "Gardener";
    }
    
    public Boolean hasTools(){
        return tools;
    }
    
    
    
    
    
    
    
    
}
