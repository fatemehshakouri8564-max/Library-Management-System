package ui;

import service.LibrarySystem;
import javax.swing.*;

import model.Librarian;

import java.awt.*;

public class LibrarianMenuPanel extends JPanel {
	
	private MainFrame mainFrame;
	private LibrarySystem system;

	public LibrarianMenuPanel(MainFrame mainFrame, LibrarySystem system) {
		this.mainFrame = mainFrame;
		this.system = system;

		setLayout(new BorderLayout(10, 10));
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JLabel titleLabel = new JLabel("Librarian Menu", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		add(titleLabel, BorderLayout.NORTH);
		

		JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));

		JButton bookPanel = new JButton("Book Panel");
		JButton viewReservations = new JButton("View Reservations");
		JButton viewExtendRequests = new JButton("View Extend Requests");
		JButton logoutButton = new JButton("Logout");

		// Action Listeners
		bookPanel.addActionListener(e -> {
			mainFrame.showCard(MainFrame.LIBRARIAN_BOOK_PANEL);
		});

		viewReservations.addActionListener(e -> {
			mainFrame.showCard(MainFrame.LIBRARIAN_VIEW_RESERVATION);
		});

		viewExtendRequests.addActionListener(e -> {
			mainFrame.showCard(MainFrame.LIBRARIAN_EXTEND_REQUEST);
		});

		logoutButton.addActionListener(e -> { 
			mainFrame.showCard(MainFrame.WELCOME);
			mainFrame.clearStudent();
		});
		
		
		
		buttonPanel.add(bookPanel);
		buttonPanel.add(viewReservations);
		buttonPanel.add(viewExtendRequests);
		buttonPanel.add(logoutButton);

		add(buttonPanel, BorderLayout.CENTER);
	}
}
