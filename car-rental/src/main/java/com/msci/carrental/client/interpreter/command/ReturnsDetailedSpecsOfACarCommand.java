package com.msci.carrental.client.interpreter.command;

import java.util.List;

import com.msci.carrental.client.interpreter.CommandHandlerInterface;
import com.msci.carrental.client.interpreter.CommandResult;

public class ReturnsDetailedSpecsOfACarCommand implements CommandHandlerInterface {

	@Override
	public String getCommand() {
		return "show";
	}

	@Override
	public CommandResult invoke(List<String> parameters) {
		return new CommandResult("detailed specs...");
	}

	@Override
	public String getDescription() {
		return "Returns the detailed specification of a car type";
	}

	@Override
	public String getUsage() {
		return "car-type";
	}

}
