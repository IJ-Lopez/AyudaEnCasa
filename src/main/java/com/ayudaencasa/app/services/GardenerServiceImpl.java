/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayudaencasa.app.services;

import com.ayudaencasa.app.entities.Gardener;
import com.ayudaencasa.app.repositories.GardenerRepository;
import com.ayudaencasa.app.repositories.JobRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usr
 */
@Service
public class GardenerServiceImpl {
    
    @Autowired
    private GardenerRepository gardenerRepository;
    
    
    
}
