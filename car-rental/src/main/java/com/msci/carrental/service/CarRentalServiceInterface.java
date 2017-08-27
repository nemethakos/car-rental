package com.msci.carrental.service;

import java.util.List;

import com.msci.carrental.service.model.Booking;
import com.msci.carrental.service.model.BookingRequest;
import com.msci.carrental.service.model.CarSpecification;
import com.msci.carrental.service.model.CarType;
import com.msci.carrental.service.model.Country;

public interface CarRentalServiceInterface {

	/**
	 * Country counts as inland country. Booking for inland country takes {@link #BOOKING_DELAY_FOR_INLAND_IN_SECONDS} seconds.
	 */
	Country INLAND_COUNTRY = Country.HU;

	/**
	 * Booking delay for {@link #INLAND_COUNTRY}
	 */
	int BOOKING_DELAY_FOR_INLAND_IN_SECONDS = 1;
	
	/**
	 * Booking delay for non {@link #INLAND_COUNTRY}
	 */
	int BOOKING_DELAY_FOR_FOREIGN_COUNTRIES_IN_SECONDS = 10;
	
	/**
	 * Start id for booking ids
	 */
	int START_ID = 1000001;

	/**
	 * Returns the list of available {@link CarType}s
	 * 
	 * @return the list of available {@link CarType}s
	 */
	List<CarType> getAvailableCarsForRental();

	/**
	 * Returns the {@link CarSpecification} for the given {@link CarType}
	 * 
	 * @param carType
	 *            the {@link CarType}
	 * @return the {@link CarSpecification} for the {@link CarType}
	 */
	CarSpecification getDetailedSpecificationForACar(CarType carType);

	/**
	 * Starts an asynchronous booking process. Returns a reference id.
	 * 
	 * @param bookingRequest
	 *            the {@link BookingRequest}
	 * @return the reference id
	 */
	long bookACar(BookingRequest bookingRequest);

	/**
	 * Sets the receiver for the booking results. The booking results are returned
	 * asynchronously.
	 * 
	 * @param bookingResultReceiver
	 *            the {@link BookingResultReceiverInterface}
	 */
	void setBookingResultReceiver(BookingResultReceiverInterface bookingResultReceiver);

	/**
	 * Returns all bookings
	 * 
	 * @return all bookings
	 */
	List<Booking> getAllBookings();
}
