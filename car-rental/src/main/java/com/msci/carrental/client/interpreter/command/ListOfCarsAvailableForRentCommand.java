package com.msci.carrental.client.interpreter.command;

import java.util.List;

import com.msci.carrental.client.interpreter.CommandHandlerInterface;
import com.msci.carrental.client.interpreter.CommandResult;

public class ListOfCarsAvailableForRentCommand implements CommandHandlerInterface {

	@Override
	public String getCommand() {
		return "list";
	}

	@Override
	public CommandResult invoke(List<String> parameters) {
		return new CommandResult("list of cars...");
	}

	@Override
	public String getDescription() {
		return "Returns the list of available car types for rent";
	}

	@Override
	public String getUsage() {
		return "";
	}

}
