
package com.ayudaencasa.app.services.impl;

import com.ayudaencasa.app.entities.Cleaning;
import com.ayudaencasa.app.repositories.CleaningRepository;
import com.ayudaencasa.app.services.CleaningService;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CleaningServiceImpl implements CleaningService {
    
    @Autowired
    private CleaningRepository cleaningRepository;
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    @Transactional
    public Cleaning create(@NonNull Cleaning cleaning) {
        return cleaningRepository.save(cleaning);
    }

    @Override
    public void update(@NonNull String id, @NonNull Cleaning newCleaning) {
        Optional<Cleaning> opt= cleaningRepository.findById(id);
        if(opt.isPresent()){
            Cleaning cleaning = opt.get();
            modelMapper.map(newCleaning, cleaning);
            cleaningRepository.save(cleaning);
        }else {
            System.out.println("error");
        }
        
    }

    @Override
    public void delete(@NonNull String id) throws Exception {
        Optional<Cleaning> opt= cleaningRepository.findById(id);
        if(opt.isPresent()){
            cleaningRepository.delete(opt.get());
        }else {
            System.out.println("error");
        }
    }

    @Override
    public Cleaning findById(@NonNull String id) throws Exception {
        Optional<Cleaning> opt= cleaningRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }else {
            System.out.println("error");
        }
        return cleaningRepository.getById(id);
    }

    @Override
    public List<Cleaning> findAll() {
        return cleaningRepository.findAll();
    }
    
}
