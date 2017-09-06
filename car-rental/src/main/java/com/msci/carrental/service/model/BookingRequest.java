package com.msci.carrental.service.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class BookingRequest {

	@XmlElement(nillable = false)
	private CarType carType;

	@XmlElement(nillable = false)
	private List<Country> countries;

	@XmlElement(nillable = false)
	private Date endDate;

	@XmlElement(nillable = false)
	private Date startDate;

	public BookingRequest() {
		super();
	}

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

	public Date getEndDate() {
		return endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public String getValidationMessage() {
		StringBuilder sb = new StringBuilder();

		if (carType == null) {
			sb.append("carType should be non null!\r\n");
		}
		if (startDate == null) {
			sb.append("startDate should be non null!\r\n");
		}
		if (startDate == null) {
			sb.append("endDate should be non null!\r\n");
		}
		if (countries == null) {
			sb.append("countries should be non null!\r\n");
		}

		return sb.toString();
	}

	@Override
	public String toString() {
		return "BookingRequest [carType=" + carType + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", countries=" + countries + "]";
	}
}