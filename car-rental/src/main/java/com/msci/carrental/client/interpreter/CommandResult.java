package com.msci.carrental.client.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * Result of executing a command.
 * <ul>
 * <li>The <code>messages</code> lists the normal messages.
 * <li>The <code>errors</code> lists the error messages.
 * </ul>
 */
public class CommandResult {

	private List<String> messages = new ArrayList<>();
	private List<String> errors = new ArrayList<>();

	public CommandResult() {
	}

	public CommandResult(String message) {
		super();
		messages.add(message);
	}

	public CommandResult(String message, boolean error) {
		super();
		if (error) {
			errors.add(message);
		} else {
			messages.add(message);
		}
	}

	public void addMessage(String message) {
		messages.add(message);
	}

	public void addError(String errorMessage) {
		errors.add(errorMessage);
	}

	public List<String> getMessages() {
		return messages;
	}

	public List<String> getErrors() {
		return errors;
	}

	public boolean isError() {
		return errors.size() > 0;
	}
}
