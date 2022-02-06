
package com.ayudaencasa.app.controllers;

import com.ayudaencasa.app.dto.input.RegisterUserDTO;
import com.ayudaencasa.app.entities.User;
import com.ayudaencasa.app.exceptions.UserNotFoundException;
import com.ayudaencasa.app.services.S3Service;
import com.ayudaencasa.app.services.UserService;
import java.util.List;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Validated
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private S3Service s3service;
    
    @Autowired
    private ModelMapper modelmap;
            
    @GetMapping("/registry")
    public String registry(){
        return "registryForm";
    }
    
    @PostMapping("/registry")
    public String create(RedirectAttributes redirectat, RegisterUserDTO inputUser) {
        try{
            System.out.println(inputUser);
            User user = new User();
            userService.validated(inputUser);
            modelmap.map(inputUser, user);
            user.setAddress(inputUser.getAddress() + " - " + inputUser.getDepartament());
            if(inputUser.getPic() != null && !inputUser.getPic().isEmpty()){
                user.setPhoto(s3service.save(inputUser.getPic()));
            }
            userService.create(user);
            redirectat.addFlashAttribute("success", "Se ha registrado con éxito");
            return "redirect:/home";
        } catch (UserNotFoundException ex) {
            redirectat.addFlashAttribute("error", ex.getMessage());
            redirectat.addFlashAttribute("firstName", inputUser.getFirstName());
            redirectat.addFlashAttribute("lastName", inputUser.getLastName());
            redirectat.addFlashAttribute("email", inputUser.getEmail());
            redirectat.addFlashAttribute("address", inputUser.getAddress());
            redirectat.addFlashAttribute("dni", inputUser.getDni());
            redirectat.addFlashAttribute("phone", inputUser.getPhone());
            return "redirect:/user/registry";
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





