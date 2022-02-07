package com.ayudaencasa.app.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

//@NoArgsConstructor
@ToString
@AllArgsConstructor
@Setter
@Getter
@SQLDelete(sql = "UPDATE other SET deleted_at = current_timestamp() WHERE id = ?")
@Where(clause = "deleted_at is null")
@Entity
public class Other extends Job implements Serializable {
//    
//    @NonNull
//    private String type;
//    
}
