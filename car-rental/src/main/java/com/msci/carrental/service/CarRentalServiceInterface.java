package com.msci.carrental.service;

import java.util.List;

import com.msci.carrental.service.model.CarSpecification;
import com.msci.carrental.service.model.CarType;

public interface CarRentalServiceInterface {

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
}
