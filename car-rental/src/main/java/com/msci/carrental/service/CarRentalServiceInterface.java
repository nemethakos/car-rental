package com.msci.carrental.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.msci.carrental.service.model.BookingRequest;
import com.msci.carrental.service.model.BookingResult;
import com.msci.carrental.service.model.CarInstance;
import com.msci.carrental.service.model.CarSpecification;
import com.msci.carrental.service.model.CarType;
import com.msci.carrental.service.model.Country;

@WebService
public interface CarRentalServiceInterface {

	/**
	 * Country counts as inland country. Booking for inland country takes
	 * {@link #BOOKING_DELAY_FOR_INLAND_IN_SECONDS} seconds.
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
	@WebMethod
	List<CarInstance> getAvailableCarsForRental();

	/**
	 * Returns the {@link CarSpecification} for the given {@link CarType}
	 * 
	 * @param carType
	 *            the {@link CarType}
	 * @return the {@link CarSpecification} for the {@link CarType}
	 */
	@WebMethod
	CarSpecification getDetailedSpecificationForACarType(CarType carType);

	/**
	 * Starts an asynchronous booking process. Returns a reference id.
	 * 
	 * @param bookingRequest
	 *            the {@link BookingRequest}
	 * @return the reference id
	 */
	@WebMethod
	long bookACar(BookingRequest bookingRequest);

	/**
	 * <p>
	 * Method for the client to poll periodically the status of the booking
	 * requests.
	 * <p>
	 * Returns the list of {@link BookingResult}s for the specified
	 * <code>bookingIdList</code>. 
	 * 
	 * @param bookingIdList
	 *            the List of Ids
	 * @return {@link List} of {@link BookingResult}s for the Ids
	 */
	List<BookingResult> getBookingResultsForIds(List<Long> bookingIdList);
}
