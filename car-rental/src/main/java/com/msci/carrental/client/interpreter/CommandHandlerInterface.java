package com.msci.carrental.client.interpreter;

import java.util.List;

import com.msci.carrental.client.ws.CarRentalServiceInterface;

/**
 * Interface for command handlers
 */
public interface CommandHandlerInterface {

	/**
	 * Sets the {@link CarRentalServiceInterface} to the command to access the
	 * services offered by the {@link CarRentalServiceInterface}
	 * 
	 * @param carRentalServiceInterface
	 *            the {@link CarRentalServiceInterface}
	 */
	void setCarRentalService(CarRentalServiceInterface carRentalServiceInterface);

	/**
	 * Returns the name of the command (e.g. "help")
	 * 
	 * @return the name of the command
	 */
	String getCommandName();

	/**
	 * Returns a one-liner description about the command
	 * 
	 * @return a one-liner description about the command
	 */
	String getTagLine();

	/**
	 * Invokes the command with the parameters provided by the user.
	 * 
	 * @param parameters
	 *            list of words after the name of the command
	 * @return {@link CommandResult} the result of the command execution
	 */
	CommandResult invoke(List<String> parameters);

	/**
	 * Returns a descriptive text about the command
	 * 
	 * @return text describing the command
	 */
	List<String> getCommandDescription();

	/**
	 * Returns the parameter list for the command
	 * 
	 * @return the parameter list for the command
	 */
	List<String> getParameterDescription();
}
