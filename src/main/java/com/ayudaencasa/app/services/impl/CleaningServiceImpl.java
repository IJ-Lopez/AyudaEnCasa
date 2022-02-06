package com.ayudaencasa.app.services.impl;

import com.ayudaencasa.app.criteria.CleaningCriteria;
import com.ayudaencasa.app.dto.input.CreateCleaningDTO;
import com.ayudaencasa.app.entities.Cleaning;
import com.ayudaencasa.app.entities.Cleaning_;
import com.ayudaencasa.app.exceptions.CleaningNotFoundException;
import com.ayudaencasa.app.repositories.CleaningRepository;
import com.ayudaencasa.app.services.CleaningService;
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
public class CleaningServiceImpl extends QueryService<Cleaning> implements CleaningService {

    @Autowired
    private CleaningRepository cleaningRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private UserService userService;

    private Specification<Cleaning> createSpecification(CleaningCriteria cleaningCriteria) {
        Specification<Cleaning> specification = Specification.where(null);
        if (cleaningCriteria != null) {
            if (cleaningCriteria.getRooms() != null) {
                specification = specification.and(buildRangeSpecification(cleaningCriteria.getRooms(), Cleaning_.rooms));
            }
            if (cleaningCriteria.getExteriors() != null) {
//                specification = specification.and(buildSpecification(cleaningCriteria.getExteriors(), Cleaning_.exteriors));
            }
            if (cleaningCriteria.getCooking() != null) {
                specification = specification.and(buildSpecification(cleaningCriteria.getCooking(), Cleaning_.cooking));
            }
            if (cleaningCriteria.getLaundry() != null) {
                specification = specification.and(buildSpecification(cleaningCriteria.getLaundry(), Cleaning_.laundry));
            }
            if (cleaningCriteria.getIroning() != null) {
                specification = specification.and(buildSpecification(cleaningCriteria.getIroning(), Cleaning_.ironing));
            }
            if (cleaningCriteria.getSalary() != null) {
                specification = specification.and(buildRangeSpecification(cleaningCriteria.getSalary(), Cleaning_.salary));
            }
            if (cleaningCriteria.getWorkingZone() != null) {
                specification = specification.and(buildStringSpecification(cleaningCriteria.getWorkingZone(), Cleaning_.workingZone));
            }
            if (cleaningCriteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(cleaningCriteria.getDescription(), Cleaning_.description));
            }
            if (cleaningCriteria.getHoursFrom() != null) {

                specification = specification.and(buildRangeSpecification(cleaningCriteria.getHoursFrom(), Cleaning_.hoursFrom));
            }
            if (cleaningCriteria.getHoursTo() != null) {
                specification = specification.and(buildRangeSpecification(cleaningCriteria.getHoursTo(), Cleaning_.hoursTo));
            }
        }
        return specification;
    }

    @Override
    public List<Cleaning> findByCriteria(CleaningCriteria cleaningCriteria) {
        final Specification<Cleaning> specification = createSpecification(cleaningCriteria);
        List<Cleaning> gardeners = cleaningRepository.findAll(specification);
        return gardeners;
    }

    @Override
    @Transactional
    public Cleaning create(@NonNull Cleaning cleaning) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }
        cleaning.setUser(userService.findByEmail(userDetails.getUsername()));
        return cleaningRepository.save(cleaning);
    }
    
    @Override
    public void validated(CreateCleaningDTO cleaning) throws CleaningNotFoundException {
        if(cleaning.getWorkingZone()== null || cleaning.getWorkingZone().isEmpty()){
            throw new CleaningNotFoundException("La zona laboral no puede estar vacía");
        }
        if(cleaning.getDays() == null || cleaning.getDays().isEmpty()){
            throw new CleaningNotFoundException("Los días laborales no pueden estar vacíos");
        }
        if(cleaning.getSalary() == null){
            throw new CleaningNotFoundException("El salario no puede ser nulo");
        }
        if(cleaning.getWorkingHoursFrom() == null){
            throw new CleaningNotFoundException("El horario no puede ser nulo");
        }
        if(cleaning.getWorkingHoursTo() == null){
            throw new CleaningNotFoundException("El horario no puede ser nulo");
        }       
    }

    @Override
    public void update(@NonNull String id, @NonNull Cleaning newCleaning) throws CleaningNotFoundException {
        Optional<Cleaning> opt = cleaningRepository.findById(id);
        if (opt.isPresent()) {
            Cleaning cleaning = opt.get();
            modelMapper.map(newCleaning, cleaning);
            cleaningRepository.save(cleaning);
        } else {
            throw new CleaningNotFoundException("Cleaner not found");
        }

    }

    @Override
    public void delete(@NonNull String id) throws CleaningNotFoundException {
        Optional<Cleaning> opt = cleaningRepository.findById(id);
        if (opt.isPresent()) {
            cleaningRepository.delete(opt.get());
        } else {
            throw new CleaningNotFoundException("Cleaner not found");
        }
    }

    @Override
    public Cleaning findById(@NonNull String id) throws CleaningNotFoundException {
        Optional<Cleaning> opt = cleaningRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new CleaningNotFoundException("Cleaner not found");
        }
    }

    @Override
    public List<Cleaning> findAll() {
        return cleaningRepository.findAll();
    }

}
