package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Student;
import service.LibrarySystem;

public class StudentLoginPanel extends JPanel {
	
    private MainFrame mainFrame;
    private LibrarySystem system;
	
	private JTextField studentNumberField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JButton backButton;


	public StudentLoginPanel(MainFrame mainFrame, LibrarySystem system) {
		
		this.mainFrame = mainFrame;
		this.system = system;
	
		
		setLayout(new GridLayout(3,2));
		
		backButton = new JButton("Back");
		
		studentNumberField = new JTextField();
		passwordField = new JPasswordField();
		loginButton = new JButton("Login");
		
		add(new JLabel("Student Number:"));
		add(studentNumberField);
		
		add(new JLabel("Password:"));
		add(passwordField);
		
		add(backButton);
		add(loginButton);
		
		backButton.addActionListener(e -> goBack());
		loginButton.addActionListener(e -> login());
		
		
	}

	private void login() {
		
		String studentNumber = studentNumberField.getText();
		String password = new String(passwordField.getPassword());
		
		try {
			
			Student s = system.loginStudent(studentNumber, password);
			mainFrame.setCurrentStudent(s);
			JOptionPane.showMessageDialog(this, "Login Successful");
			mainFrame.showCard(MainFrame.STUDENT_MENU);
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(this, e.getMessage(), "Login Error", JOptionPane.ERROR_MESSAGE);
			
		}
	}
	
	private void goBack() {
		mainFrame.showCard(MainFrame.WELCOME);
	}

}
