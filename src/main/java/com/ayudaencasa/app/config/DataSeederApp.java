package com.ayudaencasa.app.config;

import com.ayudaencasa.app.entities.Caregiver;
import com.ayudaencasa.app.entities.Job;
import static com.ayudaencasa.app.entities.Job_.id;
import static com.ayudaencasa.app.entities.Job_.user;
import com.ayudaencasa.app.entities.Other;
import com.ayudaencasa.app.entities.Role;
import com.ayudaencasa.app.entities.User;
import com.ayudaencasa.app.repositories.CaregiverRepository;
import com.ayudaencasa.app.repositories.OtherRepository;
import com.ayudaencasa.app.repositories.RoleRepository;
import com.ayudaencasa.app.repositories.UserRepository;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataSeederApp implements CommandLineRunner {

   
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CaregiverRepository caregiverRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired 
    private OtherRepository otherRepository;
    public User crearUserTest(String firstName, String lastName, String email) {
        User user = new User();
        
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        List<Role> rol= new ArrayList();
        rol.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(rol);
        return user;
    }
    
    public Caregiver crearCaregiver(User user){
       Caregiver care= new Caregiver();
       care.setAgeFrom(2);
       care.setAgeTo(15);
       care.setCleaningPeople(true);
       care.setCooking(false);
       //care.setCreatedAt();
       //care.setDays("10");
       care.setDescription("HOla todo bien?");
       care.setHoursFrom(LocalTime.MIN);
       care.setHoursTo(LocalTime.MIN);
       care.setQuantity(10);
       care.setSalary(1000);
       care.setShowContactInfo(true);
       care.setStatus(true);
       care.setTranfering(true);
       care.setWorkingHoursFrom(LocalTime.MIDNIGHT);
       care.setWorkingHoursTo(LocalTime.MIDNIGHT);
       care.setWorkingZone("Guaymallen");
       care.setUser(user);
       return care;
    }
    
    public Other crearOther(User user){
       Other other= new Other();
       
       other.setType("plomero");
       other.setDescription("Se electricidad");
       other.setHoursFrom(LocalTime.MIN);
       other.setHoursTo(LocalTime.MIN);
       other.setWorkingHoursFrom(LocalTime.MIDNIGHT);
       other.setWorkingHoursTo(LocalTime.MIDNIGHT);
       other.setWorkingZone("Godoy Cruz");
       other.setUser(user);
       return other;
    }
    
    
    @Override
    @Transactional
    public void run(String... args) throws Exception {

        System.out.println("Hola mundooooo");
       
      try{
        if (!userRepository.findByEmail("user@user.com").isPresent() ) {
           User user1= crearUserTest("User", "User", "user@user.com");
            User user2= crearUserTest("Adt", "Adt", "adt@adt.com");
             User user3= crearUserTest("usuario", "usuario", "usuario@usuario.com");                         
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            
            caregiverRepository.save(crearCaregiver(user1));
            caregiverRepository.save(crearCaregiver(user2));
            
            otherRepository.save(crearOther(user1));
               
        } 
            
           
            
        
      }catch (Exception e){
          System.out.println("No funco");
      }
        //userRepository.save(new User());
        
        
        
        
        
        
    }

   // CaregiverRepository caregiverRepository;

}
