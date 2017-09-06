package com.msci.carrental.service.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class BookingResult {

	@XmlElement
	private long reference;
	
	@XmlElement
	private BookingRequest bookingRequest;
	
	@XmlElement
	private BookingStatus bookingStatus;
	
	@XmlElement	
	private List<CarInstance> carInstances;
	
	public BookingResult(long reference, BookingRequest bookingRequest, BookingStatus bookingStatus,
			List<CarInstance> carInstances) {
		super();
		this.reference = reference;
		this.bookingRequest = bookingRequest;
		this.bookingStatus = bookingStatus;
		this.carInstances = carInstances;
	}

	public List<CarInstance> getCarInstances() {
		return carInstances;
	}

	public BookingRequest getBookingRequest() {
		return bookingRequest;
	}
	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}
	public long getReference() {
		return reference;
	}

	@Override
	public String toString() {
		return "BookingResult [reference=" + reference + ", bookingRequest=" + bookingRequest + ", bookingStatus="
				+ bookingStatus + ", carInstances=" + carInstances + "]";
	}
	
	
}
