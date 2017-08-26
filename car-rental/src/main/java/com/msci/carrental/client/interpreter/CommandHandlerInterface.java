package com.msci.carrental.client.interpreter;

import java.util.List;

public interface CommandHandlerInterface {
	String getCommand();
	CommandResult invoke(List<String> parameters);
	String getDescription();
	String getUsage();
}
