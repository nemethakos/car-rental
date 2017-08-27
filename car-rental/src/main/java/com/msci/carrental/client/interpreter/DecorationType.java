package com.msci.carrental.client.interpreter;

public enum DecorationType {
	BOLD_START("$BS$"),
	BOLD_END("$BE$"),
	ITALIC_START("$IS$"),
	ITALIC_END("$IE$");
	
	public String getMarking() {
		return marking;
	}

	private String marking;

	private DecorationType(String marking) {
		this.marking = marking;
	}
}
