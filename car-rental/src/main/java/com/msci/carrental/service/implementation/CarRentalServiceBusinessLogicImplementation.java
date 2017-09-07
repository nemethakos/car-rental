package com.msci.carrental.service.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.joda.time.Interval;

import com.msci.carrental.service.CarRentalServiceInterface;
import com.msci.carrental.service.model.BookingRequest;
import com.msci.carrental.service.model.BookingResult;
import com.msci.carrental.service.model.BookingSearchResult;
import com.msci.carrental.service.model.BookingStatus;
import com.msci.carrental.service.model.CarInstance;
import com.msci.carrental.service.model.CarSpecification;
import com.msci.carrental.service.model.CarType;
import com.msci.carrental.service.model.Country;

public class CarRentalServiceBusinessLogicImplementation implements CarRentalServiceInterface {

	private static final Logger log = Logger.getLogger(CarRentalServiceBusinessLogicImplementation.class.getName());

	protected static final int THREAD_POOL_SIZE = 20;

	private static final int NUMBER_OF_CAR_INSTANCES_PER_CAR_TYPE = 5;

	protected AtomicLong bookingIdGenerator = new AtomicLong();

	protected ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(THREAD_POOL_SIZE);

	/**
	 * Locking object for manipulating the booking lists and for querying the lists
	 */
	protected Object bookingLock = new Object();

	/**
	 * Result of all bookings (successful and unsuccessful)
	 */
	protected Map<Long, BookingResult> bookingResultMap = new HashMap<Long, BookingResult>();

	/**
	 * List of car instances available for booking
	 */
	protected List<CarInstance> bookableCarList = new ArrayList<>();

	/**
	 * Generates a list of bookable cars.
	 * 
	 * @return the {@link List} of bookable {@link CarInstance}s
	 */
	public static List<CarInstance> generateCarInstances() {
		List<CarInstance> result = new ArrayList<>();

		for (Country country : Country.values()) {
			int index = 1;
			for (CarType carType : CarType.values()) {
				for (int j = 0; j < NUMBER_OF_CAR_INSTANCES_PER_CAR_TYPE; j++) {
					String numberPlate = country.name() + index++;
					CarInstance car = new CarInstance(carType, country, numberPlate);
					result.add(car);
				}
			}
		}

		return result;
	}

	/**
	 * Returns the list of bookable cars for the given {@link CarType} for the given
	 * {@link Country}s for the given time interval is found available in the
	 * bookings.
	 * 
	 * @param bookingRequest
	 *            the {@link BookingRequest}
	 * @return the {@link BookingSearchResult}
	 */
	protected BookingSearchResult getListOfBookableCars(BookingRequest bookingRequest) {

		BookingSearchResult result = new BookingSearchResult();

		List<Country> countries = bookingRequest.getCountries();
		CarType carType = bookingRequest.getCarType();
		Date startDate = bookingRequest.getStartDate();
		Date endDate = bookingRequest.getEndDate();
		for (Country country : countries) {
			List<CarInstance> availableCars = getCarsAvailableForCountry(country, carType);
			List<CarInstance> bookedCars = getCarsBookedForInterval(country, carType, startDate, endDate);
			availableCars.removeAll(bookedCars);
			result.put(country, availableCars);
		}

		return result;
	}

	/**
	 * Returns the available {@link CarInstance}s for the given {@link CarType}s for
	 * the given {@link Country}
	 * 
	 * @param carType
	 *            the {@link CarType}
	 * @param country
	 *            the {@link Country}
	 * @return all of the cars available for the {@link Country} (if none were
	 *         booked)
	 */
	protected List<CarInstance> getCarsAvailableForCountry(Country country, CarType carType) {
		List<CarInstance> result = new ArrayList<>();
		for (CarInstance carInstance : bookableCarList) {
			boolean isCountryMatch = carInstance.getCountry().equals(country);
			boolean isCarTypeMatch = carInstance.getCarType().equals(carType);
			if (isCountryMatch && isCarTypeMatch) {
				result.add(carInstance);
			}
		}
		return result;
	}

