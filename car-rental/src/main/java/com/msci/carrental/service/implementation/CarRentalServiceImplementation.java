package com.msci.carrental.service.implementation;

import java.util.Arrays;
import java.util.List;

import com.msci.carrental.service.BookingRequest;
import com.msci.carrental.service.BookingResultReceiverInterface;
import com.msci.carrental.service.CarRentalServiceInterface;
import com.msci.carrental.service.model.CarSpecification;
import com.msci.carrental.service.model.CarType;

public class CarRentalServiceImplementation implements CarRentalServiceInterface{
	
	private long id;
	private BookingResultReceiverInterface bookingResultReceiver;
	
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

		return getNextId();
	
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
