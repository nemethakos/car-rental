package com.msci.carrental.service.model;

public class CarSpecification {
	private CarType carType;

	public CarSpecification(CarType carType) {
		super();
		this.carType = carType;
	}

	public CarType getCarType() {
		return carType;
	}
}
