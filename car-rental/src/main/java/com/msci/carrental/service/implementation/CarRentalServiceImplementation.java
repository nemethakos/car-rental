package com.msci.carrental.service.implementation;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;

import com.msci.carrental.service.CarRentalServiceInterface;
import com.msci.carrental.service.model.BookingRequest;
import com.msci.carrental.service.model.BookingResult;
import com.msci.carrental.service.model.CarInstance;
import com.msci.carrental.service.model.CarSpecification;
import com.msci.carrental.service.model.CarType;

@WebService(endpointInterface = "com.msci.carrental.service.CarRentalServiceInterface")
public class CarRentalServiceImplementation implements CarRentalServiceInterface {

	CarRentalServiceBusinessLogicImplementation businessLogic;
	static Logger log = Logger.getLogger(CarRentalServiceImplementation.class.getName());
	SOAPFactory soapFactory = null;

	@Override
	public List<CarInstance> getAvailableCarsForRental() {

		List<CarInstance> result = businessLogic.getAvailableCarsForRental();
		log.info(result.toString());
		return result;
	}

	@Override
	public long bookACar(BookingRequest bookingRequest) {

		long result = 0;

		if (bookingRequest == null) {
			throw new SOAPFaultException(getFault("bookingRequest should be non null!"));
		} else {

			log.info(bookingRequest.toString());
			String validationMessage = bookingRequest.getValidationMessage();
			if (!validationMessage.isEmpty()) {
				throw new SOAPFaultException(getFault(validationMessage));
			} else {
				result = businessLogic.bookACar(bookingRequest);
			}
		}
		return result;

	}

	private SOAPFault getFault(String message) {
		SOAPFault result = null;
		try {
			result = soapFactory.createFault(message, new QName("http://schemas.xmlsoap.org/soap/envelope/", "Client"));
		} catch (SOAPException e) {
			log.log(Level.SEVERE, "Error creating SOAPFault", e);
		}

		return result;
	}

	public CarRentalServiceImplementation() {
		super();
		businessLogic = CarRentalServiceBusinessLogicImplementation.newInstance();

		try {
			soapFactory = SOAPFactory.newInstance();
		} catch (SOAPException e) {
			log.log(Level.SEVERE, "Error creating SOAPFactory", e);
		}
	}

	@Override
	public CarSpecification getDetailedSpecificationForACarType(CarType carType) {
		if (carType != null) {
			CarSpecification carSpecification = businessLogic.getDetailedSpecificationForACarType(carType);
			log.info(carSpecification.toString());
			return carSpecification;
		} else {
			throw new SOAPFaultException(getFault("carType should not be null!"));
		}
	}

	@Override
	public List<BookingResult> getBookingResultsForIds(List<Long> bookingIdList) {
		if (bookingIdList == null) {
			throw new SOAPFaultException(getFault("Booking Id list should be non null!"));
		} else {
			List<BookingResult> bookingsForIds = businessLogic.getBookingResultsForIds(bookingIdList);
			log.info(bookingsForIds.toString());
			return bookingsForIds;
		}
	}

}
