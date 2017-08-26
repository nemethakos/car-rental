package com.msci.carrental.service.model;

public class SuitcaseSpecification {
	private int numberOfSmallSuitcase;

	public int getNumberOfSmallSuitcase() {
		return numberOfSmallSuitcase;
	}

	public int getNumberOfLargeSuitcase() {
		return numberOfLargeSuitcase;
	}

	public SuitcaseSpecification(int numberOfSmallSuitcase, int numberOfLargeSuitcase) {
		super();
		this.numberOfSmallSuitcase = numberOfSmallSuitcase;
		this.numberOfLargeSuitcase = numberOfLargeSuitcase;
	}

	private int numberOfLargeSuitcase;
}
