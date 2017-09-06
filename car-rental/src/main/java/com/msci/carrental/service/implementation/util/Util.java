package com.msci.carrental.service.implementation.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.msci.carrental.service.model.BookingRequest;
import com.msci.carrental.service.model.CarType;
import com.msci.carrental.service.model.Country;

public class Util {

	public static List<CarType> getValidCarTypes() {
		return Arrays.asList(CarType.values());
	}

	public static List<String> validateBookingRequest(BookingRequest bookingRequest) {
		List<String> errors = new ArrayList<>();

		Date startDate = bookingRequest.getStartDate();
		Date endDate = bookingRequest.getEndDate();
		if (bookingRequest.getCarType() == null) {
			errors.add("carType was invalid or not specified. Valid values are: " + getValidCarTypes());
		}
		if (startDate == null) {
			errors.add("startDate was invalid or not specified. Valid format is: YYYY-MM-DD (e.g.: 2017-09-06 )");
		}
		if (endDate == null) {
			errors.add("endDate  was invalid or not specified. Valid format is: YYYY-MM-DD (e.g.: 2017-09-06 )");
		}
/*		if (bookingRequest.getCountries() == null) {
			errors.add("countries was invalid or not specified. Valid values are: " + getValidCountries());
		}*/

		if (startDate != null && endDate != null) {

			if (endDate.equals(startDate)) {
				errors.add("Minimum booking time is one day!");
			}
			if (endDate.getTime() < startDate.getTime()) {
				errors.add("Start date should be before end date!");
			}
			Calendar today = Calendar.getInstance();
			today.set(Calendar.HOUR_OF_DAY, 0);
			today.set(Calendar.MINUTE, 0);
			today.set(Calendar.SECOND, 0);
			today.set(Calendar.MILLISECOND, 0);
			Date todayDate = today.getTime();

			if (todayDate.getTime() > startDate.getTime()) {
				errors.add("The earliest day of the start of booking is today (" + todayDate + ")");
			}

		}

		return errors;
	}

	public static List<Country> getValidCountries() {
		return Arrays.asList(Country.values());
	}

	public static Date normalizeDate(Date startDate) {

		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		return c.getTime();
	}

}
