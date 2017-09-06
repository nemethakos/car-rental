package com.msci.carrental.client.gui;

import com.msci.carrental.client.interpreter.CommandResult;
import com.msci.carrental.client.ws.BookingResult;


public interface ConsoleWindowInterface {

	/**
	 * Sets the receiver of the command text
	 * 
	 * @param commandReceiverCallback
	 *            {@link CommandReceiverCallBackInterface}
	 */
	void setCommandReceiverCallback(CommandReceiverCallBackInterface commandReceiverCallback);

	/**
	 * Sends the result of a command execution to the console window
	 * 
	 * @param commandResult
	 *            {@link CommandResult}
	 */
	void sendCommandResult(CommandResult commandResult);

	/**
	 * Sends the result of the {@link BookingResult} to the console window
	 * 
	 * @param bookingResult
	 *            {@link BookingResult}
	 */
	void sendBookingResult(BookingResult bookingResult);
}
