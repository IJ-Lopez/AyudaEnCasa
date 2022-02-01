package com.ayudaencasa.app.services.impl;

import com.ayudaencasa.app.entities.Job;
import com.ayudaencasa.app.repositories.JobBeanRepository;
import com.ayudaencasa.app.services.JobBeanService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobBeanServiceImpl implements JobBeanService{
    
    @Autowired
    private JobBeanRepository jobRepo;

    @Override
    public List<Job> findAll() {
        return jobRepo.findAll();
    }

    @Override
    public List<Job> findByType(String type) {
        return jobRepo.findByTypeIgnoreCaseStartsWith(type);
    }

    @Override
    public Class getTypeIfExist(String type) {
        List<Job> jobs = jobRepo.findByTypeIgnoreCaseStartsWith(type);
        return jobs.isEmpty() ? null : jobs.get(0).getClass();
    }
    
}
