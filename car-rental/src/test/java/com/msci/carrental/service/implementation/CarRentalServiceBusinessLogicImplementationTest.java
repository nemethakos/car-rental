package com.msci.carrental.service.implementation;

import static org.junit.Assert.*;

import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;

import com.msci.carrental.service.model.CarInstance;

public class CarRentalServiceBusinessLogicImplementationTest {

	private static Logger log = Logger.getLogger(CarRentalServiceBusinessLogicImplementation.class.getName());
	
	CarRentalServiceBusinessLogicImplementation imp = CarRentalServiceBusinessLogicImplementation.newInstance();
	
	@Test
	public void testGetListOfBookableCars() {
		List<CarInstance> cars = CarRentalServiceBusinessLogicImplementation.generateCarInstances();
		assertNotNull(cars);
		log.info(cars.toString());
	}
/*
	@Test
	public void testBookACar() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllBookings() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBookingsForIds() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDetailedSpecificationForACarType() {
		fail("Not yet implemented");
	}

	@Test
	public void testNewInstance() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAvailableCarsForRental() {
		fail("Not yet implemented");
	}
*/
}
