
package com.ayudaencasa.app.entities;

import java.util.Objects;
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
public class Cleaning extends Job{
    
    private Integer rooms;
    private Boolean exteriors;
    private Boolean cooking;
    private Boolean laundry;
    private Boolean ironing;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.getType());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cleaning other = (Cleaning) obj;
        if (!Objects.equals(this.getType(), other.getType())) {
            return false;
        }
        return true;
    }
    @Override
    public String getType() {
        return "Cleaning";
    }

    
    
    
    
}
