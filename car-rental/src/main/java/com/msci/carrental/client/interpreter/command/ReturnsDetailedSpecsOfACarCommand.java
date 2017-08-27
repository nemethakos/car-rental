package com.msci.carrental.client.interpreter.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.msci.carrental.client.interpreter.CommandHandlerInterface;
import com.msci.carrental.client.interpreter.CommandResult;
import com.msci.carrental.client.util.Util;
import com.msci.carrental.service.CarRentalServiceInterface;
import com.msci.carrental.service.model.CarSpecification;
import com.msci.carrental.service.model.CarType;

public class ReturnsDetailedSpecsOfACarCommand implements CommandHandlerInterface {
	private CarRentalServiceInterface service;

	@Override
	public void setCarRentalService(CarRentalServiceInterface carRentalServiceInterface) {
		this.service = carRentalServiceInterface;
	}

	@Override
	public String getCommandName() {
		return "show";
	}

	@Override
	public CommandResult invoke(List<String> parameters) {
		CommandResult result = new CommandResult();

		if (parameters.size() == 0) {
			result.addError("Missing car type specification!");
			addUsageInfo(result);
		} else if (parameters.size() > 1) {
			result.addError("Too many parameter!");
			addUsageInfo(result);
		} else {

			String carCode = parameters.get(0).trim().toUpperCase();
			CarType carType = Util.getCarTypeFromCarTypeCode(carCode);
			if (carType == null) {
				result.addError("Unknown car code: " + carCode);
			} else {
				CarSpecification carSpecification = service.getDetailedSpecificationForACar(carType);
				result.addMessage("Car specification for car code: " + carCode);
				CarType info = carSpecification.getCarType();
				result.addMessage("Description: " + info.getDescription());
				result.addMessage("Group code: " + info.getGroup());
				result.addMessage("Example car: " + info.getExample());
				result.addMessage("Number of passangers: " + info.getNumberOfPassangers());
				result.addMessage("Number of suitcases (small/large)" + info.getNumberOfSmallSuitcases() + "/"
						+ info.getNumberOfLargeSuitcases());
			}
		}
		return result;
	}

	private void addUsageInfo(CommandResult result) {
		result.addError("Usage:");
		result.getErrors().addAll(getParameterDescription());
		result.getErrors().addAll(getCommandDescription());
	}

	@Override
	public List<String> getCommandDescription() {
		ArrayList<String> result = new ArrayList<>();
		result.add("* car codes: " + String.join(", ", Util.getListOfCarTypeCodes()));
		return result;
	}

	@Override
	public List<String> getParameterDescription() {
		ArrayList<String> result = new ArrayList<>();
		result.addAll(Arrays.asList("car-code*"));
		return result;
	}

	@Override
	public String getTagLine() {
		
		return "Returns the detailed specification of a car type";
	}

}
