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
@SQLDelete(sql = "UPDATE cleaning SET deleted_at = current_timestamp() WHERE id = ?")
@Where(clause = "deleted_at is null")
@Entity
public class Cleaning extends Job implements Serializable{
    
    public static final String JOB_TYPE = "Servicio de Limpieza || Cleaning";
    private final String type = JOB_TYPE;
    private Integer rooms;
    private String exteriors;
    private Boolean cooking;
    private Boolean laundry;
    private Boolean ironing;

}
