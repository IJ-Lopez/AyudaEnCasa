package com.ayudaencasa.app.services;

import com.ayudaencasa.app.entities.User;
import java.util.List;

public interface UserService {
    
    public User create();
    public void update();
    public void delete();
    public User findById();
    public List<User> findAll();
    
}
