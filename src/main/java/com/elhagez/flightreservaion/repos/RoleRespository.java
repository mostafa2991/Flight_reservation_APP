package com.elhagez.flightreservaion.repos;

  
import org.springframework.data.jpa.repository.JpaRepository;

import com.elhagez.flightreservaion.entities.Role;


public interface RoleRespository extends JpaRepository<Role, Integer> {

}
