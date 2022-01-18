
package com.ayudaencasa.app.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
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
    
    private Integer quantity;
    
    private Integer ageFrom;
    
    private Integer ageTo;
    
    private Boolean cooking;
    
    private Boolean cleaningPeople;
    
    private Boolean tranfering;
    
    
    @Override
    public String getType() {
        return "Caregiver";
    }
    
}
