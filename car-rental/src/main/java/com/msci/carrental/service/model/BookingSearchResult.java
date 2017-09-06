package com.msci.carrental.service.model;

import java.util.HashMap;
import java.util.List;

/**
 * Maps {@link Country}s and the {@link List} of available {@link CarInstance}s
 */
public class BookingSearchResult extends HashMap<Country, List<CarInstance>> {

	private static final long serialVersionUID = 1430582037839224160L;

	public BookingStatus getBookingStatus(List<Country> countries) {
		boolean bookingSuccessful = true;
		StringBuilder sb = new StringBuilder();

		for (Country country : countries) {
			if (this.containsKey(country) && this.get(country).isEmpty()) {
				sb.append("No car available for " + country + "\r\n");
				bookingSuccessful = false;
			}
		}

		BookingStatus result = new BookingStatus(bookingSuccessful, sb.toString());

		return result;
	}

}
