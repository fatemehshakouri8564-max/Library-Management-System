package storage;

import java.io.*;
import java.util.ArrayList;
import java.time.*;

import model.Book;
import model.Librarian;
import model.Loan;
import model.Reservation;
import model.ReservationStatus;
import model.Student;

public class FileManager {

	public void saveBooks(ArrayList<Book> books) {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("books.txt"));

			for (Book b : books) {
				writer.write(b.getTitle() + "," + b.getAuthor() + "," + b.getIsbn() + "," + b.getYearOfPublish() + ","
						+ b.getSubject() + "," + b.getPublisher() + "," + b.getCount() + "," + b.getBookCode() + "\n");
			}

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Book> loadBooks() {

		ArrayList<Book> books = new ArrayList<>();

		try {

			BufferedReader reader = new BufferedReader(new FileReader("books.txt"));

			String input = reader.readLine();

			while (input != null) {

				String[] i = input.split(",");
				Book b = new Book(i[0], i[1], i[2], Integer.parseInt(i[3]), i[4], i[5], Integer.parseInt(i[6]), i[7]);
				books.add(b);
				input = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return books;

	}

	public void saveStudents(ArrayList<Student> students) {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt"));

			for (Student s : students) {

				writer.write(s.getFirstName() + "," + s.getLastName() + "," + s.getEmail() + "," + s.getPassword() + ","
						+ s.getStudentNumber() + "," + s.getDebt() + "\n");

			}

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Student> loadStudents() {

		ArrayList<Student> students = new ArrayList<>();

		try {

			BufferedReader reader = new BufferedReader(new FileReader("students.txt"));

			String input = reader.readLine();

			while (input != null) {

				String[] i = input.split(",");
				Student s = new Student(i[0], i[1], i[2], i[3], i[4], Integer.parseInt(i[5]));
				students.add(s);
				input = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return students;
	}

	public void saveLibrarians(ArrayList<Librarian> librarians) {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("librarians.txt"));

			for (Librarian l : librarians) {

				writer.write(l.getFirstName() + "," + l.getLastName() + "," + l.getEmail() + "," + l.getPassword() + ","
						+ l.getPersonnelId() + "\n");

			}

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Librarian> loadLibrarians() {

		ArrayList<Librarian> librarians = new ArrayList<>();

		try {

			BufferedReader reader = new BufferedReader(new FileReader("librarians.txt"));

			String input = reader.readLine();

			while (input != null) {

				String[] i = input.split(",");
				Librarian l = new Librarian(i[0], i[1], i[2], i[3], i[4]);
				librarians.add(l);
				input = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return librarians;
	}

	public void saveLoans(ArrayList<Loan> loans) {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("loans.txt"));

			for (Loan l : loans) {

				writer.write(l.getStudentNumber() + "," + l.getBookCode() + "," + l.getLoanDate() + "," + l.getDueDate()
						+ "," + l.getExtended() + "\n");

			}

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Loan> loadLoans() {

		ArrayList<Loan> loans = new ArrayList<>();

		try {

			BufferedReader reader = new BufferedReader(new FileReader("loans.txt"));

			String input = reader.readLine();

			while (input != null) {

				String[] i = input.split(",");
				Loan l = new Loan(i[0], i[1], LocalDate.parse(i[2]), LocalDate.parse(i[3]), Boolean.parseBoolean(i[4]));
				loans.add(l);
				input = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return loans;
	}

	public void saveReservations(ArrayList<Reservation> reservations) {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("reservations.txt"));

			for (Reservation r : reservations) {

				writer.write(r.getStudentNumber() + "," + r.getBookCode() + "," + r.getRequestDate() + ","
						+ r.getStatus() + "\n");

			}

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Reservation> loadReservations() {

		ArrayList<Reservation> reservations = new ArrayList<>();

		try {

			BufferedReader reader = new BufferedReader(new FileReader("reservations.txt"));

			String input = reader.readLine();

			while (input != null) {

				String[] i = input.split(",");
				ReservationStatus status = ReservationStatus.valueOf(i[3]);
				Reservation r = new Reservation(i[0], i[1], LocalDate.parse(i[2]), status);
				reservations.add(r);
				input = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return reservations;
	}

}
