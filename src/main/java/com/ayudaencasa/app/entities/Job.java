package com.ayudaencasa.app.entities;


import com.ayudaencasa.app.enums.Day;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;


import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
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
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE categories SET deleted_at = current_timestamp() WHERE id = ?")
@Where(clause = "deleted_at is null")
@MappedSuperclass
public abstract class Job {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    protected String id;
    
    protected Integer salary;
    
    protected String workingZone;
    
    protected String description;
    
//    @Enumerated(EnumType.STRING)

    @Column
    @ElementCollection(targetClass=String.class)
    protected List<String> days;
    
    
//    @Temporal(value = TemporalType.TIME)
//    @Column
//    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
//    @JsonDeserialize(using = LocalTimeDeserializer.class)
//    protected LocalTime workingHoursFrom;
    
//    @Setter(AccessLevel.NONE)
    protected Integer hoursFrom;
    
//    @Temporal(value = TemporalType.TIME)
//    @Column
//    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
//    @JsonDeserialize(using = LocalTimeDeserializer.class)
//    protected LocalTime workingHoursTo;
    
    protected Integer hoursTo;
   
    
//    @Temporal(TemporalType.TIMESTAMP)
//    protected LocalDate dateFrom;
    
//    @Temporal(TemporalType.TIMESTAMP)
//    protected LocalDate dateTo;
    
    protected Boolean status;
    
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
    
    public abstract String getType();

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
