package model;

import java.time.*;

public class Reservation {
	
	private String studentNumber;
	private String bookCode;
	private LocalDate requestDate;
	private ReservationStatus status;
	
	public Reservation(String studentNumber, String bookCode, LocalDate requestDate, ReservationStatus status) {
		
		this.bookCode = bookCode;
		this.requestDate = requestDate;
		this.status = status;
		this.studentNumber = studentNumber;
		
	}
	
	public void setStatus(ReservationStatus newStatus) {
		this.status = newStatus;
	}
	
	public String getStudentNumber() {
		return studentNumber;
	}
	
	public String getBookCode() {
		return bookCode;
	}
	
	public LocalDate getRequestDate() {
		return requestDate;
	}
	
	public ReservationStatus getStatus() {
		return status;
	}

}
