package com.ayudaencasa.app.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

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

    @Column(unique = true)
    private String email;
    
    private String password;
    
    private String firstName;
 
    private String lastName;
   
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    
    private Integer dni;
    
    private String address;
    
    private Long phone;
    private String photo;
    // private Cv cv;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private Set<Job> jobs = new HashSet<>();
    
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
