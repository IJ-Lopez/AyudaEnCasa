package com.ayudaencasa.app.services.impl;

import com.ayudaencasa.app.entities.User;
import com.ayudaencasa.app.exceptions.UserNotFoundException;
import com.ayudaencasa.app.repositories.UserRepository;
import com.ayudaencasa.app.services.UserService;
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
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public User create(@NonNull User user) {
        return userRepo.save(user);
    }
    
//    @Override
//    @Transactional
//    public User create(String firstName, String lastName, Integer dni, String address, Integer phone, String mail, String password, String password2) throws UserNotFoundException {
//        User user = new User();
//        if(mail == null || mail.isEmpty()){
//            throw new UserNotFoundException("El mail del usuario no puede estar vacío");
//        }
//        if(firstName == null || firstName.isEmpty()){
//            throw new UserNotFoundException("El nombre del usuario no puede estar vacío");
//        }
//        if(lastName == null || lastName.isEmpty()){
//            throw new UserNotFoundException("El apellido del usuario no puede estar vacío");
//        }
////        if(dob == null){
////            throw new UserNotFoundException("La fecha de nacimiento no puede ser nula");
////        }
//        if(dni == null){
//            throw new UserNotFoundException("El dni no puede ser nulo");
//        }
//        if(phone == null){
//            throw new UserNotFoundException("El teléfono no puede ser nulo");
//        }
//        if(address == null || address.isEmpty()){
//            throw new UserNotFoundException("La direccion no puede estar vacía");
//        }
//        if(password == null || password2 == null || password.isEmpty() || password2.isEmpty()){
//            throw new UserNotFoundException("La contraseña no puede estar vacía");
//        }
//        if(!password.equals(password2)){
//            throw new UserNotFoundException("Las contraseñas deben ser iguales");
//        }
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        user.setDni(dni);
////        user.setDob(dob);
//        user.setEmail(mail);
//        user.setAddress(address);
//        user.setPhone(phone);
//        user.setPassword(encoder.encode(password));     
//        return userRepo.save(user);
//        
//    }

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
