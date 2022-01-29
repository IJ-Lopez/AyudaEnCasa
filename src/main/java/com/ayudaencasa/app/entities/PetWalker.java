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
@Getter
@Setter
@SQLDelete(sql = "UPDATE categories SET deleted_at = current_timestamp() WHERE id = ?")
@Where(clause = "deleted_at is null")
@Entity
public class PetWalker extends Job implements Serializable{
    
    private String type = "Paseador de Perros || Petwalker";
    
    private Integer petQuantity;
    
    private String petType;
    
    private String petRace;

    @Override
    public String getType() {
        return "PetWalker";
    }
    
}
