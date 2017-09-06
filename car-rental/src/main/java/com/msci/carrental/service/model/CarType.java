package com.msci.carrental.service.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "CarType")
@XmlAccessorType(XmlAccessType.FIELD)
public enum CarType {

	M("Microcar", "Suzuki Swift", "Micro, 2-4 Door, Manual, Aircon", 4, 1, 1, false), //
	E("Economy", "VW Polo TSI", "Economy, 4-5 Door, Manual, Aircon", 5, 1, 1, false), //
	C("Compact", "Hyundai i30", "Compact, 2-4 Door, Manual, Aircon", 5, 1, 2, false); //

	@XmlElement
	private boolean automaticTransmission;

	@XmlElement

	private String description;
	@XmlElement

	private String example;
	@XmlElement

	private String group;
	@XmlElement

	private int numberOfLargeSuitcases;
	@XmlElement

	private int numberOfPassangers;
	@XmlElement

	private int numberOfSmallSuitcases;

	private CarType(String group, String example, String description, int numberOfPassangers,
			int numberOfSmallSuitcases, int numberOfLargeSuitcases, boolean automaticTransmission) {
		this.group = group;
		this.description = description;
		this.example = example;
		this.numberOfPassangers = numberOfPassangers;
		this.numberOfSmallSuitcases = numberOfSmallSuitcases;
		this.numberOfLargeSuitcases = numberOfLargeSuitcases;
		this.automaticTransmission = automaticTransmission;
	}

	public String getDescription() {
		return description;
	}

	public String getExample() {
		return example;
	}

	public String getGroup() {
		return group;
	}

	public int getNumberOfLargeSuitcases() {
		return numberOfLargeSuitcases;
	}

	public int getNumberOfPassangers() {
		return numberOfPassangers;
	}

	public int getNumberOfSmallSuitcases() {
		return numberOfSmallSuitcases;
	}

	public boolean isAutomaticTransmission() {
		return automaticTransmission;
	}

}
