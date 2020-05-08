package com.elhagez.flightreservaion.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractEntity {

	  @Id
	   @GeneratedValue (strategy =GenerationType.AUTO )
	   @Column(name = "ID" )
		private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	  
}
