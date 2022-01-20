package com.ayudaencasa.app.services.impl;

import RolesSeguridad.Role;
import com.ayudaencasa.app.entities.User;
import com.ayudaencasa.app.exceptions.UserNotFoundException;
import com.ayudaencasa.app.repositories.UserRepository;
import com.ayudaencasa.app.services.UserService;
//import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    @Transactional
    public User create(@NonNull User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        //user.setRoles(Arrays.asList(adminRole));
        return userRepo.save(user);
    }

    @Override
    @Transactional
    public void update(@NonNull String id, @NonNull User newUser) throws UserNotFoundException {
        Optional<User> opt = userRepo.findById(id);
        if(opt.isPresent()){
            User user = opt.get();
            modelMapper.map(newUser, user);
            userRepo.save(user);
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    @Transactional
    public void delete(@NonNull String id) {
        Optional<User> opt = userRepo.findById(id);
        if(opt.isPresent()){
            userRepo.delete(opt.get());
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public User findById(@NonNull String id) {
        Optional<User> opt = userRepo.findById(id);
        if(opt.isPresent()){
            return opt.get();
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

        
    
}
