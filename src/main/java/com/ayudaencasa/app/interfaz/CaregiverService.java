package com.ayudaencasa.app.interfaz;

import com.ayudaencasa.app.entities.Caregiver;
import com.ayudaencasa.app.entities.User;
import com.ayudaencasa.app.exceptions.UserNotFoundException;
import java.util.List;

public interface CaregiverService {

public Caregiver create(Caregiver caregiver);
    public void update(String id, Caregiver newCaregiver)throws Exception;
    public void delete(String id)throws Exception;
     public Caregiver findById(String id) throws Exception;
    public List<Caregiver> findAll();
    
 
  
}