	/**
	 * Returns the {@link CarInstance}s booked for the {@link Country},
	 * {@link CarType} between <code>startDate</code> and <code>endDate</code>
	 * 
	 * @param country
	 *            the desired {@link Country} for booking
	 * @param carType
	 *            the desired {@link CarType} for booking
	 * @param startDate
	 *            the desired start {@link Date} of booking interval
	 * @param endDate
	 *            the desired end {@link Date} of booking interval
	 * @return the {@link List} of {@link CarInstance}s already booked according to
	 *         the parameters.
	 */
	protected List<CarInstance> getCarsBookedForInterval(Country country, CarType carType, Date startDate,
			Date endDate) {
		List<CarInstance> result = new ArrayList<>();

		for (BookingResult bookingResult : bookingResultMap.values()) {
			List<CarInstance> carInstances = bookingResult.getCarInstances();
			for (CarInstance bookedCarInstance : carInstances) {

				BookingRequest bookingRequest = bookingResult.getBookingRequest();
				boolean isCarTypeMatching = bookedCarInstance.getCarType().equals(carType);
				boolean isCountryMatching = bookedCarInstance.getCountry().equals(country);
				Date start = bookingRequest.getStartDate();
				Date end = bookingRequest.getEndDate();
				Interval bookingInterval = new Interval(start.getTime(), end.getTime());
				Interval desiredInterval = new Interval(startDate.getTime(), endDate.getTime());
				boolean isBookingIntervalAndDesiredIntervalOverlaps = bookingInterval.overlaps(desiredInterval);
				if (isCarTypeMatching && isCountryMatching && isBookingIntervalAndDesiredIntervalOverlaps) {
					result.add(bookedCarInstance);
				}
			}
		}

		return result;
	}

	@Override
	public long bookACar(BookingRequest bookingRequest) {
		long result = bookingIdGenerator.getAndIncrement();

		Runnable task = () -> {
			performBooking(result, bookingRequest);
		};

		long delayForBooking = getDelayForBookingInSeconds(bookingRequest);

		executor.schedule(task, delayForBooking, TimeUnit.SECONDS);

		return result;
	}

	protected void performBooking(long bookingId, BookingRequest bookingRequest) {
		synchronized (bookingLock) {
			try {
				if (bookingRequest.getCountries().isEmpty()) {
					bookingRequest.getCountries().add(INLAND_COUNTRY);
				}
				BookingSearchResult availableCars = getListOfBookableCars(bookingRequest);
				BookingStatus bookingStatus = availableCars.getBookingStatus(bookingRequest.getCountries());
				List<CarInstance> carInstanceList = new ArrayList<CarInstance>();
				if (bookingStatus.isSuccessfulBooking()) {
					carInstanceList.addAll(getCarInstanceForEveryCountry(availableCars));
				}

				BookingResult bookingResult = new BookingResult(bookingId, bookingRequest, bookingStatus,
						carInstanceList);
				bookingResultMap.put(new Long(bookingId), bookingResult);
			} catch (Throwable e) {
				log.log(Level.SEVERE, "Error during booking", e);
			}
		}
	}

	protected static List<CarInstance> getCarInstanceForEveryCountry(BookingSearchResult bookingSearchResult) {

		List<CarInstance> result = new ArrayList<>();

		for (Country country : bookingSearchResult.keySet()) {
			CarInstance carForCountry = bookingSearchResult.get(country).get(0);
			result.add(carForCountry);
		}

		return result;

	}

	@Override
	public List<BookingResult> getBookingResultsForIds(List<Long> bookingIdList) {

		synchronized (bookingLock) {
			List<BookingResult> result = new ArrayList<>();
			if (bookingIdList.isEmpty()) {
				return new ArrayList<BookingResult>(bookingResultMap.values());
			} else {
				for (Long id : bookingIdList) {
					BookingResult br = bookingResultMap.get(id);
					if (br != null) {
						result.add(br);
					}
				}
			}
			return result;
		}
	}

	@Override
	public CarSpecification getDetailedSpecificationForACarType(CarType carType) {
		CarSpecification carSpecification = new CarSpecification(carType);
		return carSpecification;
	}

	public static CarRentalServiceBusinessLogicImplementation newInstance() {
		return new CarRentalServiceBusinessLogicImplementation();
	}

	private CarRentalServiceBusinessLogicImplementation() {
		super();
		bookingIdGenerator.set(START_ID);
		bookableCarList = generateCarInstances();
	}

	@Override
	public List<CarInstance> getAvailableCarsForRental() {
		return bookableCarList;
	}

	protected static long getDelayForBookingInSeconds(BookingRequest bookingRequest) {
		long result = 0;

		List<Country> countries = bookingRequest.getCountries();
		boolean hasForeignCountry = false;
		for (Country country : countries) {
			if (country != null && !country.equals(INLAND_COUNTRY)) {
				hasForeignCountry = true;
				break;
			}
		}

		if (hasForeignCountry) {
			result = BOOKING_DELAY_FOR_FOREIGN_COUNTRIES_IN_SECONDS;
		} else {
			result = BOOKING_DELAY_FOR_INLAND_IN_SECONDS;
		}

		return result;
	}

}
