package com.msci.carrental.service.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class CarInstance {
	
	@XmlElement
	private CarType carType;
	
	@XmlElement
	private Country country;
	
	@Override
	public String toString() {
		return "CarInstance [carType=" + carType + ", country=" + country + ", numberPlate=" + numberPlate + "]";
	}

	@XmlElement
	private String numberPlate;

	public CarType getCarType() {
		return carType;
	}

	public Country getCountry() {
		return country;
	}

	public String getNumberPlate() {
		return numberPlate;
	}

	public CarInstance() {
		super();
	}

	public CarInstance(CarType carType, Country country, String numberPlate) {
		super();
		this.carType = carType;
		this.country = country;
		this.numberPlate = numberPlate;
	}

}
