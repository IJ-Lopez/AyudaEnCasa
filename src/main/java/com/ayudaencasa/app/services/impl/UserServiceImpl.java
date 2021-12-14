package com.ayudaencasa.app.services.impl;

import com.ayudaencasa.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    
    @Autowired
    private UserRepository userRepo;
}
