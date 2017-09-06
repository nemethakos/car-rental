package com.msci.carrental.service.implementation;

import java.net.BindException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.ws.Endpoint;

/**
 * Entry point for the Web Service
 */
public class CarRentalServicePublisher {

	private static final String WEB_SERVICE_PORT = "8080";
	
	/**
	 * Can be accessed from anywhere.
	 */
	private static final String WEB_SERVICE_HOST = "0.0.0.0";
	
	private static Logger log = Logger.getLogger(CarRentalServicePublisher.class.getName());

	public static void main(String args[]) {
		try {
			log.info("Car Rental Web Service starting at: " + getWebServiceURL());
			Endpoint.publish(getWebServiceURL(), new CarRentalServiceImplementation());
			log.info("Car Rental Web Service successfully started at: " + getWebServiceURL());
		} catch (Exception be) {
			if (be instanceof BindException) {
				log.log(Level.SEVERE,
						"Service cannot be started, the address is already used. The service probably already running!", be);
			} else {
				log.log(Level.SEVERE, "Service cannot be started!", be);

			}
		}
	}

	private static String getWebServiceURL() {
		return "http://" + WEB_SERVICE_HOST + ":" + WEB_SERVICE_PORT + "/WS/CarRental";
	}
}
