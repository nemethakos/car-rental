package com.msci.carrental.client.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class WebServiceProxy implements CarRentalServiceInterface {

	private static Logger log = Logger.getLogger(WebServiceProxy.class.getName());

	private static final String CAR_RENTAL_SERVICE_URL = "http://localhost:8080/WS/CarRental?wsdl";

	public static String getConnectionURL() {
		String result = CAR_RENTAL_SERVICE_URL.split("\\?")[0];
		return result;
	}

	private static final String CAR_RENTAL_SERVICE_LOCAL_PART = "CarRentalServiceImplementationService";

	private static final String CAR_RENTAL_SERVICE_NAMESPACE_URI = "http://implementation.service.carrental.msci.com/";

	private CarRentalServiceInterface port = null;

	private static WebServiceProxy instance = null;

	public static WebServiceProxy getInstance() throws MalformedURLException {
		if (instance == null) {

			instance = new WebServiceProxy();
			log.info("Web Service Proxy successfully created for " + getConnectionURL());
		}
		return instance;
	}

	private WebServiceProxy() throws MalformedURLException {
		super();

		QName qname = new QName(CAR_RENTAL_SERVICE_NAMESPACE_URI, CAR_RENTAL_SERVICE_LOCAL_PART);
		URL url = new URL(CAR_RENTAL_SERVICE_URL);
		Service service = Service.create(url, qname);
		port = service.getPort(CarRentalServiceInterface.class);
	}

	@Override
	public CarSpecification getDetailedSpecificationForACarType(CarType arg0) {
		return port.getDetailedSpecificationForACarType(arg0);

	}

	@Override
	public List<CarInstance> getAvailableCarsForRental() {
		return port.getAvailableCarsForRental();
	}

	@Override
	public long bookACar(BookingRequest arg0) {
		return port.bookACar(arg0);
	}

	@Override
	public List<BookingResult> getBookingResultsForIds(List<Long> arg0) {
		return port.getBookingResultsForIds(arg0);
	}

}
