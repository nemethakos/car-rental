package com.msci.carrental.client.interpreter.command;

import java.util.List;

import com.msci.carrental.client.interpreter.BookingHandlerInterface;
import com.msci.carrental.client.interpreter.CommandHandlerInterface;
import com.msci.carrental.client.interpreter.CommandResult;
import com.msci.carrental.client.util.Util;
import com.msci.carrental.client.ws.CarRentalServiceInterface;


public class ListOfCarsAvailableForRentCommand implements CommandHandlerInterface {
	private CarRentalServiceInterface service;
	
	@Override
	public void setCarRentalService(CarRentalServiceInterface carRentalServiceInterface) {
		this.service = carRentalServiceInterface;
	}
	@Override
	public String getCommandName() {
		return "list";
	}

	@Override
	public CommandResult invoke(List<String> parameters) {
		CommandResult result = new CommandResult();
		result.addMessage("List of car instances by country available for rent:");
		service.getAvailableCarsForRental().stream().forEach(
				carType->result.addMessage(
						"Type: " + Util.getBoldText(carType.getCarType().name()) + 
						", Country: " + Util.getBoldText(""+carType.getCountry()) + 
						", Number Plate:" + Util.getBoldText(carType.getNumberPlate())));
		
		return result ;
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

		return "Returns the list of available car types/instances for rent";
	}
	@Override
	public void setBookingHandler(BookingHandlerInterface bookingHandlerInterface) {
	}

}
