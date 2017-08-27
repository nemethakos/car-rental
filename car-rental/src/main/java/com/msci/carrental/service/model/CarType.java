package com.msci.carrental.service.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum CarType {

	CCMR("A", "Suzuki Swift", "Economy, 2-4 Door, Manual, Aircon", 4, 1, 1, false), //
	CDAR("B", "VW Polo TSI", "Economy, 4-5 Door, Manual, Aircon", 5, 1, 1, false), //
	CDMR("L", "Opel Corsa", "Economy, 4-5 Door, Automatic, Aircon", 5, 1, 2, false), //
	CFMR("C", "Hyundai i30", "Compact, 2-4 Door, Manual, Aircon", 5, 1, 2, false); //

	private boolean automaticTransmission;

	private String description;

	private String example;

	private String group;

	private int numberOfLargeSuitcases;

	private int numberOfPassangers;

	private int numberOfSmallSuitcases;

	private CarType(String group, String example, String description, int numberOfPassangers, int numberOfSmallSuitcases,
			int numberOfLargeSuitcases, boolean automaticTransmission) {
		this.group = group;
		this.description = description;
		this.example = example;
		this.numberOfPassangers = numberOfPassangers;
		this.numberOfSmallSuitcases = numberOfSmallSuitcases;
		this.numberOfLargeSuitcases = numberOfLargeSuitcases;
		this.automaticTransmission = automaticTransmission;
	}
	
	public static CarType getCarTypeFromCode(String groupCode) {
		CarType result = null;
		try {
			result = CarType.valueOf(groupCode.trim().toUpperCase());
		} catch (IllegalArgumentException e) {
			// not found;
		}
		return result;

	}
	
	public static List<String> getListOfCarTypes() {
		List<String> result = new ArrayList<>();
		
		Arrays.asList(CarType.values()).stream().forEach(carType -> result.add(carType.name()));		
		
		return result ;
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
