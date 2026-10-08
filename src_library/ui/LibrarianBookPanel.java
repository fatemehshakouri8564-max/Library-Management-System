package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import exception.YourSearchNotFound;
import model.Book;
import service.LibrarySystem;

public class LibrarianBookPanel extends JPanel {

	private JTextField titleField;
	private JTextField authorField;
	private JTextField isbnField;
	private JTextField yearField;
	private JTextField subjectField;
	private JTextField publisherField;
	private JTextField countField;
	private JTextField bookCodeField;

	private JButton addButton;
	private JButton removeButton;

	private JButton searchIsbnButton;
	private JButton searchCodeButton;
	private JButton searchTitleButton;
	private JButton searchAuthorButton;
	private JButton searchSubjectButton;
	private JButton searchYearButton;

	private DefaultListModel<String> listModel;
	private JList<String> bookList;

	public LibrarianBookPanel(MainFrame mainFrame, LibrarySystem system) {

		setLayout(new BorderLayout());

		// INPUT PANEL

		JPanel inputPanel = new JPanel();

		inputPanel.setBorder(BorderFactory.createTitledBorder("مدیریت کتاب ها"));

		JLabel titleLabel = new JLabel("عنوان");
		titleField = new JTextField(8);

		JLabel authorLabel = new JLabel("نویسنده");
		authorField = new JTextField(8);

		JLabel isbnLabel = new JLabel("شابک");
		isbnField = new JTextField(8);

		JLabel yearLabel = new JLabel("سال انتشار");
		yearField = new JTextField(5);

		JLabel subjectLabel = new JLabel("موضوع");
		subjectField = new JTextField(8);

		JLabel publisherLabel = new JLabel("ناشر");
		publisherField = new JTextField(8);

		JLabel countLabel = new JLabel("تعداد");
		countField = new JTextField(5);

		JLabel codeLabel = new JLabel("کد کتاب");
		bookCodeField = new JTextField(8);

		inputPanel.add(titleLabel);
		inputPanel.add(titleField);

		inputPanel.add(authorLabel);
		inputPanel.add(authorField);

		inputPanel.add(isbnLabel);
		inputPanel.add(isbnField);

		inputPanel.add(yearLabel);
		inputPanel.add(yearField);

		inputPanel.add(subjectLabel);
		inputPanel.add(subjectField);

		inputPanel.add(publisherLabel);
		inputPanel.add(publisherField);

		inputPanel.add(countLabel);
		inputPanel.add(countField);

		inputPanel.add(codeLabel);
		inputPanel.add(bookCodeField);

		add(inputPanel, BorderLayout.NORTH);

		JButton backButton = new JButton("Back");

		backButton.addActionListener(e -> mainFrame.showCard(MainFrame.LIBRARIAN_MENU));

		add(backButton, BorderLayout.EAST);

		// CENTER PANEL

		listModel = new DefaultListModel<>();

		bookList = new JList<>(listModel);

		JScrollPane scrollPane = new JScrollPane(bookList);

		add(scrollPane, BorderLayout.CENTER);

		// BUTTON PANEL

		JPanel buttonPanel = new JPanel(new FlowLayout());

		addButton = new JButton("اضافه کردن کتاب");
		removeButton = new JButton("حذف کتاب");

		searchIsbnButton = new JButton("جستجوی شابک");
		searchCodeButton = new JButton("جستجوی کد");
		searchTitleButton = new JButton("جستجوی عنوان");
		searchAuthorButton = new JButton("جستجوی نویسنده");
		searchSubjectButton = new JButton("جستجوی موضوع");
		searchYearButton = new JButton("جستجوی سال");

		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);

		buttonPanel.add(searchIsbnButton);
		buttonPanel.add(searchCodeButton);
		buttonPanel.add(searchTitleButton);
		buttonPanel.add(searchAuthorButton);
		buttonPanel.add(searchSubjectButton);
		buttonPanel.add(searchYearButton);

		add(buttonPanel, BorderLayout.SOUTH);

		// ADD BUTTON

		addButton.addActionListener(e -> {

			try {

				String title = titleField.getText();
				String author = authorField.getText();
				String isbn = isbnField.getText();
				int year = Integer.parseInt(yearField.getText());
				String subject = subjectField.getText();
				String publisher = publisherField.getText();
				int count = Integer.parseInt(countField.getText());
				String code = bookCodeField.getText();

				Book b = new Book(title, author, isbn, year, subject, publisher, count, code);

				system.addBook(b);

				JOptionPane.showMessageDialog(this, "Book added successfully.");
				clearFields();

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Year and Count must be numbers.");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}

		});

		// REMOVE BUTTON

		removeButton.addActionListener(e -> {

			try {

				Book b = system.searchBookByBookCode(bookCodeField.getText());

				system.removeBook(b);
				clearFields();

				JOptionPane.showMessageDialog(this, "Book removed successfully.");

			} catch (YourSearchNotFound ex) {

				JOptionPane.showMessageDialog(this, ex.getMessage());
			}

		});

		// SEARCH ISBN

		searchIsbnButton.addActionListener(e -> {

			try {

				Book b = system.searchBookByISBN(isbnField.getText());
				showBook(b);

			} catch (YourSearchNotFound ex) {

				JOptionPane.showMessageDialog(this, ex.getMessage());
			}

		});

		// SEARCH CODE

		searchCodeButton.addActionListener(e -> {

			try {

				Book b = system.searchBookByBookCode(bookCodeField.getText());
				showBook(b);

			} catch (YourSearchNotFound ex) {

				JOptionPane.showMessageDialog(this, ex.getMessage());
			}

		});

		// SEARCH TITLE

		searchTitleButton.addActionListener(e -> {

			try {

				Book b = system.searchBookByTitle(titleField.getText());
				showBook(b);

			} catch (YourSearchNotFound ex) {

				JOptionPane.showMessageDialog(this, ex.getMessage());
			}

		});

		// SEARCH AUTHOR

		searchAuthorButton.addActionListener(e -> {

			try {

				Book b = system.searchBookByAuthor(authorField.getText());
				showBook(b);

			} catch (YourSearchNotFound ex) {

				JOptionPane.showMessageDialog(this, ex.getMessage());
			}

		});

		// SEARCH SUBJECT

		searchSubjectButton.addActionListener(e -> {

			try {

				Book b = system.serachBookBySubject(subjectField.getText());
				showBook(b);

			} catch (YourSearchNotFound ex) {

				JOptionPane.showMessageDialog(this, ex.getMessage());
			}

		});

		// SEARCH YEAR

		searchYearButton.addActionListener(e -> {

			try {

				int year = Integer.parseInt(yearField.getText());

				Book b = system.searchBookByYearOfPublish(year);
				showBook(b);

			} catch (NumberFormatException ex) {

				JOptionPane.showMessageDialog(this, "Year must be a number.");

			} catch (Exception ex) {

				JOptionPane.showMessageDialog(this, ex.getMessage());
			}

		});

	}

	private void clearFields() {
		titleField.setText("");
		authorField.setText("");
		isbnField.setText("");
		yearField.setText("");
		subjectField.setText("");
		publisherField.setText("");
		countField.setText("");
		bookCodeField.setText("");
	}

	private void showBook(Book b) {

		listModel.clear();

		listModel.addElement("Title: " + b.getTitle() + " | Author: " + b.getAuthor() + " | Code: " + b.getBookCode()
				+ " | Count: " + b.getCount());
	}

}
