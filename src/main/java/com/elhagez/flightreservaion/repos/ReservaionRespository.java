package com.elhagez.flightreservaion.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elhagez.flightreservaion.entities.Reservation;

public interface ReservaionRespository extends JpaRepository<Reservation, Integer> {

}
