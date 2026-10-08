package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JList;

import model.Reservation;
import model.Student;
import exception.EmptyVariable;
import exception.YourSearchNotFound;
import exception.YourStudentOrBookNotFound;
import service.LibrarySystem;

public class StudentReservationPanel extends JPanel {
	
	private JTextField bookCodeField;

	private JButton addButton;
	private JButton searchButton;
	private JButton removeButton;
	private JButton showButton;

	private DefaultListModel<String> listModel;
	private JList<String> reservationList;

	public StudentReservationPanel(MainFrame mainFrame, LibrarySystem system) {

		setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(BorderFactory.createTitledBorder("پنل رزرو ها"));

		JLabel bookLabel = new JLabel("کد کتاب");
		bookCodeField = new JTextField(10);

		inputPanel.add(bookLabel);
		inputPanel.add(bookCodeField);

		add(inputPanel, BorderLayout.NORTH);

		//CENTER PANEL

		listModel = new DefaultListModel<>();
		reservationList = new JList<>(listModel);

		JScrollPane scrollPane = new JScrollPane(reservationList);

		add(scrollPane, BorderLayout.CENTER);

		//BUTTON PANEL

		JPanel buttonPanel = new JPanel(new FlowLayout());

		addButton = new JButton("اضافه کردن رزرو");
		searchButton = new JButton("جستجوی رزرو");
		removeButton = new JButton("حذف رزرو");
		showButton = new JButton("نمایش رزرو های من");

		buttonPanel.add(addButton);
		buttonPanel.add(searchButton);
		buttonPanel.add(removeButton);
		buttonPanel.add(showButton);

		add(buttonPanel, BorderLayout.SOUTH);
		
		JButton backButton = new JButton("Back");

		backButton.addActionListener(e -> mainFrame.showCard(MainFrame.STUDENT_MENU));

		add(backButton, BorderLayout.EAST);

		//ADD BUTTON

		addButton.addActionListener(e -> {

			String studentNumber = mainFrame.getCurrentStudent().getStudentNumber();
			String bookCode = bookCodeField.getText();

			try {

				system.addReserve(studentNumber, bookCode);

				JOptionPane.showMessageDialog(null,
						"Reservation added successfully.");

			} catch (EmptyVariable ex) {

				JOptionPane.showMessageDialog(null,
						ex.getMessage());

			} catch (YourStudentOrBookNotFound ex) {

				JOptionPane.showMessageDialog(null,
						ex.getMessage());
			}

		});

		//SEARCH BUTTON

		searchButton.addActionListener(e -> {

			String studentNumber = mainFrame.getCurrentStudent().getStudentNumber();
			String bookCode = bookCodeField.getText();

			try {

				Reservation r = system.searchReservation(bookCode, studentNumber);

				JOptionPane.showMessageDialog(null,
						"Reservation Found\n"
								+ "Student Number: " + r.getStudentNumber()
								+ "\nBook Code: " + r.getBookCode());

			} catch (EmptyVariable ex) {

				JOptionPane.showMessageDialog(null,
						ex.getMessage());

			} catch (YourSearchNotFound ex) {

				JOptionPane.showMessageDialog(null,
						ex.getMessage());
			}

		});

		//REMOVE BUTTON

		removeButton.addActionListener(e -> {

			String studentNumber = mainFrame.getCurrentStudent().getStudentNumber();
			String bookCode = bookCodeField.getText();

			try {

				Reservation r = system.searchReservation(bookCode, studentNumber);

				system.removeReservation(r);

				JOptionPane.showMessageDialog(null,
						"Reservation removed successfully.");

			} catch (EmptyVariable ex) {

				JOptionPane.showMessageDialog(null,
						ex.getMessage());

			} catch (YourSearchNotFound ex) {

				JOptionPane.showMessageDialog(null,
						ex.getMessage());
			}

		});

		//SHOW BUTTON

		showButton.addActionListener(e -> {

			listModel.clear();

			Student student = mainFrame.getCurrentStudent();

			if (student == null) {

				JOptionPane.showMessageDialog(null,
						"Student not found.");

				return;
			}

			ArrayList<Reservation> reservations =
					system.getStudentReservations(student);

			if (reservations.isEmpty()) {
				JOptionPane.showMessageDialog(null,
						"شما رزرو ثبت شده ای ندارید.");
			}
			
			for (Reservation r : reservations) {

				listModel.addElement(
						"Book Code: " + r.getBookCode()
						+ " | Date: " + r.getRequestDate()
						+ " | Status: " + r.getStatus());
			}

		});

	}

}
