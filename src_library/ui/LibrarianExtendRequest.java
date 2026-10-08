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
import exception.InvalidExtendValue;
import exception.LoanNotFound;
import exception.TwiceExtend;
import model.Loan;
import service.LibrarySystem;

public class LibrarianExtendRequest extends JPanel {

	private JTextField studentNumberField;
	private JTextField bookCodeField;
	private JTextField valueField;

	private JButton showRequestsButton;
	private JButton approveExtendButton;

	private DefaultListModel<String> listModel;
	private JList<String> requestList;

	public LibrarianExtendRequest(MainFrame mainFrame, LibrarySystem system) {

		setLayout(new BorderLayout());

		// INPUT PANEL

		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(BorderFactory.createTitledBorder("مدیریت درخواست های تمدید"));

		JLabel studentLabel = new JLabel("شماره دانشجویی");
		studentNumberField = new JTextField(10);

		JLabel bookLabel = new JLabel("کد کتاب");
		bookCodeField = new JTextField(10);

		JLabel valueLabel = new JLabel("روزهای تمدید");
		valueField = new JTextField(5);

		inputPanel.add(studentLabel);
		inputPanel.add(studentNumberField);
		inputPanel.add(bookLabel);
		inputPanel.add(bookCodeField);
		inputPanel.add(valueLabel);
		inputPanel.add(valueField);

		add(inputPanel, BorderLayout.NORTH);

		// BACK BUTTON

		JButton backButton = new JButton("Back");
		backButton.addActionListener(e -> mainFrame.showCard(MainFrame.LIBRARIAN_MENU));
		add(backButton, BorderLayout.EAST);

		// CENTER PANEL

		listModel = new DefaultListModel<>();
		requestList = new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane(requestList);

		add(scrollPane, BorderLayout.CENTER);

		// BUTTON PANEL

		JPanel buttonPanel = new JPanel(new FlowLayout());

		showRequestsButton = new JButton("مشاهده درخواست های تمدید");
		approveExtendButton = new JButton("تایید تمدید");

		buttonPanel.add(showRequestsButton);
		buttonPanel.add(approveExtendButton);

		add(buttonPanel, BorderLayout.SOUTH);

		// SHOW REQUESTS BUTTON

		showRequestsButton.addActionListener(e -> {
			listModel.clear();
			
			ArrayList<Loan> requests = system.getExtendRequestLoans(); 
			
			if (requests.isEmpty()) {
				JOptionPane.showMessageDialog(this, "هیچ درخواست تمدیدی وجود ندارد.");
				return;
			}

			for (Loan l : requests) {
				showRequest(l);
			}
		});

		// APPROVE EXTEND BUTTON

		approveExtendButton.addActionListener(e -> {
			try {
				String studentNumber = studentNumberField.getText();
				String bookCode = bookCodeField.getText();
				int value = Integer.parseInt(valueField.getText());

				system.extendLoan(studentNumber, bookCode, value);
				
				Loan target = system.searchLoan(studentNumber, bookCode);
				system.getExtendRequestLoans().remove(target);

				JOptionPane.showMessageDialog(this, "درخواست تمدید با موفقیت تایید و اعمال شد.");
				clearFields();
				
				showRequestsButton.doClick();

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "مقدار تمدید باید عدد باشد.");
			} catch (EmptyVariable | InvalidExtendValue | LoanNotFound | TwiceExtend ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		});

	}

	private void clearFields() {
		studentNumberField.setText("");
		bookCodeField.setText("");
		valueField.setText("");
	}

	private void showRequest(Loan l) {
		listModel.addElement("Student: " + l.getStudentNumber() + 
				" | Book Code: " + l.getBookCode() + 
				" | Requested Days: " + l.getExtendDaysRequest());
	}
}
