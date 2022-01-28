
package com.ayudaencasa.app.controllers;

import com.ayudaencasa.app.entities.User;
import com.ayudaencasa.app.exceptions.UserNotFoundException;
import com.ayudaencasa.app.services.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@Validated
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/registry")
    public String registry(){
        return "registry";
    }
    
//    @PostMapping("/form")
//    @PostMapping(value ="/form", consumes =MediaType.APPLICATION_JSON_VALUE, produces =MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    public String create(@RequestBody User inputUser) {
//        try{
//            User user = new User();
//            BeanUtils.copyProperties(inputUser, user);
//            userService.created(user);
//            return "index.html";
//        }catch(UserNotFoundException ex){
//            System.out.println(ex.getMessage());
//            return "/registro";
//        }
//    }
//    @PostMapping(path = "/form", consumes = "application/json")
//    public String test(@RequestBody User user) {
//      return user.toString();
//    }

//    @PostMapping(path ="/form", consumes = "application/x-www-form-urlencoded", produces =MediaType.APPLICATION_JSON_VALUE)
//    public String testh(@RequestBody User user) {
//      return user.toString();
//    }

    
    @PostMapping("/registry")
    public String create(Model model, @Valid User inputUser, @RequestParam String departament) {
        try{
            User user = new User();
            BeanUtils.copyProperties(inputUser, user);
            user.setAddress(inputUser.getAddress() + " - " + departament);
            userService.create(user);
            return "redirect:/home";
        } catch (UserNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
            return "registry";
        }
    }

    @PostMapping("/")
    public void update(String id, User newUser) {
        userService.update(id, newUser);
    }
    
    @PostMapping
    public void delete(String id) throws UserNotFoundException {
       userService.delete(id);
    }
    
    @GetMapping("/list")
    public List<User> findAll() {
        return userService.findAll();
    }
    
    @GetMapping("/{id}")
    public User findById(String id) throws UserNotFoundException {
        return userService.findById(id);
    }
    
    
    
}





