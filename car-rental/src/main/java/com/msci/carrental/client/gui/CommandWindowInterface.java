package com.msci.carrental.client.gui;

import com.msci.carrental.client.interpreter.CommandResult;

public interface CommandWindowInterface {

	void setCommandReceiverCallback(CommandReceiverCallBackInterface commandReceiverCallback);
	void sendMessage(CommandResult message);
	void sendPlainTextMessage(String plainText, boolean error);
	
}
