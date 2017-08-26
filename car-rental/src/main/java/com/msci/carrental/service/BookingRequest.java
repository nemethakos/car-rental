package com.msci.carrental.service;

import java.util.Date;
import java.util.List;

import com.msci.carrental.service.model.CarType;
import com.msci.carrental.service.model.Country;

public class BookingRequest {
	private CarType carType;
	private Date startDate;
	private Date endDate;
	private List<Country> countries;

	public BookingRequest(CarType carType, Date startDate, Date endDate, List<Country> countries) {
		this.carType = carType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.countries = countries;
	}

	public CarType getCarType() {
		return carType;
	}

	public List<Country> getCountries() {
		return countries;
	}

	@Override
	public String toString() {
		return "BookingRequest [carType=" + carType + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", countries=" + countries + "]";
	}

	public Date getEndDate() {
		return endDate;
	}

	public Date getStartDate() {
		return startDate;
	}
}