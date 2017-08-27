package com.msci.carrental.service.model;

public class BookingResult {

	private long reference;
	private BookingRequest bookingRequest;
	private BookingStatus bookingStatus;
	
	public BookingResult(long reference, BookingRequest bookingRequest, BookingStatus bookingStatus) {
		super();
		this.reference = reference;
		this.bookingRequest = bookingRequest;
		this.bookingStatus = bookingStatus;
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
				+ bookingStatus + "]";
	}
	
	
}
