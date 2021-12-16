package com.ayudaencasa.app.services;

import com.ayudaencasa.app.entities.Caregiver;
import com.ayudaencasa.app.repositories.CaregiverRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

public class CaregiverServices {

@Autowired
CaregiverRepository caregiverReposotory;

//@Transactional
//public void SaveCaregiver (String name){
//    Caregiver caregiver = new Caregiver();
//    caregiverReposotory.save(caregiver);
//}

//public void Buscar (String name, String id) {
    
  //   Optional<Caregiver> respuesta = caregiverReposotory.findById(id);
  //  if(respuesta.isPresent()) {
//Caregiver caregiver = respuesta.get();
//} 
    
//}


    @Transactional
    public Caregiver create(@NonNull Caregiver caregiver) {
        return (Caregiver) caregiverReposotory.save(caregiver);
    }
    
    @Transactional
    public void update(String id, @NonNull Caregiver newCaregiver) throws Exception {
        Optional<Caregiver> opt = caregiverReposotory.findById(id); 
        if(opt.isPresent()){
            Caregiver caregiver = opt.get();
            modelMapper.map(newCaregiver, caregiver);
            caregiverReposotory.save(caregiver);
        } else {
            throw new Exception();
        }
    }

@Transactional
    public void delete(String id, @NonNull Caregiver caregiver) throws Exception {
        Optional<Caregiver> opt = caregiverReposotory.findById(id);
        if(opt.isPresent()){
            caregiverReposotory.delete(opt.get());
        } else {
            throw new Exception();
        }
    }
    
     public List<Caregiver> findAll() {
        return caregiverReposotory.findAll();
    }
    
}
