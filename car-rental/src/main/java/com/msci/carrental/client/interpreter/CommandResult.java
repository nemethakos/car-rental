package com.msci.carrental.client.interpreter;

import java.util.ArrayList;
import java.util.List;

public class CommandResult {
	
	private boolean error = false;
	
	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	private List<String> lines = new ArrayList<>();

	public void addText(String text) {
		lines.add(text);
	}
	
	public List<String> getLines() {
		return lines;
	}

	@Override
	public String toString() {
		return "CommandResult [lines=" + lines + "]";
	}

	public CommandResult(String text, boolean error) {
		super();
		lines.add(text);
		this.error = error;
	}
	public CommandResult(String text) {
		super();
		lines.add(text);
	}
	public CommandResult() {
	}
	
	
}
