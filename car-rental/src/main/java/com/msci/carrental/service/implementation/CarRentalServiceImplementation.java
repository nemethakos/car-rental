package com.msci.carrental.service.implementation;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.msci.carrental.service.BookingRequest;
import com.msci.carrental.service.BookingResult;
import com.msci.carrental.service.BookingResultReceiverInterface;
import com.msci.carrental.service.BookingStatus;
import com.msci.carrental.service.CarRentalServiceInterface;
import com.msci.carrental.service.model.CarSpecification;
import com.msci.carrental.service.model.CarType;

public class CarRentalServiceImplementation implements CarRentalServiceInterface{
	
	private static final int THREAD_POOL_SIZE = 20;
	private long id;
	private BookingResultReceiverInterface bookingResultReceiver;
	ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(THREAD_POOL_SIZE);
	
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
		
		long id = getNextId();
				
		
		Runnable task = () -> {
			System.out.println("Scheduling: " + id + " " + System.nanoTime());
			bookingResultReceiver.receiveBookingResult(new BookingResult(id, bookingRequest, BookingStatus.SUCCESS));
		};
		
		ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);
		
		
		
		return id;
	
	}

	private long getNextId() {
		long result = id;
		
		id = id + 1;
		
		return result ;
	}

	public CarRentalServiceImplementation() {
		super();
		this.id = 1;
		
	}

	@Override
	public void setBookingResultReceiver(BookingResultReceiverInterface bookingResultReceiver) {
		this.bookingResultReceiver = bookingResultReceiver;
		
	}

}
