package com.ayudaencasa.app.repositories;

import com.ayudaencasa.app.entities.Caregiver;
import java.util.Optional;

public abstract class CaregiverRepository implements JobRepository {

    public Optional<Caregiver> findAll(Caregiver newCaregiver) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
