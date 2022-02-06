package com.ayudaencasa.app.services.impl;

import com.ayudaencasa.app.criteria.CaregiverCriteria;
import com.ayudaencasa.app.dto.input.CreateCaregiverDTO;
import com.ayudaencasa.app.entities.Caregiver;
import com.ayudaencasa.app.entities.Caregiver_;
import com.ayudaencasa.app.exceptions.CaregiverNotFoundException;
import com.ayudaencasa.app.exceptions.UserNotFoundException;
import com.ayudaencasa.app.repositories.CaregiverRepository;
import com.ayudaencasa.app.services.CaregiverService;
import com.ayudaencasa.app.services.UserService;
import io.github.jhipster.service.QueryService;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CaregiverServiceImpl extends QueryService<Caregiver> implements CaregiverService {

    @Autowired
    private CaregiverRepository caregiverRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private UserService userService;

    private Specification<Caregiver> createSpecification(CaregiverCriteria caregiverCriteria) {
        Specification<Caregiver> specification = Specification.where(null);
        if (caregiverCriteria != null) {
            if (caregiverCriteria.getQuantity() != null) {
                specification = specification.and(buildRangeSpecification(caregiverCriteria.getQuantity(), Caregiver_.quantity));
            }
            if (caregiverCriteria.getAgeFrom() != null) {
                specification = specification.and(buildRangeSpecification(caregiverCriteria.getAgeFrom(), Caregiver_.ageFrom));
            }
            if (caregiverCriteria.getAgeTo() != null) {
                specification = specification.and(buildRangeSpecification(caregiverCriteria.getAgeTo(), Caregiver_.ageTo));
            }
            if (caregiverCriteria.getCooking() != null) {
                specification = specification.and(buildSpecification(caregiverCriteria.getCooking(), Caregiver_.cooking));
            }
            if (caregiverCriteria.getCleaningPeople() != null) {
                specification = specification.and(buildSpecification(caregiverCriteria.getCleaningPeople(), Caregiver_.cleaningPeople));
            }
            if (caregiverCriteria.getTranfering() != null) {
                specification = specification.and(buildSpecification(caregiverCriteria.getTranfering(), Caregiver_.tranfering));
            }

            if (caregiverCriteria.getSalary() != null) {
                specification = specification.and(buildRangeSpecification(caregiverCriteria.getSalary(), Caregiver_.salary));
            }
            if (caregiverCriteria.getWorkingZone() != null) {
                specification = specification.and(buildStringSpecification(caregiverCriteria.getWorkingZone(), Caregiver_.workingZone));
            }
            if (caregiverCriteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(caregiverCriteria.getDescription(), Caregiver_.description));
            }
            if (caregiverCriteria.getHoursFrom() != null) {
                specification = specification.and(buildRangeSpecification(caregiverCriteria.getHoursFrom(), Caregiver_.hoursFrom));
            }
            if (caregiverCriteria.getHoursTo() != null) {
                specification = specification.and(buildRangeSpecification(caregiverCriteria.getHoursTo(), Caregiver_.hoursTo));
            }
        }
        return specification;
    }

    @Override
    public List<Caregiver> findByCriteria(CaregiverCriteria caregiverCriteria) {
        final Specification<Caregiver> specification = createSpecification(caregiverCriteria);
        List<Caregiver> caregivers = caregiverRepository.findAll(specification);
        return caregivers;
    }

    @Override
    @Transactional
    public Caregiver create(@NonNull Caregiver caregiver) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }
        caregiver.setUser(userService.findByEmail(userDetails.getUsername()));
        return caregiverRepository.save(caregiver);
    }
    
    @Override
    public void validated(CreateCaregiverDTO caregiver) throws CaregiverNotFoundException{
        if(caregiver.getAgeRange() == null || caregiver.getAgeRange().isEmpty()){
            throw new CaregiverNotFoundException("El rango de edad no puede ser nulo");
        }
        if(caregiver.getWorkingZone() == null || caregiver.getWorkingZone().isEmpty()){
            throw new CaregiverNotFoundException("La zona laboral no puede estar vacía");
        }
        if(caregiver.getDays() == null || caregiver.getDays().isEmpty()){
            throw new CaregiverNotFoundException("Los días laborales no pueden estar vacíos");
        }
        if(caregiver.getQuantity() == null){
            throw new CaregiverNotFoundException("La cantidad de personas a cuidar no puede ser nulo");
        }
        if(caregiver.getSalary() == null){
            throw new CaregiverNotFoundException("El salario no puede ser nulo");
        }
        if(caregiver.getWorkingHoursFrom() == null){
            throw new CaregiverNotFoundException("El horario no puede ser nulo");
        }
        if(caregiver.getWorkingHoursTo() == null){
            throw new CaregiverNotFoundException("El horario no puede ser nulo");
        }       
    }
    

    @Override
    @Transactional
    public void update(@NonNull String id, @NonNull Caregiver newCaregiver) throws Exception {
        Optional<Caregiver> opt = caregiverRepository.findById(id);
        if (opt.isPresent()) {
            Caregiver caregiver = opt.get();
            modelMapper.map(newCaregiver, caregiver);
            caregiverRepository.save(caregiver);
        } else {
            throw new Exception();
        }
    }

    @Override
    @Transactional
    public void delete(@NonNull String id) throws Exception {
        Optional<Caregiver> opt = caregiverRepository.findById(id);
        if (opt.isPresent()) {
            caregiverRepository.delete(opt.get());
        } else {
            throw new Exception();
        }
    }

    @Override
    public Caregiver findById(@NonNull String id) {
        Optional<Caregiver> opt = caregiverRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<Caregiver> findAll() {
        return caregiverRepository.findAll();
    }

}
