package Exceptions;

public class StationException extends Exception {

	private static final long serialVersionUID = 1L;

	public StationException() {
		this("Invalid Station information!");
	}

	public StationException(String message) {
		super(message);
	}

}
