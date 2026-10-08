package model;

public class Librarian extends User {

	private String personnelId;

	public Librarian(String firstName, String lastName, String email, String password, String personnelId) {
		super(firstName, lastName, email, password);

		this.personnelId = personnelId;
	}

	public String getPersonnelId() {
		return personnelId;
	}

}
