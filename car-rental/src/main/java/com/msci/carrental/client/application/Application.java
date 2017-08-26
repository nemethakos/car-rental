package com.msci.carrental.client.application;

import com.msci.carrental.client.gui.CommandWindow;
import com.msci.carrental.client.interpreter.CommandInterpreter;

public class Application {

	private CommandWindow consoleWindow;

	public Application() {
		super();

		consoleWindow = new CommandWindow();
		CommandInterpreter.registerCommandInterpreter(consoleWindow);

	}

	private void run() {
		consoleWindow.runMainLoop();
	}

	public static void main(String[] args) {

		Application application = new Application();
		application.run();

	}

}
