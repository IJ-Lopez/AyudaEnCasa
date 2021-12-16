
package com.ayudaencasa.app.services;

import com.ayudaencasa.app.entities.PetWalker;
import com.ayudaencasa.app.repositories.PetWalkerRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetWalkerServiceImpl implements PetWalkerService{
    
    @Autowired
    private PetWalkerRepository petWalkerRepo;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    @Transactional
    public PetWalker create (@NonNull PetWalker petWalker){
        return petWalkerRepo.save(petWalker);
    }
    
    
    @Override
    public List <PetWalker> findAll(){
        return petWalkerRepo.findAll();
    }
    
}
