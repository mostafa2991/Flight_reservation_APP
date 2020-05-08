package com.elhagez.flightreservaion.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elhagez.flightreservaion.dto.ReservationRequest;
import com.elhagez.flightreservaion.entities.Flight;
import com.elhagez.flightreservaion.entities.Passenger;
import com.elhagez.flightreservaion.entities.Reservation;
import com.elhagez.flightreservaion.repos.FlightRespository;
import com.elhagez.flightreservaion.repos.PassengerRespository;
import com.elhagez.flightreservaion.repos.ReservaionRespository;
import com.elhagez.flightreservaion.util.EmailUtilImpl;
import com.elhagez.flightreservaion.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Value("${com.elhagez.flightreservaion.itinerary.dirpath}")
	private String ITINERARY_DIR ;
	@Autowired
	FlightRespository flightRespository;
	@Autowired
	PassengerRespository PassengerRespository;
	@Autowired
	ReservaionRespository reservaionRespository;
	@Autowired
	PDFGenerator PDFGenerator;
	@Autowired
	EmailUtilImpl emailUtilImpl;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	
	
	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {
		LOGGER.info("Inside bookFlight: ");

		// make payment
		int flightId = request.getFlightId();
		LOGGER.info("Inside Fetching flight for flight id:"+flightId);

		Flight flight = flightRespository.findById(flightId).get();

		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerlastName());
		passenger.setEmail(request.getPassengerEmail());
		passenger.setPhone(request.getPassengerPhone());
		LOGGER.info("Inside Saving passenger id:"+passenger);

		Passenger savedPassenger = PassengerRespository.save(passenger);

		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		LOGGER.info("Inside Saving the reservation:"+reservation);

		Reservation savedReservation = reservaionRespository.save(reservation);
        String filePath = ITINERARY_DIR+savedReservation.getId()+".pdf";
		LOGGER.info("Generating the itinerary");
		PDFGenerator.generateItenrary(savedReservation, filePath);
		LOGGER.info("Emailing the itenrary");
		emailUtilImpl.sendEmail(passenger.getEmail(), filePath);
		return savedReservation;
	}

}
