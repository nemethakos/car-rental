package com.msci.carrental.client.interpreter.command;

import java.util.Date;
import java.util.List;

import com.msci.carrental.client.interpreter.CommandHandlerInterface;
import com.msci.carrental.client.interpreter.CommandResult;
import com.msci.carrental.client.util.Util;
import com.msci.carrental.service.CarRentalServiceInterface;
import com.msci.carrental.service.model.BookingRequest;
import com.msci.carrental.service.model.CarType;
import com.msci.carrental.service.model.Country;

public class StressTestCommand implements CommandHandlerInterface {
	private static final int NUMBER_OF_BOOKINGS = 100;
	private CarRentalServiceInterface service;
	@Override
	public void setCarRentalService(CarRentalServiceInterface carRentalServiceInterface) {
		this.service = carRentalServiceInterface;
	}

	@Override
	public String getCommandName() {
		return "stress";
	}

	@Override
	public CommandResult invoke(List<String> parameters) {
		CommandResult result = new CommandResult();

		if (!parameters.isEmpty()) {
			result.addError("No parameters allowed!");
		} else {
			for (int i = 0; i < NUMBER_OF_BOOKINGS; i++) {
				doARandomBooking(i);
			}
			result.addMessage(String.valueOf(NUMBER_OF_BOOKINGS) + " bookings sent...");
		}
		return result;
	}

	private void doARandomBooking(int i) {
		BookingRequest bookingRequest = getRandomBookingRequest(i);
		service.bookACar(bookingRequest);

	}

	private static BookingRequest getRandomBookingRequest(int i) {

		CarType carType = Util.getARandomCarType();
		Date startDate = new Date(Util.getRandomMonthInterval(System.currentTimeMillis()));
		Date endDate = new Date(Util.getRandomMonthInterval(startDate.getTime()));
		List<Country> countries = Util.getRandomCountryList();
		BookingRequest result = new BookingRequest(carType, startDate, endDate, countries);
		return result;
	}

	@Override
	public List<String> getCommandDescription() {
		return null;
	}

	@Override
	public List<String> getParameterDescription() {
		return null;
	}

	@Override
	public String getTagLine() {
		return "Performs a stress test (100 bookings)";
	}

}
