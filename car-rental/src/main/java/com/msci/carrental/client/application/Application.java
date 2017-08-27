package com.msci.carrental.client.application;

import com.msci.carrental.client.gui.ConsoleWindow;
import com.msci.carrental.client.interpreter.CommandInterpreter;

/**
 * Main application
 */
public class Application {

	private ConsoleWindow consoleWindow;

	public Application() {
		super();

		consoleWindow = new ConsoleWindow();
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
