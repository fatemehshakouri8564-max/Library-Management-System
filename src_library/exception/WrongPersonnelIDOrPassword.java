package exception;

public class WrongPersonnelIDOrPassword extends Exception {
	public WrongPersonnelIDOrPassword(String message) {
        super(message);
    }
}