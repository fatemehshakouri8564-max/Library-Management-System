package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import exception.EmptyVariable;
import exception.YourSearchNotFound;
import exception.YourStudentOrBookNotFound;
import model.Reservation;
import service.LibrarySystem;

public class LibrarianViewReservationPanel extends JPanel {

	private JTextField studentNumberField;
	private JTextField bookCodeField;

	private JButton showButton;
	private JButton approveButton;
	private JButton rejectButton;

	private DefaultListModel<String> listModel;
	private JList<String> reservationList;

	public LibrarianViewReservationPanel(MainFrame mainFrame, LibrarySystem system) {

		setLayout(new BorderLayout());

		// INPUT PANEL

		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(BorderFactory.createTitledBorder("مدیریت درخواست های رزرو"));

		JLabel studentLabel = new JLabel("شماره دانشجویی");
		studentNumberField = new JTextField(10);

		JLabel bookLabel = new JLabel("کد کتاب");
		bookCodeField = new JTextField(10);

		inputPanel.add(studentLabel);
		inputPanel.add(studentNumberField);

		inputPanel.add(bookLabel);
		inputPanel.add(bookCodeField);

		add(inputPanel, BorderLayout.NORTH);

		// BACK BUTTON

		JButton backButton = new JButton("Back");
		backButton.addActionListener(e -> mainFrame.showCard(MainFrame.LIBRARIAN_MENU));
		add(backButton, BorderLayout.EAST);

		// CENTER PANEL

		listModel = new DefaultListModel<>();
		reservationList = new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane(reservationList);

		add(scrollPane, BorderLayout.CENTER);

		// BUTTON PANEL

		JPanel buttonPanel = new JPanel(new FlowLayout());

		showButton = new JButton("نمایش همه رزروها");
		approveButton = new JButton("تایید رزرو");
		rejectButton = new JButton("رد رزرو");

		buttonPanel.add(showButton);
		buttonPanel.add(approveButton);
		buttonPanel.add(rejectButton);

		add(buttonPanel, BorderLayout.SOUTH);

		// SHOW BUTTON

		showButton.addActionListener(e -> {

			listModel.clear();

			ArrayList<Reservation> reservations = system.showReservations();

			if (reservations.isEmpty()) {
				JOptionPane.showMessageDialog(this, "هیچ رزروی ثبت نشده است.");
				return;
			}

			for (Reservation r : reservations) {
				showReservation(r);
			}
		});

		// APPROVE BUTTON

		approveButton.addActionListener(e -> {

			try {

				String studentNumber = studentNumberField.getText();
				String bookCode = bookCodeField.getText();

				system.approveReservation(bookCode, studentNumber);

				JOptionPane.showMessageDialog(this, "رزرو با موفقیت تایید شد.");
				clearFields();

			} catch (EmptyVariable | YourSearchNotFound | YourStudentOrBookNotFound ex) {

				JOptionPane.showMessageDialog(this, ex.getMessage());
			}

		});

		// REJECT BUTTON

		rejectButton.addActionListener(e -> {

			try {

				String studentNumber = studentNumberField.getText();
				String bookCode = bookCodeField.getText();

				system.rejectReservation(bookCode, studentNumber);

				JOptionPane.showMessageDialog(this, "رزرو رد شد.");
				clearFields();

			} catch (EmptyVariable | YourSearchNotFound ex) {

				JOptionPane.showMessageDialog(this, ex.getMessage());
			}

		});

	}

	// helper method

	private void showReservation(Reservation r) {

		listModel.addElement(
				"Student: " + r.getStudentNumber()
				+ " | Book: " + r.getBookCode()
				+ " | Status: " + r.getStatus());
	}

	private void clearFields() {

		studentNumberField.setText("");
		bookCodeField.setText("");
	}

}
