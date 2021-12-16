package com.ayudaencasa.app.repositories;

import com.ayudaencasa.app.entities.Job;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface JobRepository<T extends Job> extends JpaRepository<T, String>{
    
    List<T> findAllWhereStatusEqualsTrue();
    
    List<T> findBySalary(String salary);
    
    List<T> findByWorkingZone(String workingZone);
    
    List<T> findByDescriptionContaining(String description);
    
    List<T> findByDateFrom(Date dateFrom);
    
    List<T> findByDateTo(Date dateTo);
}
