/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayudaencasa.app.services;

import com.ayudaencasa.app.entities.Other;
import com.ayudaencasa.app.exceptions.OtherNotFoundException;
import com.ayudaencasa.app.repositories.OtherRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Usr
 */
public class OtherServiceImpl implements OtherService {
    
    @Autowired
    private OtherRepository otherRepository;
    
    
    @Override
    @Transactional
    public Other create(@NonNull Other other) {
        return otherRepository.save(other);
    }

    @Override
    @Transactional
    public void update(@NonNull String id, @NonNull Other newOther) throws OtherNotFoundException {
        Optional<Other> opt = otherRepository.findById(id);
        if(opt.isPresent()){
            Other other = opt.get();
            modelMapper.map(newOther, other);
            otherRepository.save(other);
        } else {
            throw new OtherNotFoundException();
        }
    }

    @Override
    @Transactional
    public void delete(@NonNull String id) {
        Optional<Other> opt = otherRepository.findById(id);
        if(opt.isPresent()){
            otherRepository.delete(opt.get());
        } else {
            throw new OtherNotFoundException();
        }
    }

    @Override
    public Other findById(@NonNull String id) {
        Optional<Other> opt = otherRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        } else {
            throw new OtherNotFoundException();
        }
    }

    @Override
    public List<Other> findAll() {
        return otherRepository.findAll();
    }
    
    
}
