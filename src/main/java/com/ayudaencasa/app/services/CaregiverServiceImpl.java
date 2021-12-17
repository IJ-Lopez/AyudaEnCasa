package com.ayudaencasa.app.services;

import com.ayudaencasa.app.entities.Caregiver;
import com.ayudaencasa.app.entities.User;
import com.ayudaencasa.app.exceptions.UserNotFoundException;
import com.ayudaencasa.app.interfaz.CaregiverService;
import com.ayudaencasa.app.repositories.CaregiverRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class CaregiverServiceImpl implements CaregiverService {

@Autowired
private CaregiverRepository caregiverReposotory;
@Autowired
private ModelMapper modelMapper;


  @Override
    @Transactional
    public Caregiver create(@NonNull Caregiver caregiver) {
        return (Caregiver) caregiverReposotory.save(caregiver);
    }
    
      @Override
    @Transactional
    public void update(@NonNull String id, @NonNull Caregiver newCaregiver) throws Exception {
        Optional<Caregiver> opt = caregiverReposotory.findById(id); 
        if(opt.isPresent()){
            Caregiver caregiver = opt.get();
            modelMapper.map(newCaregiver, caregiver);
            caregiverReposotory.save(caregiver);
        } else {
            throw new Exception();
        }
    }

    @Override
    @Transactional
    public void delete(@NonNull String id) throws Exception {
        Optional<Caregiver> opt = caregiverReposotory.findById(id);
        if(opt.isPresent()){
            caregiverReposotory.delete(opt.get());
        } else {
            throw new Exception();
        }
    }
    
    @Override
    public Caregiver findById(@NonNull String id) {
        Optional<Caregiver> opt = caregiverReposotory.findById(id);
        if(opt.isPresent()){
            return opt.get();
        } else {
            throw new UserNotFoundException();
        }
    }
    
     public List<Caregiver> findAll() {
        return caregiverReposotory.findAll();
    }

    

}
