package com.ayudaencasa.app.services;

import com.ayudaencasa.app.entities.Job;
import java.util.List;

public interface JobBeanService {
    
    Job findById(String id);
    List<Job> findAll();
    List<Job> findByType(String type);
    List<Job> findByUserId(String id);
    List<Job> findByUserEmail(String id);
    Class getTypeIfExist(String type);
    void delete(String id);
}
