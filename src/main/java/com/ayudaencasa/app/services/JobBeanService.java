package com.ayudaencasa.app.services;

import com.ayudaencasa.app.entities.Job;
import java.util.List;

public interface JobBeanService {
    
    List<Job> findAll();
    List<Job> findByType(String type);
    Class getTypeIfExist(String type);
    
}
