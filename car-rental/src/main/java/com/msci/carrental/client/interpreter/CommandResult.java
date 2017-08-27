package com.msci.carrental.client.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * Result of executing a command
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

	public static String getBoldText(String text) {
		return DecorationType.BOLD_START.getMarking() + text + DecorationType.BOLD_END.getMarking();
	}

	public static String getItalicText(String text) {
		return DecorationType.ITALIC_START.getMarking() + text + DecorationType.ITALIC_END.getMarking();

	}

	/**
	 * Decorates the plain text with markings into decorated (e.g.: HTML) text
	 * 
	 * @param text
	 *            the input text
	 * @param decorator
	 *            the {@link TextDecoratorInterface}
	 * @return the decorated text
	 */
	public static String decorate(String text, TextDecoratorInterface decorator) {
		String result = new String(text);
		for (DecorationType decorationType : DecorationType.values()) {
			result = result.replace(decorationType.getMarking(), decorator.getDecorationFor(decorationType));
		}
		return result;
	}
}
