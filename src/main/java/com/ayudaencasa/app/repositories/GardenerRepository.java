/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayudaencasa.app.repositories;

import com.ayudaencasa.app.entities.Gardener;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usr
 */
@Repository
public interface GardenerRepository extends JobRepository<Gardener>{
    
//    @Query("SELECT g FROM :gardeners WHERE :day IN g.days")
//    List<Gardener> findByDay(@Param("day") String day, @Param("gardeners") List<Gardener> gardeners);
//    public Autor buscarAutorPorNombre(@Param("nombre") String nombre);
    
    
    
    
}
