package com.ayudaencasa.app.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@SQLDelete(sql = "UPDATE categories SET deleted_at = current_timestamp() WHERE id = ?")
@Where(clause = "deleted_at is null")
@Entity
public class Gardener extends Job implements Serializable{
    
    public static final String JOB_TYPE = "Jardinero || Gardener";
    private final String type = JOB_TYPE;
    private String surface;
    private Boolean tools;
    private Boolean poolCleaning;
    private Boolean gardenFence;    
    private Boolean plantDisinfection;
    
    public Boolean hasTools(){
        return tools;
    }    
    
}
