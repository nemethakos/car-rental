package com.msci.carrental.client.interpreter;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
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
import com.msci.carrental.client.ws.BookingResult;
import com.msci.carrental.client.ws.CarRentalServiceInterface;
import com.msci.carrental.client.ws.WebServiceProxy;

public class CommandInterpreter implements CommandReceiverCallBackInterface, BookingHandlerInterface {
	private Logger log = Logger.getLogger(CommandInterpreter.class.getName());
	private ConsoleWindowInterface commandWindow;
	private CarRentalServiceInterface carRentalService;
	private List<CommandHandlerInterface> commandHandlers;
	private HelpCommand helpCommand = null;
	private ConcurrentLinkedDeque<Long> pollerQueue = new ConcurrentLinkedDeque<>();

	private CommandInterpreter(ConsoleWindowInterface commandWindow) {
		super();
		this.commandWindow = commandWindow;
		this.commandWindow.setCommandReceiverCallback(this);
		try {
			carRentalService = WebServiceProxy.getInstance();
		} catch (MalformedURLException e) {
			commandWindow.sendCommandResult(new CommandResult("Error getting Web Service Proxy", true));
		}
		initCommandHandlers();
		initBookingResultPoller(commandWindow);
	}

	public void initBookingResultPoller(ConsoleWindowInterface commandWindow) {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(pollBookingResults(commandWindow), 1, 1, TimeUnit.SECONDS);
	}

	public Runnable pollBookingResults(ConsoleWindowInterface commandWindow) {
		return () -> {
			List<Long> pollerList = new ArrayList<Long>(pollerQueue);
			if (!pollerList.isEmpty()) {
				List<BookingResult> bookingResults = carRentalService.getBookingResultsForIds(pollerList);
				for (BookingResult bookingResult : bookingResults) {
					commandWindow.sendBookingResult(bookingResult);
					pollerQueue.remove(new Long(bookingResult.getReference()));
				}
				if (!bookingResults.isEmpty()) {
					commandWindow
							.sendCommandResult(new CommandResult("" + bookingResults.size() + " results received"));
				}
			}
		};
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

		commandHandlers.stream().forEach(handler -> {
			handler.setCarRentalService(carRentalService);
			handler.setBookingHandler(this);
		});

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

	public void commandReceived(String command) {
		if (carRentalService == null) {
			commandWindow.sendCommandResult(new CommandResult("No Web Service Proxy, cannot execute command!", true));
		} else {
			List<String> words = getWords(command);
			CommandResult result = interpret(words);
			commandWindow.sendCommandResult(result);
		}
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
				try {
					result = optional.get().invoke(commandParameters);
				} catch (Throwable t) {
					commandWindow
							.sendCommandResult(new CommandResult("Error executing command: " + t.getMessage(), true));
				}
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

	@Override
	public void addBookingIdToThePollerQueue(long bookingId) {
		pollerQueue.add(new Long(bookingId));
		commandWindow.sendCommandResult(new CommandResult("" + pollerQueue.toString(), false));
	}

}
