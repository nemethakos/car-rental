package com.msci.carrental.client.util;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import com.msci.carrental.client.interpreter.CommandResult;
import com.msci.carrental.service.model.Booking;
import com.msci.carrental.service.model.BookingRequest;
import com.msci.carrental.service.model.BookingResult;
import com.msci.carrental.service.model.CarType;
import com.msci.carrental.service.model.Country;

public class Util {
	public static final String DATE_FORMAT_STRING = "yyyyMMdd";
	
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_STRING);

	public static final long ONE_MONTH_IN_MILLISECONDS = 1000L * 60L * 60L * 24L * 30L;

	private static final Random RANDOM = new Random();

	public static void addAllBookingsTo(CommandResult result, List<Booking> allBookings) {
		allBookings.stream().forEach(booking -> {
			BookingRequest b = booking.getBookingRequest();
			result.addMessage("Booking Id: " + getBoldText(String.valueOf(booking.getId())));
			result.addMessage("Car Code Type: " + getBoldText(b.getCarType().name()));
			result.addMessage("Start Date: " + getBoldText(getDateString(b.getStartDate())));
			result.addMessage("End Date: " + getBoldText(getDateString(b.getEndDate())));
			result.addMessage("Countries: " + getBoldText(String.join(", ", getStringList(b.getCountries()))));
			result.addMessage("");
		});
	}

	public static void addBookingResultTo(CommandResult result, BookingResult bookingResult) {
		Booking booking = new Booking(bookingResult.getReference(), bookingResult.getBookingRequest());
		List<Booking> bookingList = new ArrayList<Booking>(1);
		bookingList.add(booking);
		addAllBookingsTo(result, bookingList);
	}

	public static Consumer<? super String> appendLinesIntoOneLineInItalic(CommandResult result) {
		return line -> {
			List<String> messages = result.getMessages();
			int indexOfLastLine = messages.size() - 1;
			String lastLine = messages.get(indexOfLastLine);
			String newLastLine = lastLine + " " + Util.getItalicText(line);
			messages.set(indexOfLastLine, newLastLine);
		};
	}

	public static void appendTextToLastLine(CommandResult result, String line) {
		List<String> messages = result.getMessages();
		int indexOfLastLine = messages.size() - 1;
		String lastLine = messages.get(indexOfLastLine);
		String newLastLine = lastLine + " " + line;
		messages.set(indexOfLastLine, newLastLine);
	}

	/**
	 * Decorates the plain text with markings into decorated (e.g.: HTML) text
	 * 
	 * @param text
	 *            the input text
	 * @param decorator
	 *            the {@link TextDecoratorInterface}
	 * @return the decorated text
	 */
	public static String decorate(String text, TextDecoratorInterface decorator) {
		String result = new String(text);
		for (DecorationType decorationType : DecorationType.values()) {
			result = result.replace(decorationType.getMarking(), decorator.getDecorationFor(decorationType));
		}
		return result;
	}

	public static CarType getARandomCarType() {
		CarType result = CarType.values()[RANDOM.nextInt(CarType.values().length)];
		return result;
	}

	public static String getBoldText(String text) {
		return DecorationType.BOLD_START.getMarking() + text + DecorationType.BOLD_END.getMarking();
	}

	public static CarType getCarTypeFromCarTypeCode(String carTypeCode) {
		CarType result = null;
		try {
			result = CarType.valueOf(carTypeCode.trim().toUpperCase());
		} catch (IllegalArgumentException e) {
			// not found;
		}
		return result;

	}

	public static String getCodeText(String text) {
		return DecorationType.CODE_START.getMarking() + text + DecorationType.CODE_END.getMarking();
	}

	public static Country getCountryFromCountryCode(String countryCode) {
		Country result = null;

		try {
			result = Country.valueOf(countryCode);
		} catch (IllegalArgumentException e) {
			// not found
		}

		return result;
	}

	public static List<String> getCountryList() {
		List<String> result = new ArrayList<>();

		Arrays.asList(Country.values()).stream().forEach(country -> result.add(country.name()));

		return result;

	}

	public static String getDateString(Date b) {
		return DATE_FORMAT.format(b);
	}

	public static String getHeadText(String text) {
		return DecorationType.HEAD_START.getMarking() + text + DecorationType.HEAD_END.getMarking();
	}

	public static String getItalicText(String text) {
		return DecorationType.ITALIC_START.getMarking() + text + DecorationType.ITALIC_END.getMarking();

	}

	public static List<String> getListOfCarTypeCodes() {
		List<String> result = new ArrayList<>();

		Arrays.asList(CarType.values()).stream().forEach(carType -> result.add(carType.name()));

		return result;
	}

	public static List<Country> getRandomCountryList() {
		List<Country> result = new ArrayList<>();
		Arrays.asList(Country.values()).stream().forEach(country -> {
			if (RANDOM.nextBoolean()) {
				result.add(country);
			}
		});
		return result;
	}

	public static long getRandomMonthInterval(long start) {
		long randomNumberBound = start + Util.ONE_MONTH_IN_MILLISECONDS;
		return RANDOM.longs(1, start, randomNumberBound).findFirst().getAsLong();
	}

	public static List<String> getStringList(List<Country> countries) {
		List<String> result = new ArrayList<String>();

		countries.stream().forEach(country -> result.add(country.name()));

		return result;
	}

	public static List<Country> parseCountries(CommandResult result, List<String> countries) {
		List<Country> countryResult = new ArrayList<>();

		for (String countryCode : countries) {
			Country country = Util.getCountryFromCountryCode(countryCode.toUpperCase());
			if (country == null) {
				result.addError("Invalid country: " + countryCode);
			} else {
				countryResult.add(country);
			}
		}

		return countryResult;
	}

	public static Date parseDate(CommandResult result, String dateToParse, String errorMessage) {
		Date dateResult = null;
		try {
			dateResult = DATE_FORMAT.parse(dateToParse);
		} catch (ParseException e) {
			String formattedErrorMesage = MessageFormat.format(errorMessage, dateToParse);
			result.addError(formattedErrorMesage);
		}

		return dateResult;
	}

}
