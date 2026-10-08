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
import exception.LoanNotFound;
import model.Loan;
import service.LibrarySystem;

public class StudentLoanManagementPanel extends JPanel {

	private JTextField bookCodeField;
	private JTextField extendField;

	private JButton searchButton;
	private JButton returnButton;
	private JButton extendButton;
	private JButton showButton;

	private DefaultListModel<String> listModel;
	private JList<String> loanList;

	public StudentLoanManagementPanel(MainFrame mainFrame, LibrarySystem system) {

		setLayout(new BorderLayout());

		// INPUT PANEL

		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(BorderFactory.createTitledBorder("مدیریت امانت ها"));

		JLabel codeLabel = new JLabel("کد کتاب");
		bookCodeField = new JTextField(10);

		JLabel extendLabel = new JLabel("مقدار تمدید");
		extendField = new JTextField(5);

		inputPanel.add(codeLabel);
		inputPanel.add(bookCodeField);
		inputPanel.add(extendLabel);
		inputPanel.add(extendField);

		add(inputPanel, BorderLayout.NORTH);

		// BACK BUTTON

		JButton backButton = new JButton("Back");
		backButton.addActionListener(e -> mainFrame.showCard(MainFrame.STUDENT_MENU));
		add(backButton, BorderLayout.EAST);

		// CENTER PANEL

		listModel = new DefaultListModel<>();
		loanList = new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane(loanList);

		add(scrollPane, BorderLayout.CENTER);

		// BUTTON PANEL

		JPanel buttonPanel = new JPanel(new FlowLayout());

		searchButton = new JButton("جستجوی امانت");
		returnButton = new JButton("برگرداندن کتاب");
		extendButton = new JButton("درخواست تمدید مهلت امانت");
		showButton = new JButton("نشان دادن کتاب های امانت من");

		buttonPanel.add(searchButton);
		buttonPanel.add(returnButton);
		buttonPanel.add(extendButton);
		buttonPanel.add(showButton);

		add(buttonPanel, BorderLayout.SOUTH);

		// SEARCH BUTTON

		searchButton.addActionListener(e -> {
			try {
				String studentNumber = mainFrame.getCurrentStudent().getStudentNumber();
				String bookCode = bookCodeField.getText();

				Loan l;
				try {
					l = system.searchLoan(studentNumber, bookCode);
					showLoan(l);
				} catch (EmptyVariable e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
				}

			} catch (LoanNotFound ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage());
			} catch (NullPointerException ex) {
				JOptionPane.showMessageDialog(this, "خطا: دانشجویی وارد نشده است.");
			}
		});

		// RETURN BUTTON

		returnButton.addActionListener(e -> {
			try {
				String studentNumber = mainFrame.getCurrentStudent().getStudentNumber();
				String bookCode = bookCodeField.getText();

				system.returnBook(studentNumber, bookCode);
				JOptionPane.showMessageDialog(this, "کتاب با موفقیت بازگردانده شد.");
				clearFields();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		});

		// EXTEND REQUEST BUTTON

		extendButton.addActionListener(e -> {
			try {
				String studentNumber = mainFrame.getCurrentStudent().getStudentNumber();
				String bookCode = bookCodeField.getText();
				int value = Integer.parseInt(extendField.getText());

				// صدا زدن متد جدید برای ثبت درخواست تمدید
				system.extendRequest(studentNumber, bookCode, value);

				JOptionPane.showMessageDialog(this, "درخواست تمدید با موفقیت ثبت شد و در انتظار تایید کتابدار است.");
				clearFields();

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "مقدار تمدید باید عدد باشد.");
			} catch (EmptyVariable | LoanNotFound ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		});

		// SHOW BUTTON

		showButton.addActionListener(e -> {
			listModel.clear();
			try {
				ArrayList<Loan> loans = system.getStudentLoans(mainFrame.getCurrentStudent());
				if (loans.isEmpty()) {
					JOptionPane.showMessageDialog(this, "شما هیچ امانتی ندارید.");
					return;
				}
				for (Loan l : loans) {
					showLoan(l);
				}
			} catch (NullPointerException ex) {
				JOptionPane.showMessageDialog(this, "خطا: دانشجویی وارد نشده است.");
			}
		});

	}

	private void clearFields() {
		bookCodeField.setText("");
		extendField.setText("");
	}

	private void showLoan(Loan l) {
		listModel.addElement("Book Code: " + l.getBookCode() + " | Due Date: " + l.getDueDate());
	}
}
