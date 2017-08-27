package com.msci.carrental.service.proxy;

import java.util.List;

import com.msci.carrental.service.BookingResultReceiverInterface;
import com.msci.carrental.service.CarRentalServiceInterface;
import com.msci.carrental.service.implementation.CarRentalServiceImplementation;
import com.msci.carrental.service.model.Booking;
import com.msci.carrental.service.model.BookingRequest;
import com.msci.carrental.service.model.CarSpecification;
import com.msci.carrental.service.model.CarType;

public class CarRentalServiceProxy implements CarRentalServiceInterface {

	private CarRentalServiceInterface carRentalServiceImplementation;
	
	private CarRentalServiceProxy(CarRentalServiceInterface carRentalServiceImplementation) {
		super();
		this.carRentalServiceImplementation = carRentalServiceImplementation;
	}

	public static CarRentalServiceProxy getProxy() {
		return new CarRentalServiceProxy(new CarRentalServiceImplementation());
	}
	
	@Override
	public List<CarType> getAvailableCarsForRental() {
		return carRentalServiceImplementation.getAvailableCarsForRental();
	}

	@Override
	public CarSpecification getDetailedSpecificationForACar(CarType carType) {
		return carRentalServiceImplementation.getDetailedSpecificationForACar(carType);
	}

	@Override
	public long bookACar(BookingRequest bookingRequest) {
		return carRentalServiceImplementation.bookACar(bookingRequest);
	}

	@Override
	public void setBookingResultReceiver(BookingResultReceiverInterface bookingResultReceiver) {
		carRentalServiceImplementation.setBookingResultReceiver(bookingResultReceiver);

	}

	@Override
	public List<Booking> getAllBookings() {
		return carRentalServiceImplementation.getAllBookings();
	}

}
