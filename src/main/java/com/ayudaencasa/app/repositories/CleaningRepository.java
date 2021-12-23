
package com.ayudaencasa.app.repositories;

import com.ayudaencasa.app.entities.Cleaning;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CleaningRepository extends JobRepository<Cleaning> {
  
    @Query("SELECT c FROM Cleaning c WHERE c.rooms = :rooms")
    List <Cleaning> findRooms(@Param("rooms")Integer rooms);
    
    List <Cleaning> findExteriors(@Param("exteriors")Boolean exteriors);
    
    List <Cleaning> findCooking(@Param("cooking")Boolean cooking);
    
    List <Cleaning> findLaundry(@Param("laundry")Boolean laundry);
    
    List <Cleaning> findIroning(@Param("ironing")Boolean ironing);
    
    
    
    
    
    
   
}
