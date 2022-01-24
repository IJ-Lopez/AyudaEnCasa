package com.ayudaencasa.app.services.impl;

import RolesSeguridad.Privilege;
import RolesSeguridad.Role;
import com.ayudaencasa.app.entities.User;
import com.ayudaencasa.app.repositories.RoleRepository;
import com.ayudaencasa.app.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserIndexService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserServiceImpl service;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> opt = userRepo.findByEmail(email);

        if (opt == null) {
            return new org.springframework.security.core.userdetails.User(
                    " ", " ", true, true, true, true,
                    getAuthorities(Arrays.asList(roleRepository.findByName("ROLE_USER"))));
        }

        User user = opt.get();

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), user.getDeletedAt()==null, true, true,
                true, getAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {

        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

//         if(!opt.isPresent()){
//             throw new UsernameNotFoundException("Email not found");
//         }
//         
//         User usuario = opt.get();
//        
//        if(usuario != null){
//            List<GrantedAuthority> permisos = new ArrayList<>();
//            
//            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_USER_REGISTER");
//            permisos.add(p1);
//            
//            
//            
//            org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getPassword(), permisos);
//            return user;
//        }else{
//            return null;
//        }
}
