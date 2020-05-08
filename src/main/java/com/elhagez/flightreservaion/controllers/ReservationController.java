package com.elhagez.flightreservaion.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.elhagez.flightreservaion.dto.ReservationRequest;
import com.elhagez.flightreservaion.entities.Flight;
import com.elhagez.flightreservaion.entities.Reservation;
import com.elhagez.flightreservaion.repos.FlightRespository;
import com.elhagez.flightreservaion.services.ReservationService;
@Controller
public class ReservationController {
	@Autowired
	FlightRespository  flightRespository;
	@Autowired
	ReservationService reservationService;
    
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    @RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") int flightId ,
    		ModelMap modelMap ){
    	LOGGER.info("Inside showCompleteReservation() FLIGHT_ID: "+flightId);
    	Flight flight = flightRespository.findById(flightId).get();
    	
    	
    	modelMap.addAttribute("flight",flight);
    	LOGGER.info("Inside FlightIs: "+flight);
		return "CompleteReservation";

    }
    @RequestMapping(value = "/completeReservation" ,method = RequestMethod.POST)
    public String completeReservation(ReservationRequest request,
    		ModelMap modelMap) {
    	LOGGER.info("Inside completeReservation()"+request);

    	Reservation reservation = reservationService.bookFlight(request);
    	modelMap.addAttribute("msg", "Reservation created successfully and the id is "+ reservation.getId());
    	
		return "reservationConfirmation";
    	
    }
    
}
