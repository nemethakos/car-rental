package com.msci.carrental.client.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import com.msci.carrental.client.interpreter.CommandResult;
import com.msci.carrental.client.interpreter.DecorationType;
import com.msci.carrental.client.interpreter.TextDecoratorInterface;
import com.msci.carrental.service.BookingResult;

public class ConsoleWindow implements ConsoleWindowInterface, TextDecoratorInterface {

	private final Logger log = Logger.getLogger(ConsoleWindow.class.getName());

	private CommandReceiverCallBackInterface commandReceiverCallback = null;

	private JScrollPane scrollPane;
	private final JTextField commandText = new JTextField();
	private final JTextPane consoleText = new JTextPane();
	private final JButton commandButton = new JButton();
	private final HTMLEditorKit kit = new HTMLEditorKit();
	private final HTMLDocument doc = new HTMLDocument();
	private final JPanel consolePanel = new JPanel();
	private final JPanel mainPanel = new JPanel();
	private final JPanel commandPanel = new JPanel();
	private final JFrame mainFrame = new JFrame();

	public void runMainLoop() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConsoleWindow() {
		initialize();
	}

	private void updateCommandButtonEnabledStatus() {
		commandButton.setEnabled(isCommandTextValid());
	}

	private void addHTMLTextToConsole(String htmlText) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					kit.insertHTML(doc, doc.getLength(), htmlText, 0, 0, null);
					scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
				} catch (Exception e) {
					log.log(Level.SEVERE, "Error inserting HTML text into console: '" + htmlText + "'", e);
				}
			}
		});

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		initMainFrame();
		initCommandPanel();
		initCommandText();
		initCommandButton();
		initMainPanel();
		initConsolePanel();

		updateCommandButtonEnabledStatus();
	}

	private void initConsolePanel() {
		consolePanel.setBorder(new TitledBorder(null, "Console", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		consolePanel.setLayout(new BorderLayout(0, 0));
		initConsoleText();

		scrollPane = new JScrollPane(consoleText);
		consolePanel.add(scrollPane);
		mainPanel.add(consolePanel, BorderLayout.CENTER);
	}

	private void initConsoleText() {
		consoleText.setEditable(false);
		consoleText.setEditorKit(kit);
		consoleText.setDocument(doc);
	}

	private void initMainPanel() {
		mainPanel.setLayout(new BorderLayout(0, 0));
		mainPanel.add(commandPanel, BorderLayout.SOUTH);
	}

	private void initCommandPanel() {
		commandPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		commandPanel
				.setBorder(new TitledBorder(null, "Enter command", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.X_AXIS));
	}

	private void initMainFrame() {
		mainFrame.setTitle("MSCI Car Rental Console");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BorderLayout(5, 5));
		mainFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point centerPoint = ge.getCenterPoint();

		mainFrame.setBounds(new Rectangle(0, 0, (int) ge.getMaximumWindowBounds().getWidth() / 2,
				(int) ge.getMaximumWindowBounds().getHeight() / 2));
		Dimension windowSize = mainFrame.getSize();
		int dx = centerPoint.x - windowSize.width / 2;
		int dy = centerPoint.y - windowSize.height / 2;

		mainFrame.setLocation(dx, dy);
	}

	private void initCommandButton() {
		commandButton.setText("Send");
		commandButton.setHorizontalAlignment(SwingConstants.RIGHT);

		commandButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				commandButtonPressedEventHandler();
			}
		});

		commandPanel.add(commandButton);
	}

	private void initCommandText() {
		commandText.getDocument().addDocumentListener(new DocumentListener() {

			public void removeUpdate(DocumentEvent e) {
				updateCommandButtonEnabledStatus();
			}

			public void insertUpdate(DocumentEvent e) {
				updateCommandButtonEnabledStatus();
			}

			public void changedUpdate(DocumentEvent e) {

			}
		});

		commandText.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				commandButtonPressedEventHandler();
			}
		});
		commandText.setHorizontalAlignment(SwingConstants.LEFT);
		commandPanel.add(commandText);
	}

	private void clearCommandText() {
		commandText.setText("");
	}

	private void commandButtonPressedEventHandler() {
		if (isCommandTextValid()) {
			notifiyCommandReceiver(commandText.getText());
			clearCommandText();
			updateCommandButtonEnabledStatus();
		}
	}

	private boolean isCommandTextValid() {
		return commandText.getText().trim().length() > 0;
	}

	private void notifiyCommandReceiver(String text) {
		if (commandReceiverCallback != null && text != null && text.length() > 0) {
			commandReceiverCallback.commandReceived(text);
		}
	}

	public void setCommandReceiverCallback(CommandReceiverCallBackInterface commandReceiverCallback) {
		this.commandReceiverCallback = commandReceiverCallback;
	}

	public void sendCommandResult(final CommandResult message) {
		StringBuilder sb = new StringBuilder();

		for (String line : message.getMessages()) {
			sb.append("<p style=\"font-size: 15px;\">");
			sb.append(CommandResult.decorate(line, this));
			sb.append("</p>");

		}

		for (String error : message.getErrors()) {
			sb.append("<p style=\"color: red;font-size: 15px;\">");
			sb.append(CommandResult.decorate(error, this));
			sb.append("</p>");

		}
		sb.append("<hr>");

		addHTMLTextToConsole(sb.toString());
	}

	@Override
	public void sendBookingResult(BookingResult bookingResult) {
		StringBuilder sb = new StringBuilder();

		sb.append("Booking result received for reference: ");
		sb.append(bookingResult.getReference());
		sb.append("Booking request was: ");
		sb.append(bookingResult.getBookingRequest());
		sb.append("Booking status: ");
		sb.append(bookingResult.getBookingStatus());

		addHTMLTextToConsole(sb.toString());

	}

	@Override
	public String getDecorationFor(DecorationType decorationType) {
		String result = "";
		switch (decorationType) {
		case BOLD_START: {
			result = "<span style=\"font-weight: bold;\">";
			break;
		}
		case ITALIC_START: {
			result = "<span style=\" font-style: italic;\">";
			break;
		}
		default: {
			result = "</span>";
		}
		}
		return result;
	}
}
