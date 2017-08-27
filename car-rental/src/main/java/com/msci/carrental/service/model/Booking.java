package com.msci.carrental.service.model;

/**
 * Class for providing booking information for already booked cars.
 */
public class Booking {
	private long id;
	private BookingRequest bookingRequest;

	public long getId() {
		return id;
	}

	public BookingRequest getBookingRequest() {
		return bookingRequest;
	}

	public Booking(long id, BookingRequest bookingRequest) {
		super();
		this.id = id;
		this.bookingRequest = bookingRequest;
	}
}
