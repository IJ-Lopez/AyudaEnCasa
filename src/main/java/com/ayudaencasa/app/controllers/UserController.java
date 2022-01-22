
package com.ayudaencasa.app.controllers;

//import org.springframework.stereotype.Controller;
import com.ayudaencasa.app.entities.User;
import com.ayudaencasa.app.exceptions.UserNotFoundException;
import com.ayudaencasa.app.services.UserService;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@Validated
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/registro")
    public String registro(){
        return "formularioRegistro.html";
    }
    
//    @RequestMapping(value = "/form", method = RequestMethod.POST,
//                        consumes = MediaType.APPLICATION_JSON_VALUE)
//    
//    @ResponseStatus(HttpStatus.OK)
//    
//    @RequestMapping(value = "/form", method = RequestMethod.POST)
//    @PostMapping("/form")
//    public String create(Model model, @RequestParam String firstName, @RequestParam String lastName, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dob,  @RequestParam Integer dni, @RequestParam String address, @RequestParam Integer phone, @RequestParam String mail, @RequestParam String password, @RequestParam String password2) {
//        try{
////            User user = new User();
////            BeanUtils.copyProperties(inputUser, user);
//            System.out.println("nombre: " + firstName);
//            System.out.println("apellido: " + lastName);
//            System.out.println("email: " + mail);
//            System.out.println("address: " + address);
//            userService.create(firstName, lastName, java.sql.Timestamp.valueOf(dob), dni, address, phone, mail, password, password2);
//            return "index.html";
//        }catch(UserNotFoundException ex){
//            System.out.println(ex.getMessage());
//            return "/registro";
//        }
//    }
    
//    @PostMapping("/form")
//    public String create(Model model, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String doc, @RequestParam String address, @RequestParam String tel, @RequestParam String mail, @RequestParam String password, @RequestParam String password2) {
//        try{
////            User user = new User();
////            BeanUtils.copyProperties(inputUser, user);
//            System.out.println("nombre: " + firstName);
//            System.out.println("apellido: " + lastName);
//            System.out.println("email: " + mail);
//            System.out.println("address: " + address);
//            int dni = Integer.parseInt(doc);
//            int phone = Integer.parseInt(tel);
//            userService.create(firstName, lastName, dni, address, phone, mail, password, password2);
//            return "index.html";
//        }catch(UserNotFoundException ex){
//            System.out.println(ex.getMessage());
//            return "/registro";
//        }
//    }
    
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
    
    @RequestMapping(value = "/formiy", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> created(@RequestBody User user) {
        User userCreated = userService.created(user);
        return new ResponseEntity(userCreated, HttpStatus.CREATED);
    }
    
    @PostMapping("/form")
    @ResponseStatus(HttpStatus.OK)
    public String create(@Valid User inputUser) {
        User user = new User();
        System.out.println(inputUser);
        BeanUtils.copyProperties(inputUser, user);
        userService.created(user);
        return "index.html";
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





