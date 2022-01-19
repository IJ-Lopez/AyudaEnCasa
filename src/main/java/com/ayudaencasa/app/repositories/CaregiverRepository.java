package com.ayudaencasa.app.repositories;

import com.ayudaencasa.app.entities.Caregiver;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface CaregiverRepository extends JobRepository<Caregiver> {

  
}
