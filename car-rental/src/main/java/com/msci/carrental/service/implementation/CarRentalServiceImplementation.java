package com.msci.carrental.service.implementation;

import java.util.List;

import com.msci.carrental.service.BookingRequest;
import com.msci.carrental.service.BookingResultReceiverInterface;
import com.msci.carrental.service.CarRentalServiceInterface;
import com.msci.carrental.service.model.CarSpecification;
import com.msci.carrental.service.model.CarType;

public class CarRentalServiceImplementation implements CarRentalServiceInterface{
	
	@Override
	public List<CarType> getAvailableCarsForRental() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarSpecification getDetailedSpecificationForACar(CarType carType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long bookACar(BookingRequest bookingRequest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBookingResultReceiver(BookingResultReceiverInterface bookingResultReceiver) {
		// TODO Auto-generated method stub
		
	}

}
