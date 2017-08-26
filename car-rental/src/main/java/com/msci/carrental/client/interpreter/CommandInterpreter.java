package com.msci.carrental.client.interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.msci.carrental.client.gui.CommandReceiverCallBackInterface;
import com.msci.carrental.client.gui.CommandWindowInterface;
import com.msci.carrental.client.interpreter.command.BookACarCommand;
import com.msci.carrental.client.interpreter.command.HelpCommand;
import com.msci.carrental.client.interpreter.command.ListOfCarsAvailableForRentCommand;
import com.msci.carrental.client.interpreter.command.ReturnsDetailedSpecsOfACarCommand;
import com.msci.carrental.service.BookingResult;
import com.msci.carrental.service.BookingResultReceiverInterface;
import com.msci.carrental.service.CarRentalServiceInterface;
import com.msci.carrental.service.proxy.CarRentalServiceProxy;

public class CommandInterpreter implements CommandReceiverCallBackInterface, BookingResultReceiverInterface {
	private CommandWindowInterface commandWindow;
	private CarRentalServiceInterface carRentalService;
	private long id = 0;
	private List<CommandHandlerInterface> commandHandlers;

	private CommandInterpreter(CommandWindowInterface commandWindow) {
		super();
		this.commandWindow = commandWindow;
		this.commandWindow.setCommandReceiverCallback(this);
		carRentalService = CarRentalServiceProxy.getProxy();
		carRentalService.setBookingResultReceiver(this);
		initCommandHandlers();
	}

	private void initCommandHandlers() {
		commandHandlers = new ArrayList<>();

		HelpCommand helpCommand = new HelpCommand(commandHandlers);
		commandHandlers.add(helpCommand);
		commandHandlers.add(new ListOfCarsAvailableForRentCommand());
		commandHandlers.add(new ReturnsDetailedSpecsOfACarCommand());
		commandHandlers.add(new BookACarCommand());
	}

	public static CommandInterpreter registerCommandInterpreter(CommandWindowInterface commandWindow) {
		return new CommandInterpreter(commandWindow);
	}

	@Override
	public void receiveBookingResult(BookingResult bookingResult) {
		commandWindow.sendPlainTextMessage(bookingResult.toString(), false);
	}

	public void commandReceived(String command) {
		List<String> words = getWords(command);
		CommandResult result = interpret(words);
		commandWindow.sendPlainTextMessage("\r\n\r\n"+result.getLines().stream().map(line -> line + "\r\n").collect(Collectors.joining()), result.isError() );
	}

	private CommandResult interpret(List<String> words) {
		CommandResult result = new CommandResult();
		if (words.size() > 0) {
			String commandName = words.get(0);

			Optional<CommandHandlerInterface> optional = commandHandlers.stream()
					.filter(handler -> handler.getCommand().equals(commandName)).findFirst();

			if (optional.isPresent()) {
				List<String> commandParameters = new ArrayList<>();
				if (words.size() > 1) {
					List<String> parametersList = words.subList(1, words.size());
					commandParameters.addAll(parametersList);
				}
				result = optional.get().invoke(commandParameters);
			} else {
				result = new CommandResult("command not found: \"" + commandName + "\"", true);
			}
		}
		return result;
	}

	private List<String> getWords(String commandSentence) {

		List<String> result = new ArrayList<>();

		if (commandSentence != null) {
			String[] words = commandSentence.split("\\s++");
			List<String> asList = Arrays.asList(words).stream().filter(str -> str.length() > 0)
					.collect(Collectors.toList());
			result.addAll(asList);
		}

		return result;
	}

	private List<String> getLines(String text) {
		List<String> result = new ArrayList<>();
		result.add(text);
		return result;
	}

	private long getNextId() {
		return id++;
	}

}
