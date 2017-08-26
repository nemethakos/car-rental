package com.msci.carrental.client.gui;

import java.util.List;

public class Message {
	private long id;
	private boolean error;
	private List<String> lines;
	public long getId() {
		return id;
	}
	public boolean isError() {
		return error;
	}
	public List<String> getLines() {
		return lines;
	}
	public Message(long id, boolean error, List<String> lines) {
		super();
		this.id = id;
		this.error = error;
		this.lines = lines;
	}
	
	
}
