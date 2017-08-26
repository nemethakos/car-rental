package com.msci.carrental.client.interpreter.command;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.msci.carrental.client.interpreter.CommandHandlerInterface;
import com.msci.carrental.client.interpreter.CommandResult;

public class HelpCommand implements CommandHandlerInterface {

	private List<CommandHandlerInterface> commandList;

	@Override
	public String getCommand() {
		return "help";
	}

	@Override
	public CommandResult invoke(List<String> parameters) {
		CommandResult result = new CommandResult();
		if (parameters.size() == 0) {
			commandList.stream()
			.forEach(getHelpText(result));
		} else {
			commandList.stream()
			.filter(commandFilter(parameters))
			.forEach(getHelpText(result));
		}

		return result;
	}

	private Predicate<? super CommandHandlerInterface> commandFilter(List<String> parameters) {
		return handler -> parameters.stream().anyMatch(handler.getCommand()::equals);
	}

	private Consumer<? super CommandHandlerInterface> getHelpText(CommandResult result) {
		return handler -> 
		{
			String text = handler.getCommand() + " " + handler.getUsage() + " " + handler.getDescription();
			result.addText(text);
		};
	}

	public HelpCommand(List<CommandHandlerInterface> commands) {
		super();
		this.commandList = commands;
	}

	@Override
	public String getDescription() {
		return "Returns help for commands";
	}

	@Override
	public String getUsage() {
		return "[command]";
	}

}
