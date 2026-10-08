package service;

import java.awt.Container;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import exception.EmailAlreadyExists;
import exception.EmptyVariable;
import exception.IncorrectPasswordRepetition;
import exception.InvalidExtendValue;
import exception.LoanNotFound;
import exception.PersonnelIDAlreadyExists;
import exception.StudentNumberAlreadyExists;
import exception.TwiceExtend;
import exception.WrongPersonnelIDOrPassword;
import exception.WrongStudentNumberOrPassword;
import exception.YourSearchNotFound;
import exception.YourStudentOrBookNotFound;
import model.Book;
import model.Librarian;
import model.Loan;
import model.Reservation;
import model.ReservationStatus;
import model.Student;
import storage.FileManager;

public class LibrarySystem {

	private ArrayList<Book> books;
	private ArrayList<Student> students;
	private ArrayList<Librarian> librarians;
	private ArrayList<Loan> loans;
	private ArrayList<Reservation> reservations;
	private ArrayList<Loan> extendRequestLoans;

	public LibrarySystem() {
		this.books = new ArrayList<Book>();
		this.librarians = new ArrayList<Librarian>();
		this.loans = new ArrayList<Loan>();
		this.reservations = new ArrayList<Reservation>();
		this.students = new ArrayList<Student>();
		this.extendRequestLoans = new ArrayList<Loan>();
	}

	// Getters:

