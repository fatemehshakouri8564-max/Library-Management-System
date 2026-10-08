package ui;

import javax.swing.*;
import java.awt.*;
import model.Student;
import service.LibrarySystem;

public class StudentRegisterPanel extends JPanel {

	private MainFrame mainFrame;
	private LibrarySystem system;

	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JPasswordField repeatPasswordField;
	private JTextField studentNumberField;

	public StudentRegisterPanel(MainFrame mainFrame, LibrarySystem system) {

		this.mainFrame = mainFrame;
		this.system = system;

		setLayout(new GridLayout(7, 2));

		firstNameField = new JTextField();
		lastNameField = new JTextField();
		emailField = new JTextField();
		passwordField = new JPasswordField();
		repeatPasswordField = new JPasswordField();
		studentNumberField = new JTextField();

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

		add(new JLabel("Student Number:"));
		add(studentNumberField);

		add(backButton);
		add(registerButton);

		backButton.addActionListener(e -> goBack());
		registerButton.addActionListener(e -> register());

	}

	private void register() {

		try {

			Student s = system.registerStudent(firstNameField.getText(), lastNameField.getText(), emailField.getText(),
					new String(passwordField.getPassword()), new String(repeatPasswordField.getPassword()),
					studentNumberField.getText());

			mainFrame.setCurrentStudent(s);

			clearFields();

			JOptionPane.showMessageDialog(this, "Registration successful!");

			mainFrame.showCard(MainFrame.STUDENT_MENU);

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
		studentNumberField.setText("");
	}

	private void goBack() {
		mainFrame.showCard(MainFrame.WELCOME);
	}
}
