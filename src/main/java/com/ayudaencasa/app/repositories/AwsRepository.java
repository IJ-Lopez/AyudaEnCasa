package com.ayudaencasa.app.repositories;


import com.ayudaencasa.app.entities.Job;
import org.springframework.stereotype.Repository;

@Repository
public interface AwsRepository extends JobRepository<Job> {
    
}
