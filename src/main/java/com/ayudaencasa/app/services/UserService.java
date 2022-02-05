package com.ayudaencasa.app.services;

import com.ayudaencasa.app.dto.input.RegisterUserDTO;
import com.ayudaencasa.app.entities.User;
import com.ayudaencasa.app.exceptions.UserNotFoundException;
import java.util.Date;
import java.util.List;

public interface UserService {

    public User create(User user);
    
    public boolean validated(RegisterUserDTO user) throws UserNotFoundException;
    
    //public User create(String firstName, String lastName, Integer dni, String address, Integer phone, String mail, String password, String password2);

    public void update(String id, User user) throws UserNotFoundException;

    public void delete(String id) throws UserNotFoundException;

    public User findById(String id) throws UserNotFoundException;

    public User findByEmail(String email) throws UserNotFoundException;
    
    public List<User> findAll();

}
