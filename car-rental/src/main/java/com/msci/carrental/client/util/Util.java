package com.msci.carrental.client.util;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;

import com.msci.carrental.client.interpreter.CommandResult;
import com.msci.carrental.client.ws.BookingRequest;
import com.msci.carrental.client.ws.BookingResult;
import com.msci.carrental.client.ws.BookingStatus;
import com.msci.carrental.client.ws.CarInstance;
import com.msci.carrental.client.ws.CarType;
import com.msci.carrental.client.ws.Country;

public class Util {
	public static final String DATE_FORMAT_STRING = "yyyyMMdd";

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_STRING);

	public static final long ONE_MONTH_IN_MILLISECONDS = 1000L * 60L * 60L * 24L * 30L;

	private static final Random RANDOM = new Random();

	private static Logger log = Logger.getLogger(Util.class.getName());

	public static Calendar toCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static Date normalizeDate(Date date) {
		Calendar calendar = toCalendar(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date result = calendar.getTime();
		return result;
	}

	public static void addAllBookingsTo(CommandResult result, List<BookingResult> allBookings) {
		allBookings.stream().forEach(booking -> {
			BookingRequest br = booking.getBookingRequest();
			BookingStatus bs = booking.getBookingStatus();

			if (bs.isSuccessfulBooking()) {
				result.addMessage(getBoldText("Successful booking!"));
			}
			else {
				result.addMessage(getBoldText("Unsuccessful booking!"));
			}
			
			result.addMessage("Booking Id: " + getBoldText(String.valueOf(booking.getReference())));
			result.addMessage("Booking interval: " + getBoldText(getDateString(br.getStartDate())) + " - "
					+ getBoldText(getDateString(br.getEndDate())));
			result.addMessage("Desired car type: " + getBoldText(br.getCarType().name()));
			if (bs.isSuccessfulBooking()) {
				for (CarInstance i : booking.getCarInstances()) {
					result.addMessage("Country: "+ getBoldText(""+i.getCountry()) + " - Number Plate: " + getBoldText(i.getNumberPlate()));
				}
			} else {
				result.addMessage("Reason of unsuccessful booking: " + getBoldText("" + bs.getDescription()));
			}
			result.addMessage("");
		});
	}

	public static void addBookingResultTo(CommandResult result, BookingResult bookingResult) {
		List<BookingResult> bookingList = new ArrayList<BookingResult>(1);
		bookingList.add(bookingResult);
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

	public static String getDateString(XMLGregorianCalendar xmlGregorianCalendar) {
		XMLGregorianCalendar calendar = null;
		try {
			calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(xmlGregorianCalendar.getYear(),
					xmlGregorianCalendar.getMonth(), xmlGregorianCalendar.getDay(), DatatypeConstants.FIELD_UNDEFINED,
					DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED,
					DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED);
		} catch (DatatypeConfigurationException e) {
			log.log(Level.SEVERE, "Error converting XMLGregorianCalendar to String", e);
		}

		return calendar.toString();
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

	public static XMLGregorianCalendar getXmlGregorianCalendar(Date date) {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		XMLGregorianCalendar result = null;
		try {
			result = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (DatatypeConfigurationException e) {
			log.log(Level.SEVERE, "Error converting date to XMLGregorianCalendar!", e);
		}
		return result;
	}

	public static BookingRequest getBookingRequest(List<Country> countries, CarType carType, Date startDate,
			Date endDate) {
		BookingRequest bookingRequest = new BookingRequest();
		bookingRequest.setCarType(carType);
		bookingRequest.setStartDate(getXmlGregorianCalendar(startDate));
		bookingRequest.setEndDate(getXmlGregorianCalendar(endDate));
		bookingRequest.getCountries().addAll(countries);
		return bookingRequest;
	}

	public static void handleSoapFault(BookingRequest bookingRequest, SOAPFaultException sfe,
			CommandResult commandResult) {
		SOAPFault fault = sfe.getFault();
		String faultString = null;
		if (fault != null) {
			faultString = fault.getFaultString();
		}
		commandResult
				.addError("Soap Fault received for booking: Car Type: " + bookingRequest.getCarType() + ", Countries: "
						+ bookingRequest.getCountries() + ", Dates: " + getDateString(bookingRequest.getStartDate())
						+ " - " + getDateString(bookingRequest.getEndDate()) + " , Fault: " + faultString);

	}

}
