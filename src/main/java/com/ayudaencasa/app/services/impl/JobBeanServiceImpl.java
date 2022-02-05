package com.ayudaencasa.app.services.impl;

import com.ayudaencasa.app.entities.Job;
import com.ayudaencasa.app.repositories.JobBeanRepository;
import com.ayudaencasa.app.services.JobBeanService;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
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
    public Job findById(String id){
        Optional<Job> opt = jobRepo.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new RuntimeException("User not found");
    }

    @Override
    public List<Job> findByType(String type) {
        return jobRepo.findByTypeIgnoreCaseStartsWith(type);
    }

    @Override
    public List<Job> findByUserEmail(String email) {
        return jobRepo.findByUser_Email(email);
    }
    
    @Override
    public List<Job> findByUserId(String id){
        return jobRepo.findByUser_Id(id);
    }
    
    @Override
    @Transactional
    public void delete(String id){
        jobRepo.deleteById(id);
    }

    @Override
    public Class getTypeIfExist(String type) {
        List<Job> jobs = jobRepo.findByTypeIgnoreCaseStartsWith(type);
        return jobs.isEmpty() ? null : jobs.get(0).getClass();
    }
    
}
