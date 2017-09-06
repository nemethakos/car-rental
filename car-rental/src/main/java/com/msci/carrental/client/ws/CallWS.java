package com.msci.carrental.client.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceRef;

public class CallWS {
	@WebServiceRef(wsdlLocation = 
		      "http://localhost:8080/WS/CarRental?wsdl")
		    private static CarRentalServiceImplementationService service;

		    /**
		     * @param args the command line arguments
		     * @throws MalformedURLException 
		     */
		    public static void main(String[] args) throws MalformedURLException {
		    	URL url = new URL("http://localhost:8080/WS/CarRental?wsdl");

		        //1st argument service URI, refer to wsdl document above
			//2nd argument is service name, refer to wsdl document above
		        QName qname = new QName("http://implementation.service.carrental.msci.com/", "CarRentalServiceImplementationService");

		        Service service = Service.create(url, qname);

		          CarRentalServiceInterface port = service.getPort(CarRentalServiceInterface.class);

		        List<CarInstance> list = port.getAvailableCarsForRental();
		        
		        System.out.println(list);

		    }

		  
}
