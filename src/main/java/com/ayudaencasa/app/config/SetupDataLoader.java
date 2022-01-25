
package com.ayudaencasa.app.config;

import com.ayudaencasa.app.entities.Role;
import com.ayudaencasa.app.entities.Privilege;
import com.ayudaencasa.app.entities.User;
import com.ayudaencasa.app.repositories.PrivilegeRepository;
import com.ayudaencasa.app.repositories.RoleRepository;
import com.ayudaencasa.app.repositories.UserRepository;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SetupDataLoader implements
  ApplicationListener<ContextRefreshedEvent>{
    
    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;
 
    @Autowired
    private RoleRepository roleRepository;
 
    @Autowired
    private PrivilegeRepository privilegeRepository;
 
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
       
        if (alreadySetup)
            return;
        
        Privilege readPrivilege
          = createPrivilegeIfNotFound("USER_PRIVILEGE");
        Privilege writePrivilege
          = createPrivilegeIfNotFound("ADMIN_PRIVILEGE");
 
        List<Privilege> adminPrivileges = Arrays.asList(
          readPrivilege, writePrivilege);
        
        
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
                
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setPassword(passwordEncoder.encode("test"));
        user.setEmail("test@test.com");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);

        alreadySetup = true; 

    }
    

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {
 
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege();
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {
 
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role();
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
        

        

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
  
    
}
