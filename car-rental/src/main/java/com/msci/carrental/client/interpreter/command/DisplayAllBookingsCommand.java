package com.msci.carrental.client.interpreter.command;

import java.util.ArrayList;
import java.util.List;

import com.msci.carrental.client.interpreter.BookingHandlerInterface;
import com.msci.carrental.client.interpreter.CommandHandlerInterface;
import com.msci.carrental.client.interpreter.CommandResult;
import com.msci.carrental.client.util.Util;
import com.msci.carrental.client.ws.BookingResult;
import com.msci.carrental.client.ws.CarRentalServiceInterface;



public class DisplayAllBookingsCommand implements CommandHandlerInterface {
	private CarRentalServiceInterface service;

	@Override
	public void setCarRentalService(CarRentalServiceInterface carRentalServiceInterface) {
		this.service = carRentalServiceInterface;
	}

	@Override
	public String getCommandName() {
		return "bookings";
	}

	@Override
	public CommandResult invoke(List<String> parameters) {
		CommandResult result = new CommandResult();

		if (!parameters.isEmpty()) {
			result.addError("No parameters allowed!");
		} else {
			List<BookingResult> allBookings = service.getBookingResultsForIds(new ArrayList<>());
			if (allBookings.isEmpty()) {
				result.addMessage("No bookings yet!");
			} else {
				result.addMessage(String.valueOf(allBookings.size()) + " Bookings:");
				Util.addAllBookingsTo(result, allBookings);
			}
		}
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

		return "Returns the list of all bookings from the server (both successful and unsuccessful)";
	}

	@Override
	public void setBookingHandler(BookingHandlerInterface bookingHandlerInterface) {
	}

}
