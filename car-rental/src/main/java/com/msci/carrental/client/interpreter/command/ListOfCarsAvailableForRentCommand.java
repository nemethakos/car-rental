package com.msci.carrental.client.interpreter.command;

import java.util.List;

import com.msci.carrental.client.interpreter.CommandHandlerInterface;
import com.msci.carrental.client.interpreter.CommandResult;
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
		result.addMessage("List of car type codes available for rent:");
		service.getAvailableCarsForRental().stream().forEach(
				carType->result.addMessage(
						"Type: " + carType.getCarType().name() + 
						", Country: " + carType.getCountry() + 
						", Number Plate:" + carType.getNumberPlate()));
		
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

}
