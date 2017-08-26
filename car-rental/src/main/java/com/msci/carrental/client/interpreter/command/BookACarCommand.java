package com.msci.carrental.client.interpreter.command;

import java.util.List;

import com.msci.carrental.client.interpreter.CommandHandlerInterface;
import com.msci.carrental.client.interpreter.CommandResult;

public class BookACarCommand implements CommandHandlerInterface {

	@Override
	public String getCommand() {
		return "book";
	}

	@Override
	public CommandResult invoke(List<String> parameters) {
		return new CommandResult("booking is in progress..." + parameters);
	}

	@Override
	public String getDescription() {
		return "Books a car";
	}

	@Override
	public String getUsage() {
		return "from-date to-date country1 country2 ... countryN";
	}

}
