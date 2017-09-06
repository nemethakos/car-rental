package com.msci.carrental.client.interpreter;

public interface BookingHandlerInterface {

	/**
	 * The implementor of the interface can store the booking id and poll the result
	 * of the booking later.
	 * 
	 * @param bookingId
	 *            the id returned by the booking service
	 */
	void addBookingIdToThePollerQueue(long bookingId);

}
