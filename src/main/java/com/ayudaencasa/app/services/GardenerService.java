/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayudaencasa.app.services;

import com.ayudaencasa.app.entities.Gardener;
import com.ayudaencasa.app.repositories.GardenerRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Usr
 */
public class GardenerService {
    
    @Autowired
    private GardenerRepository gardenerRepository;
    
    @Transactional
    public Gardener saveGardener() {
        Gardener gardener = new Gardener(); 
        return gardenerRepository.save(gardener);
    }
    
    public List<Gardener> gardenerList(){
        return gardenerRepository.findAll();
    }
    
}
