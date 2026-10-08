package ui;

import javax.swing.*;

import exception.YourSearchNotFound;
import model.Book;
import service.LibrarySystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentSearchBookPanel extends JPanel {

	private JTextField searchField;
	private JComboBox<String> searchTypeCombo;
	private JButton searchButton;
	private JTextArea resultArea;
	private LibrarySystem system;
	private MainFrame mainFrame;

	public StudentSearchBookPanel(MainFrame mainFrame, LibrarySystem system) {
		this.system = system;
		this.mainFrame = mainFrame;

		setLayout(new BorderLayout(10, 10));

		JPanel topPanel = new JPanel(new FlowLayout());

		searchField = new JTextField(15);
		String[] options = { "Title", "ISBN", "Book Code", "Author", "Subject", "Year" };
		searchTypeCombo = new JComboBox<>(options);
		searchButton = new JButton("Search");

		topPanel.add(new JLabel("Search By:"));
		topPanel.add(searchTypeCombo);
		topPanel.add(searchField);
		topPanel.add(searchButton);

		// بخش مرکزی: نمایش نتیجه
		resultArea = new JTextArea(10, 30);
		resultArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(resultArea);
		resultArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		resultArea.setFont(new Font("Tahoma", Font.PLAIN, 14));

		add(topPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);

		searchButton.addActionListener(e -> executeSearch());

		JButton backButton = new JButton("Back");

		backButton.addActionListener(e -> mainFrame.showCard(MainFrame.STUDENT_MENU));

		add(backButton, BorderLayout.EAST);

	}

	private void executeSearch() {
		String query = searchField.getText().trim();
		String type = (String) searchTypeCombo.getSelectedItem();

		if (query.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter a search term.");
			return;
		}

		try {
			Book foundBook = null;

			switch (type) {
			case "ISBN":
				foundBook = system.searchBookByISBN(query);
				searchField.setText("");
				break;
			case "Book Code":
				foundBook = system.searchBookByBookCode(query);
				searchField.setText("");
				break;
			case "Title":
				foundBook = system.searchBookByTitle(query);
				searchField.setText("");
				break;
			case "Author":
				foundBook = system.searchBookByAuthor(query);
				searchField.setText("");
				break;
			case "Subject":
				foundBook = system.serachBookBySubject(query);
				searchField.setText("");
				break;
			case "Year":
				int year = Integer.parseInt(query);
				foundBook = system.searchBookByYearOfPublish(year);
				searchField.setText("");
				break;
			}

			if (foundBook != null) {
				resultArea.setText("کتاب پیدا شد:\n" + "عنوان: " + foundBook.getTitle() + "\nنویسنده: "
						+ foundBook.getAuthor() + "\nناشر: " + foundBook.getPublisher() + "\nسال انتشار: "
						+ foundBook.getYearOfPublish() + "\nشابک:" + foundBook.getIsbn() + "\nکد :کتاب"
						+ foundBook.getBookCode() + "\nموجودی:" + foundBook.getCount());
			}

		} catch (YourSearchNotFound ex) {
			resultArea.setText("Result: " + ex.getMessage());
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Please enter a valid number for Year.");
		}
	}
}
