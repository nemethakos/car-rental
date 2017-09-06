package com.msci.carrental.client.interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.msci.carrental.client.gui.CommandReceiverCallBackInterface;
import com.msci.carrental.client.gui.ConsoleWindowInterface;
import com.msci.carrental.client.interpreter.command.BookACarCommand;
import com.msci.carrental.client.interpreter.command.DisplayAllBookingsCommand;
import com.msci.carrental.client.interpreter.command.HelpCommand;
import com.msci.carrental.client.interpreter.command.ListOfCarsAvailableForRentCommand;
import com.msci.carrental.client.interpreter.command.ReturnsDetailedSpecsOfACarCommand;
import com.msci.carrental.client.interpreter.command.StressTestCommand;
import com.msci.carrental.client.util.Util;
import com.msci.carrental.service.BookingResultReceiverInterface;
import com.msci.carrental.service.CarRentalServiceInterface;
import com.msci.carrental.service.model.BookingResult;

public class CommandInterpreter implements CommandReceiverCallBackInterface, BookingResultReceiverInterface {
	private ConsoleWindowInterface commandWindow;
	private CarRentalServiceInterface carRentalService;
	private List<CommandHandlerInterface> commandHandlers;
	private HelpCommand helpCommand = null;

	private CommandInterpreter(ConsoleWindowInterface commandWindow) {
		super();
		this.commandWindow = commandWindow;
		this.commandWindow.setCommandReceiverCallback(this);
		initCommandHandlers();
	}

	private void initCommandHandlers() {
		commandHandlers = new ArrayList<>();

		helpCommand = new HelpCommand(commandHandlers);
		commandHandlers.add(helpCommand);
		commandHandlers.add(new ListOfCarsAvailableForRentCommand());
		commandHandlers.add(new ReturnsDetailedSpecsOfACarCommand());
		commandHandlers.add(new BookACarCommand());
		commandHandlers.add(new DisplayAllBookingsCommand());
		commandHandlers.add(new StressTestCommand());
		
		commandHandlers.stream().forEach(handler->handler.setCarRentalService(carRentalService));
		
		printWelcomeMessage();
	}

	private void printWelcomeMessage() {
		CommandResult result = new CommandResult();
		result.addMessage(Util.getHeadText("MSCI Car Rental Service Demo"));
		result.addMessage("");
		CommandResult helpResult = helpCommand.invoke(null);
		result.getMessages().addAll(helpResult.getMessages());
		commandWindow.sendCommandResult(result);
		
	}

	public static CommandInterpreter registerCommandInterpreter(ConsoleWindowInterface commandWindow) {
		return new CommandInterpreter(commandWindow);
	}
	
	@Override
	public void receiveBookingResult(BookingResult bookingResult) {
		commandWindow.sendBookingResult(bookingResult);
	}

	public void commandReceived(String command) {
		List<String> words = getWords(command);
		CommandResult result = interpret(words);
		commandWindow.sendCommandResult(result);
	}

	private CommandResult interpret(List<String> words) {
		CommandResult result = new CommandResult();
		if (words.size() > 0) {
			String commandName = words.get(0);

			Optional<CommandHandlerInterface> optional = commandHandlers.stream()
					.filter(handler -> handler.getCommandName().equals(commandName)).findFirst();

			if (optional.isPresent()) {
				List<String> commandParameters = new ArrayList<>();
				if (words.size() > 1) {
					List<String> parametersList = words.subList(1, words.size());
					commandParameters.addAll(parametersList);
				}
				result = optional.get().invoke(commandParameters);
				result.getMessages().add(0, Util.getBoldText("> " + String.join(" ", words)));
			} else {
				result = new CommandResult("command not found: \"" + commandName + "\"", true);
			}
		}
		return result;
	}

	private static List<String> getWords(String commandSentence) {

		List<String> result = new ArrayList<>();

		if (commandSentence != null) {
			String[] words = commandSentence.split("\\s++");
			List<String> asList = Arrays.asList(words).stream().filter(str -> str.length() > 0)
					.collect(Collectors.toList());
			result.addAll(asList);
		}

		return result;
	}

	
}
