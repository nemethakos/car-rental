package com.msci.carrental.service.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class BookingStatus {
	
	@Override
	public String toString() {
		return "BookingStatus [successfulBooking=" + successfulBooking + ", description=" + description + "]";
	}

	@XmlElement
	private boolean successfulBooking;
	
	@XmlElement
	private String description;

	public boolean isSuccessfulBooking() {
		return successfulBooking;
	}

	public String getDescription() {
		return description;
	}
	
	public BookingStatus(boolean successfulBooking, String description) {
		super();
		this.successfulBooking = successfulBooking;
		this.description = description;
	}

	public BookingStatus() {
	}
	
}
