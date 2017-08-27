package com.msci.carrental.client.interpreter.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.msci.carrental.client.interpreter.CommandHandlerInterface;
import com.msci.carrental.client.interpreter.CommandResult;
import com.msci.carrental.client.util.Util;
import com.msci.carrental.service.CarRentalServiceInterface;
import com.msci.carrental.service.model.BookingRequest;
import com.msci.carrental.service.model.CarType;
import com.msci.carrental.service.model.Country;

public class BookACarCommand implements CommandHandlerInterface {

	@Override
	public String getCommandName() {
		return "book";
	}

	@Override
	public CommandResult invoke(List<String> parameters) {

		CommandResult result = new CommandResult();
		List<Country> countries = new ArrayList<>();

		if (parameters.size() < 3) {
			result.addError("Insufficient number of parameters! At least 3 parameters are needed.");
			result.addError("Usage: ");
			result.addError(getCommandName() + " " + getParameterDescription());
			result.getErrors().addAll(getCommandDescription());
		}

		if (!result.isError()) {
			CarType carType = Util.getCarTypeFromCarTypeCode(parameters.get(0));
			Date startDate = Util.parseDate(result, parameters.get(1),
					"Invalid start date: '{0}'. Valid date format: "+Util.DATE_FORMAT_STRING + " (e.g.: 20170826)");
			Date endDate = Util.parseDate(result, parameters.get(2),
					"Invalid end date: '{0}'. Valid date format: yyyyMMdd (e.g.: 20170901)");
			if (parameters.size() > 3) {
				countries = Util.parseCountries(result, parameters.subList(3, parameters.size()));
			}
			if (!result.isError()) {
				BookingRequest bookingRequest = new BookingRequest(carType, startDate, endDate, countries);
				long serviceResult = service.bookACar(bookingRequest);
				result.addMessage("Booking request queued with reference id: " + serviceResult);
			}
		}
		return result;
	}

	@Override
	public List<String> getCommandDescription() {

		ArrayList<String> result = new ArrayList<>();
		result.add(" *car codes: " + String.join(", ", Util.getListOfCarTypeCodes()));
		result.add(" **yyyyMMdd");
		result.add(" ***countries (optional): " + String.join(", ", Util.getCountryList()));
		result.addAll(
				Arrays.asList(". Example: " + Util.getCodeText("book " + CarType.CCMR + " " + "20180101 20180130 hu de")
						+ " " + Util.getItalicText("(you can copy and paste this to command line.)")));
		
		result.add(Util.getItalicText("Note: ") + "Booking for " + CarRentalServiceInterface.INLAND_COUNTRY + " takes "
				+ CarRentalServiceInterface.BOOKING_DELAY_FOR_INLAND_IN_SECONDS
				+ "s; if there is another country in the country list, the booking takes "
				+ CarRentalServiceInterface.BOOKING_DELAY_FOR_FOREIGN_COUNTRIES_IN_SECONDS + "s");
		return result;
	}

	@Override
	public List<String> getParameterDescription() {
		ArrayList<String> result = new ArrayList<>();
		result.addAll(Arrays.asList("car-code*", "from-date**", "to-date**", "[country1 country2 ... countryN]***"));
		return result;
	}

	private CarRentalServiceInterface service;

	@Override
	public void setCarRentalService(CarRentalServiceInterface carRentalServiceInterface) {
		this.service = carRentalServiceInterface;
	}

	@Override
	public String getTagLine() {
		return "Books a car";
	}

}
