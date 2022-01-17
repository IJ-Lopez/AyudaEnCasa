
package com.ayudaencasa.app.services;

import com.ayudaencasa.app.entities.PetWalker;
import com.ayudaencasa.app.exceptions.PetWalkerNotFoundException;
import com.ayudaencasa.app.repositories.PetWalkerRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author martina
 */
@Service
public class PetWalkerServiceImpl implements PetWalkerService{
    
//    @Autowired
//    private PetWalkerRepository petWalkerRepo;
//    
//    @Autowired
//    private ModelMapper modelMapper;
//    
//    @Override
//    @Transactional
//    public PetWalker create (@NonNull PetWalker petWalker){
//        return petWalkerRepo.save(petWalker);
//    }
//    
//    @Override
//    @Transactional
//   public void update (@NonNull String id, @NonNull PetWalker newPetWalker) throws PetWalkerNotFoundException{
//   Optional <PetWalker> opt = petWalkerRepo.findById(id);
//        if (opt.isPresent()) {
//            PetWalker petWalker = opt.get();
//            modelMapper.map (newPetWalker, petWalker);
//            petWalkerRepo.save(petWalker);
//        } else{
//            throw new PetWalkerNotFoundException();
//        }
//   }
//    
//      @Override
//    @Transactional
//    public void delete (@NonNull String id){
//        Optional <PetWalker> opt = petWalkerRepo.findById(id);
//          if (opt.isPresent()) {
//          petWalkerRepo.delete(opt.get());
//          } else {
//              throw new PetWalkerNotFoundException();
//          }
//    }
//    
//    @Override
//    public PetWalker findById (@NonNull String id){
//        Optional <PetWalker> opt = petWalkerRepo.findById(id);
//        if (opt.isPresent()){
//            return opt.get();
//        } else {
//              throw new PetWalkerNotFoundException();
//          }
//    }
//    
//    @Override
//    public List <PetWalker> findAll(){
//        return petWalkerRepo.findAll();
//    }
    
}
