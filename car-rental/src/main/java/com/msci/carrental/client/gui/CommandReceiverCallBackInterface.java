package com.msci.carrental.client.gui;

public interface CommandReceiverCallBackInterface {

	/**
	 * The implementor of this interface will receive the command text
	 * 
	 * @param text
	 *            the command text
	 */
	void commandReceived(String text);

}
