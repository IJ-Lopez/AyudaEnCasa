
package com.ayudaencasa.app.repositories;

import com.ayudaencasa.app.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
   
    public Role findByName(String name);
    
}
