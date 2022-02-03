package com.ayudaencasa.app.config;

import com.ayudaencasa.app.entities.Caregiver;
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
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User crearUserTest(String firstName, String lastName, String email, String password, Integer dni) {
        User user = new User();
        //user.setDeletedAt(deletedAt);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setDni(dni);
        List<Role> rol = new ArrayList();
        rol.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(rol);
        return user;
    }

    public Caregiver crearCaregiver(User user) {
        Caregiver care = new Caregiver();
        care.setAgeFrom(2);
        care.setAgeTo(15);
        care.setCleaningPeople(true);
        care.setCooking(false);
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

    public Other crearOther(User user) {
        Other other = new Other();

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

        try {
            if (!userRepository.findByEmail("user@user.com").isPresent()) {
                User user1 = crearUserTest("User", "User", "user@user.com", "contra123", 123456);
                User user2 = crearUserTest("Adlllt", "Adlllt", "adt@adt.com", "control124", 1452656);
                User user3 = crearUserTest("usuario", "usuario", "usuario@usuario.com", "cntra23", 15785633);
                userRepository.save(user1);
                userRepository.save(user2);
                userRepository.save(user3);

                caregiverRepository.save(crearCaregiver(user1));
                caregiverRepository.save(crearCaregiver(user2));
                otherRepository.save(crearOther(user1));
                System.out.println("hola");
            }

        } catch (Exception e) {
            System.out.println("No funco");
        }

    }

}
