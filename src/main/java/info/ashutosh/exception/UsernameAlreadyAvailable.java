package info.ashutosh.exception;

public class UsernameAlreadyAvailable extends Exception {

	private static final long serialVersionUID = 1L;

	public UsernameAlreadyAvailable() {
		super();
	}

	public UsernameAlreadyAvailable(String exception) {
		super(exception);
	}

}
