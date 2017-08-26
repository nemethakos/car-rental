package com.msci.carrental.service.model;

public enum ServiceType {

	HIGHWAY_FEE("Highway fee"),
	TAX("Tax"),
	THEFT_PROTECTION("Theft Protection (Excess Applies)"),
	COLLISION_DAMAGE_WAIVER("Collision Damage Waiver (Excess Applies)"),
	LOCATION_SERVICE_CHARGE("Location Service Charge (LSC)"),
	FUEL("Fuel"),
	SUPER_COVER("Super Cover (Excess Waiver)"),
	ADDITIONAL_DRIVER_FEE("Additional Driver Fee"),
	WINTER_TIRE("Winter Tyre");
	
	public String getDescription() {
		return description;
	}

	private String description;
	
	private ServiceType(String descrription) {
		this.description = descrription;
	}
	
}
