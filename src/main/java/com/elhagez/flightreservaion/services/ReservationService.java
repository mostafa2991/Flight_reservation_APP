package com.elhagez.flightreservaion.services;

import com.elhagez.flightreservaion.dto.ReservationRequest;
import com.elhagez.flightreservaion.entities.Reservation;

public interface ReservationService {
	
	public Reservation bookFlight(ReservationRequest request);

}
