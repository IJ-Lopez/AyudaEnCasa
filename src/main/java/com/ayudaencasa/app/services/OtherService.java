package com.ayudaencasa.app.services;

import com.ayudaencasa.app.criteria.OtherCriteria;
import com.ayudaencasa.app.entities.Other;
import com.ayudaencasa.app.exceptions.OtherNotFoundException;
import java.util.List;

public interface OtherService {
    
    public Other create(Other other);
    public void update(String id, Other other) throws OtherNotFoundException;
    public void delete(String id) throws OtherNotFoundException;
    public Other findById(String id) throws OtherNotFoundException;
    public List<Other> findAll();
    public List<Other> findByType(String type);

    public List<Other> findByCriteria(OtherCriteria otherCriteria);
    
}
