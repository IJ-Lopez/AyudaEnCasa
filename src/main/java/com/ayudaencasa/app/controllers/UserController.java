
package com.ayudaencasa.app.controllers;

//import org.springframework.stereotype.Controller;
import com.ayudaencasa.app.entities.User;
import com.ayudaencasa.app.exceptions.UserNotFoundException;
import com.ayudaencasa.app.services.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/User")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/form")
    @ResponseStatus(HttpStatus.OK)
    public User create(@Valid @RequestBody User inputUser) {
        User user = new User();
        BeanUtils.copyProperties(inputUser, user);
        return userService.create(user);
    }
    
    @PostMapping("/")
    public void update(String id, User newUser) {
        userService.update(id, newUser);
    }
    
    @PostMapping
    public void delete(String id) throws UserNotFoundException {
       userService.delete(id);
    }
    
    @GetMapping("/viewUser")
    public List<User> findAll() {
        return userService.findAll();
    }
    
    @GetMapping("/{id}")
    public User findById(String id) throws UserNotFoundException {
        return userService.findById(id);
    }
    
    
    
}
