package com.ayudaencasa.app.repositories;

import com.ayudaencasa.app.entities.Job;
import com.ayudaencasa.app.enums.Day;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface JobRepository<T extends Job> extends JpaRepository<T, String>, JpaSpecificationExecutor<T> {
    
//    List<T> findAllWhereStatusEqualsTrue();
    
    List<T> findBySalary(String salary);
    
    List<T> findByWorkingZone(String workingZone);
    
    List<T> findByDescriptionContaining(String description);
    
//    List<T> findByDateFrom(Date dateFrom);
//    
//    List<T> findByDateTo(Date dateTo);
    
//    @Query("SELECT j FROM Job j WHERE :day IN j.days")
//    List<T> findByDay(@Param("day")String day);
}