	public ArrayList<Book> getBooks() {
		return books;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public ArrayList<Librarian> getLibrarians() {
		return librarians;
	}

	public ArrayList<Loan> getLoans() {
		return loans;
	}

	public ArrayList<Reservation> getReservations() {
		return reservations;
	}

	// Book relevant:

	public void addBook(Book newBook) {
		books.add(newBook);
	}

	public void removeBook(Book bookName) {
		books.remove(bookName);
	}

	public Book searchBookByISBN(String isbn) throws YourSearchNotFound {

		for (Book b : books) {
			if (b.getIsbn().equals(isbn)) {
				return b;
			}
		}

		throw new YourSearchNotFound("Your book not found.");
	}

	public Book searchBookByBookCode(String bookCode) throws YourSearchNotFound {

		for (Book b : books) {
			if (b.getBookCode().equals(bookCode)) {
				return b;
			}
		}

		throw new YourSearchNotFound("Your book not found.");
	}

	public Book searchBookByTitle(String title) throws YourSearchNotFound {

		for (Book b : books) {
			if (b.getTitle().equals(title)) {
				return b;
			}
		}

		throw new YourSearchNotFound("Your book not found.");
	}

	public Book searchBookByAuthor(String author) throws YourSearchNotFound {

		for (Book b : books) {
			if (b.getAuthor().equals(author)) {
				return b;
			}
		}

		throw new YourSearchNotFound("Your book not found.");

	}

	public Book serachBookBySubject(String subject) throws YourSearchNotFound {

		for (Book b : books) {
			if (b.getSubject().equals(subject)) {
				return b;
			}
		}

		throw new YourSearchNotFound("Your book not found.");

	}

	public Book searchBookByYearOfPublish(int yearOfPublish) throws YourSearchNotFound {

		for (Book b : books) {
			if (b.getYearOfPublish() == yearOfPublish) {
				return b;
			}
		}

		throw new YourSearchNotFound("Your book not found.");

	}

	// Student relevant:

	public Student registerStudent(String firstName, String lastName, String email, String password,
			String passwordRepetition, String studentNumber) throws Exception {
		// Empty variables:
		if (firstName.isEmpty()) {
			throw new EmptyVariable("Your first name field is empty");
		} else if (lastName.isEmpty()) {
			throw new EmptyVariable("Your last name field is empty");
		} else if (email.isEmpty()) {
			throw new EmptyVariable("Your email field is empty");
		} else if (password.isEmpty()) {
			throw new EmptyVariable("Your password field is empty");
		} else if (passwordRepetition.isEmpty()) {
			throw new EmptyVariable("Your password repetition field is empty");
		} else if (studentNumber.isEmpty()) {
			throw new EmptyVariable("Your student number field is empty");
		}

		// Already Exists:
		for (Student s : students) {

			if (s.getEmail().equals(email)) {
				throw new EmailAlreadyExists("This email is already registered.");
			} else if (s.getStudentNumber().equals(studentNumber)) {
				throw new StudentNumberAlreadyExists("This student is already registered");
			}

		}

		// Password repetition

		if (!(password.equals(passwordRepetition))) {
			throw new IncorrectPasswordRepetition("Your password repetition is incorrect.");
		}

		// Successful register.

		Student s = new Student(firstName, lastName, email, password, studentNumber, 0);
		students.add(s);
		return s;
	}

	public Student loginStudent(String studentNumber, String password) throws Exception {

		if (password.isEmpty()) {
			throw new EmptyVariable("Your password field is empty");
		} else if (studentNumber.isEmpty()) {
			throw new EmptyVariable("Your student number field is empty");
		}
		for (Student s : students) {
			if (s.getStudentNumber().equals(studentNumber) && s.getPassword().equals(password)) {
				return s;
			}
		}

		throw new WrongStudentNumberOrPassword("Your student number or password is incorrect");

	}

	public Student searchByNumber(String num) throws YourSearchNotFound {

		for (Student s : students) {
			if (s.getStudentNumber().equals(num)) {
				return s;
			}
		}

		throw new YourSearchNotFound("User not found.");
	}

	// Librarian relevant:

	public Librarian registerLibrarian(String firstName, String lastName, String email, String password,
			String passwordRepetition, String personnelId) throws Exception {
		// Empty variables:
		if (firstName.isEmpty()) {
			throw new EmptyVariable("Your first name field is empty");
		} else if (lastName.isEmpty()) {
			throw new EmptyVariable("Your last name field is empty");
		} else if (email.isEmpty()) {
			throw new EmptyVariable("Your email field is empty");
		} else if (password.isEmpty()) {
			throw new EmptyVariable("Your password field is empty");
		} else if (passwordRepetition.isEmpty()) {
			throw new EmptyVariable("Your password repetition field is empty");
		} else if (personnelId.isEmpty()) {
			throw new EmptyVariable("Your personel ID field is empty");
		}

		// Already Exists:
		for (Librarian l : librarians) {

			if (l.getEmail().equals(email)) {
				throw new EmailAlreadyExists("This email is already registered.");
			} else if (l.getPersonnelId().equals(personnelId)) {
				throw new PersonnelIDAlreadyExists("This librarian is already registered");
			}

		}

		// Password repetition

		if (!(password.equals(passwordRepetition))) {
			throw new IncorrectPasswordRepetition("Your password repetition is incorrect.");
		}

		// Successful register.

		Librarian l = new Librarian(firstName, lastName, email, password, personnelId);
		librarians.add(l);
		return l;
	}

	public Librarian loginLibrarian(String personnelId, String password) throws Exception {

		if (password.isEmpty()) {
			throw new EmptyVariable("Your password field is empty");
		} else if (personnelId.isEmpty()) {
			throw new EmptyVariable("Your personnel ID field is empty");
		}
		for (Librarian l : librarians) {
			if (l.getPersonnelId().equals(personnelId) && l.getPassword().equals(password)) {
				return l;
			}
		}

		throw new WrongPersonnelIDOrPassword("Your personel ID or password is incorrect");

	}

	public Librarian searchByID(String Id) throws YourSearchNotFound {

		for (Librarian l : librarians) {
			if (l.getPersonnelId().equals(Id)) {
				return l;
			}
		}

		throw new YourSearchNotFound("User not found.");
	}

	// Loan relevant:

	public void loanBook(String studentNumber, String bookCode) throws EmptyVariable, YourStudentOrBookNotFound {

		if (studentNumber.isEmpty()) {
			throw new EmptyVariable("Your student number field is empty.");
		} else if (bookCode.isEmpty()) {
			throw new EmptyVariable("Your book code field is empty.");
		}

		for (Student s : students) {
			for (Book b : books) {

				if (b.getBookCode().equals(bookCode) && s.getStudentNumber().equals(studentNumber)
						&& b.getCount() > 0) {
					LocalDate today = LocalDate.now();
					loans.add(new Loan(studentNumber, bookCode, today, today.plusMonths(1), false));
					return;
				}
			}
		}

		throw new YourStudentOrBookNotFound("Your student or book not found. ");

	}

	public void returnBook(String studentNumber, String bookCode)
			throws LoanNotFound, EmptyVariable, YourSearchNotFound {

		if (studentNumber.isEmpty()) {
			throw new EmptyVariable("Your student number field is empty.");
		} else if (bookCode.isEmpty()) {
			throw new EmptyVariable("Your book code field is empty.");
		}

		LocalDate today = LocalDate.now();

		for (Loan l : loans) {

			if (l.getBookCode().equals(bookCode) && l.getStudentNumber().equals(studentNumber)) {
				if (today.isBefore(l.getDueDate())) {
					Book b = searchBookByBookCode(bookCode);
					b.addCount(1);
					loans.remove(l);
					return;
				} else {

					int days = (int) ChronoUnit.DAYS.between(l.getDueDate(), today);
					Book b = searchBookByBookCode(bookCode);
					b.addCount(1);
					loans.remove(l);

					Student s = searchByNumber(studentNumber);
					s.addDebt(days * 5000);
					return;
				}
			}
		}

		throw new LoanNotFound("There is no loan record with these informations");

	}

	public void extendLoan(String studentNumber, String bookCode, int value)
			throws EmptyVariable, InvalidExtendValue, LoanNotFound, TwiceExtend {

		if (studentNumber.isEmpty()) {
			throw new EmptyVariable("Your student number field is empty.");
		} else if (bookCode.isEmpty()) {
			throw new EmptyVariable("Your book code field is empty.");
		} else if (value <= 0) {
			throw new EmptyVariable("Your value field is not valid");
		} else if (value > 7) {
			throw new InvalidExtendValue("You can't extend your loan more then 7 days");
		}

		for (Loan l : loans) {

			if (l.getBookCode().equals(bookCode) && l.getStudentNumber().equals(studentNumber)) {
				if (l.getExtended()) {
					throw new TwiceExtend("You can't extend your loan twice.");
				} else {
					l.setDue(l.getDueDate().plusDays(value));
					l.setExtended();
					return;
				}
			}

		}

		throw new LoanNotFound("There is no loan record with these informations");

	}

	public Loan searchLoan(String studentNumber, String bookCode) throws EmptyVariable, LoanNotFound {

		if (studentNumber.isEmpty()) {
			throw new EmptyVariable("Your student number field is empty.");
		} else if (bookCode.isEmpty()) {
			throw new EmptyVariable("Your book code field is empty.");
		}

		for (Loan l : loans) {

			if (l.getBookCode().equals(bookCode) && l.getStudentNumber().equals(studentNumber)) {
				return l;
			}

		}

		throw new LoanNotFound("There is no loan record with these informations");

	}

	public ArrayList<Loan> getStudentLoans(Student student) {

		ArrayList<Loan> studentLoans = new ArrayList<Loan>();

		for (Loan l : loans) {
			if (student.getStudentNumber().equals(l.getStudentNumber())) {
				studentLoans.add(l);
			}
		}

		return studentLoans;

	}

	public ArrayList<Loan> getBookLoans(Book book) {

		ArrayList<Loan> bookLoans = new ArrayList<Loan>();

		for (Loan l : loans) {
			if (book.getBookCode().equals(l.getBookCode())) {
				bookLoans.add(l);
			}
		}

		return bookLoans;

	}

	public void extendRequest(String studentNumber, String bookCode, int value) throws EmptyVariable, LoanNotFound {

		Loan l = searchLoan(studentNumber, bookCode);
		extendRequestLoans.add(l);
		l.setExtendDaysRequest(value);

	}

	public ArrayList<Loan> getExtendRequestLoans() {
		return extendRequestLoans;
	}

	// Reservation relevant:

	public void addReserve(String studentNumber, String bookCode) throws EmptyVariable, YourStudentOrBookNotFound {

		if (studentNumber.isEmpty()) {
			throw new EmptyVariable("Your student number field is empty.");
		} else if (bookCode.isEmpty()) {
			throw new EmptyVariable("Your book code field is empty.");
		}

		for (Student s : students) {
			for (Book b : books) {

				if (b.getBookCode().equals(bookCode) && s.getStudentNumber().equals(studentNumber)) {
					LocalDate today = LocalDate.now();
					reservations.add(new Reservation(studentNumber, bookCode, today, ReservationStatus.PENDING));
					return;
				}
			}
		}

		throw new YourStudentOrBookNotFound("Your student or book not found. ");

	}

	public ArrayList<Reservation> showReservations() {
		return reservations;
	}

	public ArrayList<Reservation> getStudentReservations(Student student) {

		ArrayList<Reservation> studentReservations = new ArrayList<Reservation>();

		for (Reservation r : reservations) {
			if (student.getStudentNumber().equals(r.getStudentNumber())) {
				studentReservations.add(r);
			}
		}

		return studentReservations;

	}

	public ArrayList<Reservation> getBookReservations(Book book) {

		ArrayList<Reservation> bookReservations = new ArrayList<Reservation>();

		for (Reservation r : reservations) {
			if (book.getBookCode().equals(r.getBookCode())) {
				bookReservations.add(r);
			}
		}

		return bookReservations;
	}

	public Reservation searchReservation(String bookCode, String studentNumber)
			throws YourSearchNotFound, EmptyVariable {

		if (studentNumber.isEmpty()) {
			throw new EmptyVariable("Your student number field is empty.");
		} else if (bookCode.isEmpty()) {
			throw new EmptyVariable("Your book code field is empty.");
		}

		for (Reservation r : reservations) {
			if (r.getBookCode().equals(bookCode) && r.getStudentNumber().equals(studentNumber)) {
				return r;
			}

		}

		throw new YourSearchNotFound("Your reservation not found.");

	}

	public void approveReservation(String bookCode, String studentNumber)
			throws YourSearchNotFound, EmptyVariable, YourStudentOrBookNotFound {

		Reservation r = searchReservation(bookCode, studentNumber);

		r.setStatus(ReservationStatus.APPROVED);
		loanBook(studentNumber, bookCode);
		reservations.remove(r);

	}

	public void rejectReservation(String bookCode, String studentNumber) throws YourSearchNotFound, EmptyVariable {

		Reservation r = searchReservation(bookCode, studentNumber);

		r.setStatus(ReservationStatus.REJECTED);
		reservations.remove(r);

	}

	public void removeReservation(Reservation r) {
		reservations.remove(r);
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public void setLibrarians(ArrayList<Librarian> librarians) {
		this.librarians = librarians;
	}

	public void setLoans(ArrayList<Loan> loans) {
		this.loans = loans;
	}

	public void setReservations(ArrayList<Reservation> reservations) {
		this.reservations = reservations;
	}

	public void loadData() {
		FileManager fileManager = new FileManager();
		books = fileManager.loadBooks();
		students = fileManager.loadStudents();
		librarians = fileManager.loadLibrarians();
		loans = fileManager.loadLoans();
		reservations = fileManager.loadReservations();

	}

}
