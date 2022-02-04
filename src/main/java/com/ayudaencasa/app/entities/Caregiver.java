package com.ayudaencasa.app.entities;

import java.io.Serializable;
import java.time.LocalTime;
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
public class Caregiver extends Job implements Serializable {
    
    public static final String JOB_TYPE = "Cuidado de Personas || Caregiver";
    
    private final String type = JOB_TYPE;
    
    private Integer quantity;
    
    private Integer ageFrom;
    
    private Integer ageTo;
    
    private Boolean cooking;
    
    private Boolean cleaningPeople;
    
    private Boolean tranfering;
    
    private String curriculim;
    
}
