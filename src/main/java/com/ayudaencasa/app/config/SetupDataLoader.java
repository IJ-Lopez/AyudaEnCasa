package com.ayudaencasa.app.config;

import com.ayudaencasa.app.entities.Gardener;
import com.ayudaencasa.app.entities.Other;
import com.ayudaencasa.app.entities.Role;
import com.ayudaencasa.app.entities.Privilege;
import com.ayudaencasa.app.entities.User;
import com.ayudaencasa.app.repositories.GardenerRepository;
import com.ayudaencasa.app.repositories.OtherRepository;
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
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private GardenerRepository gardenRepository;

    @Autowired
    private OtherRepository otherRepo;
    
    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup) {
            return;
        }

        Privilege readPrivilege
                = createPrivilegeIfNotFound("USER_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("ADMIN_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege);

        Role adminRole = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);

        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        if (!userRepository.findByEmail("test@test.com").isPresent()) {
            User user = new User();
            user.setFirstName("Test");
            user.setLastName("Test");
            user.setPassword(passwordEncoder.encode("test"));
            user.setEmail("test@test.com");
            user.setRoles(Arrays.asList(adminRole));
            userRepository.save(user);
        }
        
        if(gardenRepository.findAll().isEmpty()){
            gardenRepository.save(new Gardener());
            otherRepo.save(new Other("jamon"));            
        }
        
        alreadySetup = true;

    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            role = roleRepository.save(role);
        }
        return role;
    }
}
