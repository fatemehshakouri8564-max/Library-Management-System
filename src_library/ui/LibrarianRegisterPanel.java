package ui;

import javax.swing.*;
import java.awt.*;
import model.Librarian;
import service.LibrarySystem;

public class LibrarianRegisterPanel extends JPanel {

	private MainFrame mainFrame;
	private LibrarySystem system;

	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JPasswordField repeatPasswordField;
	private JTextField personnelIdField;

	public LibrarianRegisterPanel(MainFrame mainFrame, LibrarySystem system) {

		this.mainFrame = mainFrame;
		this.system = system;

		setLayout(new GridLayout(7, 2));

		firstNameField = new JTextField();
		lastNameField = new JTextField();
		emailField = new JTextField();
		passwordField = new JPasswordField();
		repeatPasswordField = new JPasswordField();
		personnelIdField = new JTextField();

		JButton registerButton = new JButton("Register");
		JButton backButton = new JButton("Back");

		add(new JLabel("First Name:"));
		add(firstNameField);

		add(new JLabel("Last Name:"));
		add(lastNameField);

		add(new JLabel("Email:"));
		add(emailField);

		add(new JLabel("Password:"));
		add(passwordField);

		add(new JLabel("Repeat Password:"));
		add(repeatPasswordField);

		add(new JLabel("Personnel ID:"));
		add(personnelIdField);

		add(backButton);
		add(registerButton);

		backButton.addActionListener(e -> goBack());
		registerButton.addActionListener(e -> register());
	}

	private void register() {

		try {

			Librarian l = system.registerLibrarian(firstNameField.getText(), lastNameField.getText(),
					emailField.getText(), new String(passwordField.getPassword()),
					new String(repeatPasswordField.getPassword()), personnelIdField.getText());

			mainFrame.setCurrentLibrarian(l);

			clearFields();

			JOptionPane.showMessageDialog(this, "Registration successful!");

			mainFrame.showCard(MainFrame.LIBRARIAN_MENU);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Registration Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void clearFields() {
		firstNameField.setText("");
		lastNameField.setText("");
		emailField.setText("");
		passwordField.setText("");
		repeatPasswordField.setText("");
		personnelIdField.setText("");
	}

	private void goBack() {
		mainFrame.showCard(MainFrame.WELCOME);
	}
}
