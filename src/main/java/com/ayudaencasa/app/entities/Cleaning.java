package com.ayudaencasa.app.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@SQLDelete(sql = "UPDATE categories SET deleted_at = current_timestamp() WHERE id = ?")
@Where(clause = "deleted_at is null")
@Entity
public class Cleaning extends Job implements Serializable{
    
    private final String type = "Limpiador || Cleaning";
    private Integer rooms;
    private String exteriors;
    private Boolean cooking;
    private Boolean laundry;
    private Boolean ironing;

}
