package model;

import java.time.*;

public class Loan {

	private String studentNumber;
	private String bookCode;
	private LocalDate loanDate;
	private LocalDate dueDate;
	private boolean extended;
	private int extendDaysRequest;

	public Loan(String studentNumber, String bookCode, LocalDate loanDate, LocalDate dueDate, boolean extended) {

		this.studentNumber = studentNumber;
		this.bookCode = bookCode;
		this.loanDate = loanDate;
		this.dueDate = dueDate;
		this.extended = extended;
		this.extendDaysRequest = 0;

	}
	
	public void setDue(LocalDate newDate) {
		this.dueDate = newDate;
	}

	public String getStudentNumber() {
		return studentNumber;
	}
	
	public String getBookCode() {
		return bookCode;
	}
	
	public LocalDate getDueDate() {
		return dueDate;
	}
	
	public LocalDate getLoanDate() {
		return loanDate;
	}
	
	public void setExtended() {
		this.extended = true;
	}
	
	public boolean getExtended() {
		return extended;
	}
	
	public int getExtendDaysRequest() {
		return extendDaysRequest;
	}
	
	public void setExtendDaysRequest(int value) {
		extendDaysRequest = value;
	}
}
