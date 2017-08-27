package com.msci.carrental.service.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Country {
	HU, DE, AT, HR;

	public static Country getCountryFromCountryCode(String countryCode) {
		Country result = null;

		try {
			result = Country.valueOf(countryCode);
		} catch (IllegalArgumentException e) {
			// not found
		}

		return result;
	}
	
	public static List<String> getCountryList() {
		List<String> result = new ArrayList<>();

		Arrays.asList(Country.values()).stream().forEach(country -> result.add(country.name()));
		
		return result ;
		
	}

}
