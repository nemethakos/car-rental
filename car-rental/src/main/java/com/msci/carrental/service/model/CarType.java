package com.msci.carrental.service.model;

public class CarType {

	public CarType(String description, String categoryGroup, String categoryExample, String carCode,
			SuitcaseSpecification luggageSpecification, int numberOfPassangers, TransmissionType transmissionType,
			boolean hasAirConditioning, float fuelConsumptionLiterPer100Km, float co2EmissionGramPerKm) {
		
		super();
		this.description = description;
		this.categoryGroup = categoryGroup;
		this.categoryExample = categoryExample;
		this.carCode = carCode;
		this.luggageSpecification = luggageSpecification;
		this.numberOfPassangers = numberOfPassangers;
		this.transmissionType = transmissionType;
		this.hasAirConditioning = hasAirConditioning;
		this.fuelConsumptionLiterPer100Km = fuelConsumptionLiterPer100Km;
		this.co2EmissionGramPerKm = co2EmissionGramPerKm;
	}
	
	private String description;
	private String categoryGroup;
	private String categoryExample;
	private String carCode;
	private SuitcaseSpecification luggageSpecification;
	private int numberOfPassangers;
	private TransmissionType transmissionType;
	private boolean hasAirConditioning;
	private float fuelConsumptionLiterPer100Km;
	private float co2EmissionGramPerKm;
	
	public String getDescription() {
		return description;
	}
	public String getCategoryGroup() {
		return categoryGroup;
	}
	public String getCategoryExample() {
		return categoryExample;
	}
	public String getCarCode() {
		return carCode;
	}
	public SuitcaseSpecification getLuggageSpecification() {
		return luggageSpecification;
	}
	public int getNumberOfPassangers() {
		return numberOfPassangers;
	}
	public TransmissionType getTransmissionType() {
		return transmissionType;
	}
	public boolean isHasAirConditioning() {
		return hasAirConditioning;
	}
	public float getFuelConsumptionLiterPer100Km() {
		return fuelConsumptionLiterPer100Km;
	}
	public float getCo2EmissionGramPerKm() {
		return co2EmissionGramPerKm;
	}
}
