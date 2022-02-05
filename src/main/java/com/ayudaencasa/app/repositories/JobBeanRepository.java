package com.ayudaencasa.app.repositories;

import com.ayudaencasa.app.entities.Job;
import java.util.List;

public interface JobBeanRepository extends JobRepository<Job>{
    
    List<Job> findByTypeIgnoreCaseStartsWith(String type);
    List<Job> findByUser_Id(String id);
    List<Job> findByUser_Email(String email);
    
}
