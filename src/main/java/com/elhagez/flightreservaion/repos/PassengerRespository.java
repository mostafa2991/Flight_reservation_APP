package com.elhagez.flightreservaion.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elhagez.flightreservaion.entities.Passenger;

public interface PassengerRespository extends JpaRepository<Passenger, Integer> {

}
