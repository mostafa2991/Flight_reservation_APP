package com.elhagez.flightreservaion.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elhagez.flightreservaion.entities.User;

public interface UserRespository extends JpaRepository<User, Integer> {

	User findByEmail(String email);

}
