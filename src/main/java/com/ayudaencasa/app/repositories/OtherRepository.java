package com.ayudaencasa.app.repositories;

import com.ayudaencasa.app.entities.Other;
import java.util.List;

public interface OtherRepository extends JobRepository<Other> {
    
    List<Other> findByTypeContainingIgnoreCase(String type);
    
}
