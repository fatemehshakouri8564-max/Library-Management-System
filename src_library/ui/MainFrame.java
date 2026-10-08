package ui;

import java.awt.CardLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import model.Student;
import model.Librarian;
import service.LibrarySystem;
import storage.FileManager;

public class MainFrame extends JFrame {

	private CardLayout cardLayout;
	private JPanel cards;
	private LibrarySystem system;
	private Student currentStudent;
	private Librarian currentLibrarian;
	private FileManager fileManager;

	public static final String WELCOME = "welcome";
	public static final String STUDENT_LOGIN = "studentLogin";
	public static final String LIBRARIAN_LOGIN = "librarianLogin";
	public static final String STUDENT_MENU = "studentMenu";
	public static final String LIBRARIAN_MENU = "librarianMenu";
	public static final String STUDENT_REGISTER = "studentRegister";
	public static final String LIBRARIAN_REGISTER = "librarianRegister";
	public static final String STUDENT_SEARCH_BOOK = "SearchBook";
	public static final String VIEW_AND_EXTEND_STUDENT_LOAN = "ViewAndExtendStudentLoan";
	public static final String RESERVATION_STUDENT = "ReservationStudent";
	public static final String LIBRARIAN_BOOK_PANEL = "librarianBookPanel";
	public static final String LIBRARIAN_VIEW_RESERVATION = "librarianViewReservation";
	public static final String LIBRARIAN_EXTEND_REQUEST = "librarianExtendRequest";

	public MainFrame(LibrarySystem system) {

		this.fileManager = new FileManager();
		this.system = system;
		system.loadData();

		setTitle("سیستم مدیریت کتابخانه");
		setSize(800, 600);
		setLocationRelativeTo(null); // وسط صفحه

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				fileManager.saveBooks(system.getBooks());
				fileManager.saveLoans(system.getLoans());
				fileManager.saveStudents(system.getStudents());
				fileManager.saveLibrarians(system.getLibrarians());
				fileManager.saveReservations(system.getReservations());

				dispose();
		        System.exit(0);

			}
		});

		Image icon = new ImageIcon("logo.png").getImage();
		setIconImage(icon);

		cardLayout = new CardLayout();
		cards = new JPanel(cardLayout);

		// Panels:

		WelcomePanel welcome = new WelcomePanel(this);
		StudentLoginPanel studentLogin = new StudentLoginPanel(this, system);
		LibrarianLoginPanel librarianLogin = new LibrarianLoginPanel(this, system);
		StudentRegisterPanel studentRegister = new StudentRegisterPanel(this, system);
		LibrarianRegisterPanel librarianRegister = new LibrarianRegisterPanel(this, system);
		StudentMenuPanel studentMenu = new StudentMenuPanel(this, system);
		LibrarianMenuPanel librarianMenu = new LibrarianMenuPanel(this, system);
		StudentSearchBookPanel studentSearch = new StudentSearchBookPanel(this, system);
		StudentReservationPanel studentReserve = new StudentReservationPanel(this, system);
		StudentLoanManagementPanel studentLoan = new StudentLoanManagementPanel(this, system);
		LibrarianBookPanel librarianBook = new LibrarianBookPanel(this, system);
		LibrarianViewReservationPanel librarianReservation = new LibrarianViewReservationPanel(this, system);
		LibrarianExtendRequest librarianExtend = new LibrarianExtendRequest(this, system);

		// ....

		// Adding panels to cards:

		cards.add(welcome, WELCOME);
		cards.add(studentLogin, STUDENT_LOGIN);
		cards.add(librarianLogin, LIBRARIAN_LOGIN);
		cards.add(studentRegister, STUDENT_REGISTER);
		cards.add(librarianRegister, LIBRARIAN_REGISTER);
		cards.add(studentMenu, STUDENT_MENU);
		cards.add(librarianMenu, LIBRARIAN_MENU);
		cards.add(studentSearch, STUDENT_SEARCH_BOOK);
		cards.add(studentLoan, VIEW_AND_EXTEND_STUDENT_LOAN);
		cards.add(studentReserve, RESERVATION_STUDENT);
		cards.add(librarianBook, LIBRARIAN_BOOK_PANEL);
		cards.add(librarianReservation, LIBRARIAN_VIEW_RESERVATION);
		cards.add(librarianExtend, LIBRARIAN_EXTEND_REQUEST);

		// ...

		add(cards);
		cardLayout.show(cards, WELCOME);

		setVisible(true);

	}

	public Student getCurrentStudent() {
		return currentStudent;
	}

	public void setCurrentStudent(Student currentStudent) {
		this.currentStudent = currentStudent;
	}

	public Librarian getCurrentLibrarian() {
		return currentLibrarian;
	}

	public void setCurrentLibrarian(Librarian currentLibrarian) {
		this.currentLibrarian = currentLibrarian;
	}

	public void clearStudent() {
		currentStudent = null;
	}

	public void clearLibrarian() {
		currentLibrarian = null;
	}

	public void showCard(String cardName) {
		cardLayout.show(cards, cardName);
	}

}