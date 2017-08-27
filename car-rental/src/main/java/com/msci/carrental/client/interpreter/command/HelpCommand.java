package com.msci.carrental.client.interpreter.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import com.msci.carrental.client.interpreter.CommandHandlerInterface;
import com.msci.carrental.client.interpreter.CommandResult;
import com.msci.carrental.client.util.Util;
import com.msci.carrental.service.CarRentalServiceInterface;

public class HelpCommand implements CommandHandlerInterface {

	@Override
	public void setCarRentalService(CarRentalServiceInterface carRentalServiceInterface) {
	}

	private List<CommandHandlerInterface> commandList;

	@Override
	public String getCommandName() {
		return "help";
	}

	@Override
	public CommandResult invoke(List<String> parameters) {
		CommandResult result = new CommandResult();
		if (parameters == null || parameters.size() == 0) {
			commandList.stream().forEach(getHelpText(result));
		} else {
			commandList.stream().filter(commandFilter(parameters)).forEach(getHelpText(result));
		}
		if (result.getMessages().size()==0) {
			result.addError("Parameter is not recognized as a command name!");
		}
		return result;
	}

	private static Predicate<? super CommandHandlerInterface> commandFilter(List<String> parameters) {
		return handler -> parameters.stream().anyMatch(handler.getCommandName()::equals);
	}

	private static Consumer<? super CommandHandlerInterface> getHelpText(CommandResult result) {
		return handler -> {
			result.addMessage(Util.getBoldText(handler.getCommandName()));
			if (handler.getParameterDescription() != null) {
				handler.getParameterDescription().stream().forEach(Util.appendLinesIntoOneLineInItalic(result));
			}
			if (handler.getTagLine() != null) {
				Util.appendTextToLastLine(result, " - " + handler.getTagLine());
			}

			if (handler.getCommandDescription() != null) {
				result.addMessage("");
				handler.getCommandDescription().stream().forEach(line -> {
					Util.appendTextToLastLine(result, line);
				});
			}
			
			result.addMessage("");
		};
	}

	public HelpCommand(List<CommandHandlerInterface> commands) {
		super();
		this.commandList = commands;
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> result = new ArrayList<>();

		final StringBuilder sb = new StringBuilder();

		commandList.stream().forEach(command -> {
			sb.append(command.getCommandName() + " ");
		});

		result.add("*available commands: " + sb.toString());

		return result;
	}

	@Override
	public List<String> getParameterDescription() {
		return Arrays.asList("[command1 command2 ... commandN]*");
	}

	@Override
	public String getTagLine() {
		return "Returns help for commands";
	}

}
