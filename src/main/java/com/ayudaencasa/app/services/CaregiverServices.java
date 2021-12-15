package com.ayudaencasa.app.services;

import com.ayudaencasa.app.entities.Caregiver;
import com.ayudaencasa.app.repositories.CaregiverRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

public class CaregiverServices {

@Autowired
CaregiverRepository caregiverReposotory;

@Transactional
public void SaveCaregiver (String name){
    Caregiver caregiver = new Caregiver();
    caregiverReposotory.save(caregiver);
}

public void Buscar (String name, String id) {
    
     Optional<Caregiver> respuesta = caregiverReposotory.findById(id);
    if(respuesta.isPresent()) {
Caregiver caregiver = respuesta.get();
} 
    
}

}
