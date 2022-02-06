package com.ayudaencasa.app.services.impl;

import com.ayudaencasa.app.dto.input.RegisterUserDTO;
import com.ayudaencasa.app.entities.Role;
import com.ayudaencasa.app.entities.User;
import com.ayudaencasa.app.exceptions.UserNotFoundException;
import com.ayudaencasa.app.repositories.RoleRepository;
import com.ayudaencasa.app.repositories.UserRepository;
import com.ayudaencasa.app.services.UserService;
import java.util.Arrays;
import java.util.Date;
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
    private RoleRepository roleRepo;
   
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    @Transactional
    public User create(@NonNull User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(roleRepo.findByName("ROLE_USER")));
        return userRepo.save(user);
    }
    
    @Override
    public void validated(RegisterUserDTO user) throws UserNotFoundException{
        if(user.getEmail() == null || user.getEmail().isEmpty()){
            throw new UserNotFoundException("El mail del usuario no puede estar vacío");
        }
        if(userRepo.findByEmail(user.getEmail()).isPresent()){
            throw new UserNotFoundException("El mail ingresado ya existe");
        }
        if(user.getFirstName() == null || user.getFirstName().isEmpty()){
            throw new UserNotFoundException("El nombre del usuario no puede estar vacío");
        }
        if(user.getLastName() == null || user.getLastName().isEmpty()){
            throw new UserNotFoundException("El apellido del usuario no puede estar vacío");
        }
        if(user.getDni() == null){
            throw new UserNotFoundException("El dni del usuario no puede ser nulo");
        }
        if(user.getPhone() == null){
            throw new UserNotFoundException("El teléfono del usuario no puede ser nulo");
        }
        if(user.getDob() == null){
            throw new UserNotFoundException("La fecha de nacimiento del usuario no puede ser nula");
        }
        if(user.getDepartament() == null || user.getDepartament().isEmpty()){
            throw new UserNotFoundException("El departamento no puede estar vacío");
        }
        
        if(user.getPassword() == null || user.getPassword2() == null || user.getPassword().isEmpty() || user.getPassword2().isEmpty()){
            throw new UserNotFoundException("La contraseña no puede estar vacía");
        }
        if(!user.getPassword().equals(user.getPassword2())){
            throw new UserNotFoundException("Las contraseñas deben ser iguales");
        }     
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
    public User findByEmail(@NonNull String email) {
        Optional<User> opt = userRepo.findByEmail(email);
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
