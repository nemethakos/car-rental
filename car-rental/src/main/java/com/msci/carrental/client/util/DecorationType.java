package com.msci.carrental.client.util;

public enum DecorationType {
	BOLD_START("$BS$"),
	BOLD_END("$BE$"),
	ITALIC_START("$IS$"),
	ITALIC_END("$IE$"),
	HEAD_START("$HS$"),
	HEAD_END("$HE$"),
	CODE_START("$CS$"),
	CODE_END("$CE$");
	
	public String getMarking() {
		return marking;
	}

	private String marking;

	private DecorationType(String marking) {
		this.marking = marking;
	}
}
