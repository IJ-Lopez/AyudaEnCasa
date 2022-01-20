
package com.ayudaencasa.app.repositories;

import RolesSeguridad.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege,Long> {
   
    public Privilege findByName(String name);
    
}
