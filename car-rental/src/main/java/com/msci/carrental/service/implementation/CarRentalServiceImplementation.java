package com.msci.carrental.service.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.msci.carrental.service.BookingResultReceiverInterface;
import com.msci.carrental.service.CarRentalServiceInterface;
import com.msci.carrental.service.model.Booking;
import com.msci.carrental.service.model.BookingRequest;
import com.msci.carrental.service.model.BookingResult;
import com.msci.carrental.service.model.BookingStatus;
import com.msci.carrental.service.model.CarSpecification;
import com.msci.carrental.service.model.CarType;
import com.msci.carrental.service.model.Country;

public class CarRentalServiceImplementation implements CarRentalServiceInterface{
	
	private static final int THREAD_POOL_SIZE = 20;
	private AtomicLong bookingId = new AtomicLong();
	private BookingResultReceiverInterface bookingResultReceiver;
	ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(THREAD_POOL_SIZE);
	List<Booking> bookingList = Collections.synchronizedList(new ArrayList<Booking>());
	
	@Override
	public List<CarType> getAvailableCarsForRental() {
		
		return Arrays.asList(CarType.values());
	}

	@Override
	public CarSpecification getDetailedSpecificationForACar(CarType carType) {
		
		return new CarSpecification(carType);
	}

	@Override
	public long bookACar(BookingRequest bookingRequest) {
		
		long result = bookingId.getAndIncrement();
		
		if (bookingRequest.getCountries().isEmpty()) {
			bookingRequest.getCountries().add(INLAND_COUNTRY);
		}
		
		Runnable task = () -> {
			bookingList.add(new Booking(result, bookingRequest));
			BookingResult bookingResult = new BookingResult(result, bookingRequest, BookingStatus.SUCCESS);
			bookingResultReceiver.receiveBookingResult(bookingResult);
		};
		
		long delayForBooking = getDelayForBookingInSeconds(bookingRequest);
		
		executor.schedule(task, delayForBooking, TimeUnit.SECONDS);
		
		return result;
	
	}


	private static long getDelayForBookingInSeconds(BookingRequest bookingRequest) {
		long result = 0;
		
		List<Country> countries = bookingRequest.getCountries();
		boolean hasForeignCountry = false;
		for (Country country: countries) {
			if (!country.equals(INLAND_COUNTRY)) {
				hasForeignCountry = true;
				break;
			}
		}
		
		if (hasForeignCountry) {
			result = BOOKING_DELAY_FOR_FOREIGN_COUNTRIES_IN_SECONDS;
		}
		else {
			result = BOOKING_DELAY_FOR_INLAND_IN_SECONDS;
		}

		return result;
	}

	public CarRentalServiceImplementation() {
		super();
		bookingId.set(START_ID);
		
	}

	@Override
	public void setBookingResultReceiver(BookingResultReceiverInterface bookingResultReceiver) {
		this.bookingResultReceiver = bookingResultReceiver;
		
	}

	@Override
	public List<Booking> getAllBookings() {
		return new ArrayList<Booking>(bookingList);
	}

}
