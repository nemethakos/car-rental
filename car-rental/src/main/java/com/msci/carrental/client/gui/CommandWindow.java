package com.msci.carrental.client.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import com.msci.carrental.client.interpreter.CommandResult;

public class CommandWindow implements CommandWindowInterface {

	private CommandReceiverCallBackInterface commandReceiverCallback = null;

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
	public CommandWindow() {
		initialize();
	}

	private void updateCommandButtonEnabledStatus() {
		commandButton.setEnabled(isCommandTextValid());
	}

	private void addTextToConsole(String htmlText) {

		try {
			kit.insertHTML(doc, doc.getLength(), htmlText, 0, 0, null);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		JScrollPane scrollPane = new JScrollPane(consoleText);
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
		mainFrame.setBounds(100, 100, 565, 634);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BorderLayout(5, 5));
		mainFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		Dimension windowSize = mainFrame.getSize();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point centerPoint = ge.getCenterPoint();

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

	public void sendMessage(final CommandResult message) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addTextToConsole("<p style=\"color: red;\">" + message.getLines() + "</p>");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	@Override
	public void sendPlainTextMessage(final String plainText, boolean error) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String style = "";
					if (error) {
						style = " style='color: red;' ";
					}
					String htmlText = "<pre" + style + ">" + plainText + "</pre><hr>";
					addTextToConsole(htmlText);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
