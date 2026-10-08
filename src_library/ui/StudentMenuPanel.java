package ui;

import service.LibrarySystem;
import javax.swing.*;

import model.Student;

import java.awt.*;

public class StudentMenuPanel extends JPanel {
	
	private MainFrame mainFrame;
	private LibrarySystem system;

	public StudentMenuPanel(MainFrame mainFrame, LibrarySystem system) {
		this.mainFrame = mainFrame;
		this.system = system;

		setLayout(new BorderLayout(10, 10));
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JLabel titleLabel = new JLabel("Student Menu", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		add(titleLabel, BorderLayout.NORTH);
		

		JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));

		JButton searchButton = new JButton("Search Books");
		JButton viewLoansButton = new JButton("View My Loans And Extend");
		JButton reserveButton = new JButton("Reservation");
		JButton logoutButton = new JButton("Logout");

		// Action Listeners
		searchButton.addActionListener(e -> {
			mainFrame.showCard(MainFrame.STUDENT_SEARCH_BOOK);
		});

		viewLoansButton.addActionListener(e -> {
			mainFrame.showCard(MainFrame.VIEW_AND_EXTEND_STUDENT_LOAN);
		});

		reserveButton.addActionListener(e -> {
			mainFrame.showCard(MainFrame.RESERVATION_STUDENT);
		});

		logoutButton.addActionListener(e -> { 
			mainFrame.showCard(MainFrame.WELCOME);
			mainFrame.clearStudent();
		});
		
		
		
		buttonPanel.add(searchButton);
		buttonPanel.add(viewLoansButton);
		buttonPanel.add(reserveButton);
		buttonPanel.add(logoutButton);

		add(buttonPanel, BorderLayout.CENTER);
	}
}
