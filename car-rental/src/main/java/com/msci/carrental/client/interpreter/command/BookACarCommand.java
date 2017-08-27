package com.msci.carrental.client.interpreter.command;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.msci.carrental.client.interpreter.CommandHandlerInterface;
import com.msci.carrental.client.interpreter.CommandResult;
import com.msci.carrental.service.BookingRequest;
import com.msci.carrental.service.CarRentalServiceInterface;
import com.msci.carrental.service.model.CarType;
import com.msci.carrental.service.model.Country;

public class BookACarCommand implements CommandHandlerInterface {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	@Override
	public String getCommandName() {
		return "book";
	}

	@Override
	public CommandResult invoke(List<String> parameters) {

		CommandResult result = new CommandResult();
		List<Country> countries = null;

		if (parameters.size() < 3) {
			result.addError("Insufficient number of parameters! At least 3 parameters are needed.");
			result.addError("Usage: ");
			result.addError(getCommandName() + " " + getParameterDescription());
			result.getErrors().addAll(getCommandDescription());
		}

		if (!result.isError()) {
			CarType carType = CarType.getCarTypeFromCode(parameters.get(0));
			Date startDate = parseDate(result, parameters.get(1), "Invalid start date: '{0}'. Valid date format: yyyyMMdd (e.g.: 20170826)");
			Date endDate = parseDate(result, parameters.get(2), "Invalid end date: '{0}'. Valid date format: yyyyMMdd (e.g.: 20170901)");
			if (parameters.size() > 3) {
				countries = parseCountries(result, parameters.subList(3, parameters.size()));
			}
			if (!result.isError()) {
				BookingRequest bookingRequest = new BookingRequest(carType, startDate, endDate, countries);
				long serviceResult = service.bookACar(bookingRequest);
				result.addMessage("Booking request queued with reference id: " + serviceResult);
			}
		}
		return result;
	}

	private List<Country> parseCountries(CommandResult result, List<String> countries) {
		List<Country> countryResult = new ArrayList<>();

		for (String countryCode: countries) {
			Country country = Country.getCountryFromCountryCode(countryCode.toUpperCase());
			if (country == null) {
				result.addError("Invalid country: " + countryCode);
			}
			else {
				countryResult.add(country);
			}
		}
		
		return countryResult ;
	}

	private Date parseDate(CommandResult result, String dateToParse, String errorMessage) {
		Date dateResult = null;
		try {
			dateResult = dateFormat.parse(dateToParse);
		} catch (ParseException e) {
			String formattedErrorMesage = MessageFormat.format(errorMessage, dateToParse);
			result.addError(formattedErrorMesage);
		}

		return dateResult;
	}

	@Override
	public List<String> getCommandDescription() {
		
		ArrayList<String> result = new ArrayList<>();
		result.add("* car codes: " + String.join(", ", CarType.getListOfCarTypes()));
		result.add("** yyyyMMdd");
		result.add("*** countries (optional): " + String.join(", ", Country.getCountryList()));
		result.addAll(Arrays.asList("Example: book " + CarType.CCMR + " " + "20180101 20180130 hu de "));

		return result;
	}

	@Override
	public List<String> getParameterDescription() {
		ArrayList<String> result = new ArrayList<>();
		result.addAll(Arrays.asList(
				"car-code*", 
				"from-date**", 
				"to-date**", 
				"[country1 country2 ... countryN]***"));
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
