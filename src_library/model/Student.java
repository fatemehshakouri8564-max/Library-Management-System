package model;

public class Student extends User {

	private String studentNumber;
	private int debt;

	public Student(String firstName, String lastName, String email, String password, String studentNumber, int debt) {
		super(firstName, lastName, email, password);

		this.studentNumber = studentNumber;
		this.debt = debt;
	}

	public String getStudentNumber() {
		return studentNumber;
	}
	
	public int getDebt() {
		return debt;
	}

	public void addDebt(int value) {
		this.debt = debt + value;
	}
	
}
