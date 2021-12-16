package com.ayudaencasa.app.interfaz;

import com.ayudaencasa.app.entities.Caregiver;
import java.util.List;

public interface CaregiverInter {

public Caregiver create();
    public void update();
    public void delete();
    public List<Caregiver> findAll();
    
    public Caregiver create(Caregiver caregiver);
    public void update(String id, Caregiver caregiver) throws Exception;
    public void delete(Caregiver caregiver) throws Exception;
  
}