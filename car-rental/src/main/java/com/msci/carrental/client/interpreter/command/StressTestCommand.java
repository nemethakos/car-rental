package com.msci.carrental.client.interpreter.command;

import java.util.Date;
import java.util.List;

import javax.xml.ws.soap.SOAPFaultException;

import com.msci.carrental.client.interpreter.BookingHandlerInterface;
import com.msci.carrental.client.interpreter.CommandHandlerInterface;
import com.msci.carrental.client.interpreter.CommandResult;
import com.msci.carrental.client.util.Util;
import com.msci.carrental.client.ws.BookingRequest;
import com.msci.carrental.client.ws.CarRentalServiceInterface;
import com.msci.carrental.client.ws.CarType;
import com.msci.carrental.client.ws.Country;

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
				doARandomBooking(i, result);
			}
			result.addMessage(String.valueOf(NUMBER_OF_BOOKINGS) + " bookings sent...");
		}
		return result;
	}

	private void doARandomBooking(int i, CommandResult commandResult) {
		BookingRequest bookingRequest = getRandomBookingRequest(i);
		
		long bookingId = 0;
		try {
			bookingId = service.bookACar(bookingRequest);
			addBookingIdToThePollingQueue(bookingId);
		} catch (SOAPFaultException sfe) {
			Util.handleSoapFault(bookingRequest, sfe, commandResult);
		}
		

	}

	private static BookingRequest getRandomBookingRequest(int i) {

		CarType carType = Util.getARandomCarType();
		Date startDate = Util.normalizeDate(new Date(Util.getRandomMonthInterval(System.currentTimeMillis())));
		Date endDate = Util.normalizeDate(new Date(Util.getRandomMonthInterval(startDate.getTime())));
		List<Country> countries = Util.getRandomCountryList();
		BookingRequest result = Util.getBookingRequest(countries, carType, startDate, endDate);
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
	
	public void addBookingIdToThePollingQueue(long serviceResult) {
		if (bookingHandlerInterface != null) {
			bookingHandlerInterface.addBookingIdToThePollerQueue(serviceResult);
		}
	}
	private BookingHandlerInterface bookingHandlerInterface;

	@Override
	public void setBookingHandler(BookingHandlerInterface bookingHandlerInterface) {
		this.bookingHandlerInterface = bookingHandlerInterface;
	}

}
