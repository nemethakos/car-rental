package com.msci.carrental.service;

import com.msci.carrental.service.model.BookingResult;

public interface BookingResultReceiverInterface {

	/**
	 * Implementor class can receive {@link BookingResult}
	 * 
	 * @param bookingResult
	 *            the {@link BookingResult}
	 */
	void receiveBookingResult(BookingResult bookingResult);

}
