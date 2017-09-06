package com.msci.carrental.service.implementation;

import javax.xml.ws.Endpoint;

public class CarRentalServicePublisher {
	public static void main(String args[]) {
		Endpoint.publish("http://localhost:8080/WS/CarRental", new CarRentalServiceImplementation());
	}
}
