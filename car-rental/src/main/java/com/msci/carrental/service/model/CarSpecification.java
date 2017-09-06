package com.msci.carrental.service.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class CarSpecification {

	@XmlElement
	private boolean automaticTransmission;

	@XmlElement
	private String carTypeName;

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

	public CarSpecification(CarType carType) {

		this.automaticTransmission = carType.isAutomaticTransmission();
		this.carTypeName = carType.name();
		this.description = carType.getDescription();
		this.example = carType.getExample();
		this.group = carType.getGroup();
		this.numberOfLargeSuitcases = carType.getNumberOfLargeSuitcases();
		this.numberOfPassangers = carType.getNumberOfPassangers();
		this.numberOfSmallSuitcases = carType.getNumberOfSmallSuitcases();
	}

	public String getCarTypeName() {
		return carTypeName;
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

	@Override
	public String toString() {
		return "CarSpecification [carTypeName=" + carTypeName + ", automaticTransmission=" + automaticTransmission
				+ ", description=" + description + ", example=" + example + ", group=" + group
				+ ", numberOfLargeSuitcases=" + numberOfLargeSuitcases + ", numberOfPassangers=" + numberOfPassangers
				+ ", numberOfSmallSuitcases=" + numberOfSmallSuitcases + "]";
	}

}
