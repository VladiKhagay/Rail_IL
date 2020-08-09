package Exceptions;

public class TimeFormatException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public TimeFormatException() {
		this("Invalid Time Format!");
	}

	public TimeFormatException(String message) {
		super(message);
	}
	

}
