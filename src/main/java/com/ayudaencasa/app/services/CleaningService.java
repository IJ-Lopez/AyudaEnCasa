package com.ayudaencasa.app.services;

import com.ayudaencasa.app.entities.Cleaning;
import com.ayudaencasa.app.repositories.CleaningRepository;
import java.util.Date;
import java.util.List;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CleaningService {

    @Autowired
    private CleaningRepository cleaningRepository;

    public Cleaning create(@NonNull Integer rooms, Boolean exteriors, Boolean cooking, Boolean laundry, Boolean ironing, Integer salary, String workingZone, Date dateFrom, Date dateTo, Boolean status) {

        Cleaning cleaning = new Cleaning();
        cleaning.setRooms(rooms);
        cleaning.setExteriors(exteriors);
        cleaning.setCooking(cooking);
        cleaning.setLaundry(laundry);
        cleaning.setIroning(ironing);
        cleaning.setSalary(salary);
        cleaning.setWorkingZone(workingZone);
        cleaning.setDateFrom(dateFrom);
        cleaning.setDateTo(dateTo);
        cleaning.setStatus(status);

        return cleaningRepository.save(cleaning);

    }

    public void update(@NonNull String id, Integer rooms, Boolean exteriors, Boolean cooking, Boolean laundry, Boolean ironing, Integer salary, String workingZone, Date dateFrom, Date dateTo, Boolean status) {
        Cleaning cleaning = cleaningRepository.findById(id).get();
        cleaning.setRooms(rooms);
        cleaning.setExteriors(exteriors);
        cleaning.setCooking(cooking);
        cleaning.setLaundry(laundry);
        cleaning.setIroning(ironing);
        cleaning.setSalary(salary);
        cleaning.setWorkingZone(workingZone);
        cleaning.setDateFrom(dateFrom);
        cleaning.setDateTo(dateTo);
        cleaning.setStatus(status);
        
        cleaningRepository.save(cleaning);

    }

    public void delete(String id, Date deleteAt ) {
        Cleaning cleaning = cleaningRepository.findById(id).get();
        cleaning.setDeletedAt(deleteAt);
        
    }

    public Cleaning findById(@NonNull String id) {
        Cleaning cleaning = cleaningRepository.findById(id).get();
        
        return cleaningRepository.getById(id);

    }

    public List<Cleaning> findAll(@NonNull String id) {
        
        
        return cleaningRepository.findAll();

    }
}
