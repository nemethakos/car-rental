package com.msci.carrental.service.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Maps {@link Country}s and the {@link List} of available {@link CarInstance}s
 */
public class BookingSearchResult extends HashMap<Country, List<CarInstance>> {

	private static final long serialVersionUID = 1430582037839224160L;

	public BookingStatus getBookingStatus(List<Country> countries) {
		boolean bookingSuccessful = true;
		List<String> problems = new ArrayList<>();

		for (Country country : countries) {
			if (this.containsKey(country) && this.get(country).isEmpty()) {
				problems.add("No car from the desired type available for the desired booking time interval for " + country);
				bookingSuccessful = false;
			}
		}

		BookingStatus result = new BookingStatus(bookingSuccessful, problems.toString());

		return result;
	}

}
