package com.msci.carrental.service.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.msci.carrental.service.implementation.util.Util;

@XmlType
public class BookingRequest {

	@XmlElement(nillable = false)
	public CarType carType;

	@XmlElement(nillable = false)
	public List<Country> countries;

	@XmlElement(nillable = false)
	private Date endDate;

	@XmlElement(nillable = false)
	public Date startDate;

	public BookingRequest() {
		super();
	}

	public BookingRequest(CarType carType, Date startDate, Date endDate, List<Country> countries) {
		this.carType = carType;
		this.startDate = Util.normalizeDate(startDate);
		this.endDate = Util.normalizeDate(endDate);
		this.countries = new ArrayList<>();
		if (countries != null) {
			this.countries.addAll(countries);
		}
	}

	public CarType getCarType() {
		return carType;
	}

	public List<Country> getCountries() {
		if (countries == null) {
			countries = new ArrayList<>();
		}
		return countries;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	@Override
	public String toString() {
		return "BookingRequest [carType=" + carType + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", countries=" + countries + "]";
	}
}