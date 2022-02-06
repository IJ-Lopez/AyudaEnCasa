package com.ayudaencasa.app.services;

import com.ayudaencasa.app.criteria.CaregiverCriteria;
import com.ayudaencasa.app.dto.input.CreateCaregiverDTO;
import com.ayudaencasa.app.entities.Caregiver;
import com.ayudaencasa.app.exceptions.CaregiverNotFoundException;
import java.util.List;

public interface CaregiverService {

    public Caregiver create(Caregiver caregiver);
    public void validated(CreateCaregiverDTO caregiver) throws CaregiverNotFoundException;
    public void update(String id, Caregiver newCaregiver)throws CaregiverNotFoundException;
    public void delete(String id)throws CaregiverNotFoundException;
    public Caregiver findById(String id) throws CaregiverNotFoundException;
    public List<Caregiver> findAll();
    public List<Caregiver> findByCriteria(CaregiverCriteria caregiverCriteria);
    
 
  
}