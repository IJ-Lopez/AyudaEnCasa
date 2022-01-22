package com.ayudaencasa.app.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE categories SET deleted_at = current_timestamp() WHERE id = ?")
@Where(clause = "deleted_at is null")
public class User implements Serializable {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
//    @JsonProperty("email")
    @NonNull

    @Column(unique = true)
    private String email;
    
//    @JsonProperty("password")
    @NonNull
    private String password;
    
//    @JsonProperty("firstName")
    @NonNull
    private String firstName;
    
//    @JsonProperty("lastName")
    @NonNull
    private String lastName;
    
//    @JsonProperty("dob")
//    @NonNull
   
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    
////    @JsonProperty("dni")
//    @NonNull
    private Integer dni;
    
//    @JsonProperty("address")
    private String address;
    
//    @JsonProperty("phone")
    private Integer phone;
    // private Photo photo;
    // private Cv cv;
    private HashSet<Job> jobs;
    
    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(value = TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "updated_at", insertable = false)
    private Date updatedAt;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "deleted_at")
    private Date deletedAt;
}
