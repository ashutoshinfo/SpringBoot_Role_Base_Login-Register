package info.ashutosh.exception;

public class EmailAlreadyAvailable extends Exception {

	private static final long serialVersionUID = 1L;

	public EmailAlreadyAvailable() {
		super();
	}

	public EmailAlreadyAvailable(String exception) {
		super(exception);
	}

}
