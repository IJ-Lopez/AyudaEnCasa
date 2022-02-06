package com.ayudaencasa.app.entities;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE job SET deleted_at = current_timestamp() WHERE id = ?")
@Where(clause = "deleted_at is null")
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Job implements Serializable {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    protected String id;
    protected Integer salary;
    protected String workingZone;  
    protected String description;
    
    @Setter(AccessLevel.NONE)
    protected String type;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="USER_ID", referencedColumnName = "ID")
    protected User user;
    
    @ElementCollection(targetClass=String.class)
    protected List<String> days;
    protected Integer hoursFrom;
    protected Integer hoursTo;
    protected Boolean status;  
    protected String cv;
    
    protected Boolean showContactInfo;
    
    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "created_at")
    protected Date createdAt;

    @Temporal(value = TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "updated_at", insertable = false)
    protected Date updatedAt;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "deleted_at")
    protected Date deletedAt;
    
    public void setHoursFrom(LocalTime lt){
        hoursFrom = lt.toSecondOfDay();
    }

    public void setHoursTo(LocalTime lt){
        hoursTo = lt.toSecondOfDay();
    }
    
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
        final Job other = (Job) obj;
        if (!Objects.equals(this.getType(), other.getType())) {
            return false;
        }
        return true;
    }

}
