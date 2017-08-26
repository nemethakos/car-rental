package com.msci.carrental.service.model;

public class Price {
	private float price;
	private Currency currency;

	public Price(float price, Currency currency) {
		super();
		this.price = price;
		this.currency = currency;
	}

	public float getPrice() {
		return price;
	}

	public Currency getCurrency() {
		return currency;
	}
}
