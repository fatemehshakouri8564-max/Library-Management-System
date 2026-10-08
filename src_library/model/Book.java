package model;

public class Book {

	private String title;
	private String author;
	private String isbn; // in haman shabok dar farsi ast.
	private int yearOfPublish;
	private String subject;
	private String publisher;
	private int count;
	private String bookCode;

	public Book(String title, String author, String isbn, int yearOfPublish, String subject, String publisher,
			int count, String bookCode) {

		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.yearOfPublish = yearOfPublish;
		this.subject = subject;
		this.publisher = publisher;
		this.count = count;
		this.bookCode = bookCode;

	}

	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public int getYearOfPublish() {
		return yearOfPublish;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public int getCount() {
		return count;
	}
	
	public String getBookCode() {
		return bookCode;
	}
	
	public void reduceCount(int value) {
		this.count = count - value;
	}
	
	public void addCount(int value) {
		this.count = count + value;
	}
	
}
