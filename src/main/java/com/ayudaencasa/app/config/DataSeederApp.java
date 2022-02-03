package com.ayudaencasa.app.config;

import com.ayudaencasa.app.entities.Caregiver;
import com.ayudaencasa.app.entities.Cleaning;
import com.ayudaencasa.app.entities.Gardener;
import com.ayudaencasa.app.entities.Other;
import com.ayudaencasa.app.entities.PetWalker;
import com.ayudaencasa.app.entities.Role;
import com.ayudaencasa.app.entities.User;
import com.ayudaencasa.app.repositories.CaregiverRepository;
import com.ayudaencasa.app.repositories.CleaningRepository;
import com.ayudaencasa.app.repositories.GardenerRepository;
import com.ayudaencasa.app.repositories.OtherRepository;
import com.ayudaencasa.app.repositories.PetWalkerRepository;
import com.ayudaencasa.app.repositories.RoleRepository;
import com.ayudaencasa.app.repositories.UserRepository;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeederApp implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CaregiverRepository caregiverRepository;
    @Autowired
    private PetWalkerRepository petWalkerRepository;
    @Autowired
    private CleaningRepository cleaningRepo;
    @Autowired
    private GardenerRepository gardenerRepo;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private OtherRepository otherRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private ThreadLocalRandom random;

    public User crearUserTest(String firstName, String lastName, String email, String password, Integer dni) {
        User user = new User();
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
        care.setCleaningPeople(random.nextBoolean());
        care.setCooking(random.nextBoolean());
        care.setDescription("HOla todo bien?");
        care.setHoursFrom(LocalTime.MAX);
        care.setHoursTo(LocalTime.MAX);
        care.setQuantity(random.nextInt(10));
        care.setSalary(random.nextInt(10000));
        care.setShowContactInfo(true);
        care.setStatus(true);
        care.setTranfering(random.nextBoolean());
        care.setWorkingHoursFrom(LocalTime.MIDNIGHT);
        care.setWorkingHoursTo(LocalTime.MIDNIGHT);
        care.setWorkingZone("Guaymallen");
        care.setUser(user);
        return care;
    }

    public PetWalker crearPetWalker(User user, String petType, String petRace) {
        PetWalker pw = new PetWalker();
        pw.setDescription("HOla todo bien?");
        pw.setHoursFrom(LocalTime.MAX);
        pw.setHoursTo(LocalTime.MAX);
        pw.setSalary(random.nextInt(10000));
        pw.setShowContactInfo(true);
        pw.setStatus(true);
        pw.setWorkingHoursFrom(LocalTime.MIDNIGHT);
        pw.setWorkingHoursTo(LocalTime.MIDNIGHT);
        pw.setWorkingZone("Guaymallen");
        pw.setUser(user);
        pw.setPetQuantity(random.nextInt(7));
        pw.setPetType(petType);
        pw.setPetRace(petRace);
        return pw;
    }

    public Cleaning crearCleaning(User user) {
        Cleaning cl = new Cleaning();
        cl.setDescription("Hola todo bien?");
        cl.setHoursFrom(LocalTime.MIN);
        cl.setHoursTo(LocalTime.MAX);
        cl.setSalary(random.nextInt(10000));
        cl.setShowContactInfo(true);
        cl.setStatus(true);
        cl.setWorkingHoursFrom(LocalTime.MIDNIGHT);
        cl.setWorkingHoursTo(LocalTime.MIDNIGHT);
        cl.setWorkingZone("Guaymallen");
        cl.setUser(user);
        cl.setRooms(random.nextInt(6));
        cl.setExteriors("Ambos");
        cl.setCooking(random.nextBoolean());
        cl.setLaundry(random.nextBoolean());
        cl.setIroning(random.nextBoolean());
        return cl;
    }

    public Gardener crearGardener(User user) {
        Gardener g = new Gardener();
        g.setDescription("Hola todo bien?");
        g.setHoursFrom(LocalTime.MIN);
        g.setHoursTo(LocalTime.MAX);
        g.setSalary(random.nextInt(10000));
        g.setShowContactInfo(true);
        g.setStatus(true);
        g.setWorkingHoursFrom(LocalTime.MIDNIGHT);
        g.setWorkingHoursTo(LocalTime.MIDNIGHT);
        g.setWorkingZone("Guaymallen");
        g.setUser(user);
        g.setSurface("Indistinto");
        g.setTools(random.nextBoolean());
        g.setPoolCleaning(random.nextBoolean());
        g.setGardenFence(random.nextBoolean());
        g.setPlantDisinfection(random.nextBoolean());
        return g;
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
        random = ThreadLocalRandom.current();

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
            for (int i = 0; i < 25; i++) {
                String name = RandomStringUtils.randomAlphabetic(8);
                User randomUser = crearUserTest(name, name, String.format("%s@%s.com", name, name), "contraseña", 1);
                if(i < 5){
                    caregiverRepository.save(crearCaregiver(randomUser));
                } else if (i < 10) {
                    petWalkerRepository.save(crearPetWalker(randomUser, RandomStringUtils.randomAlphabetic(6), RandomStringUtils.randomAlphabetic(6)));
                } else if (i < 15) {
                    gardenerRepo.save(crearGardener(randomUser));
                } else if (i < 20) {
                    cleaningRepo.save(crearCleaning(randomUser));
                } else if (i < 25) {
                    otherRepository.save(crearOther(randomUser));
                }
            }
        }
    }
}
