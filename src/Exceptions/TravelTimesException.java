package Exceptions;

public class TravelTimesException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public TravelTimesException() {
		this ("Arrival Time canot be before departure time!");
	}
	
	public TravelTimesException(String message) {
		super(message);
	}
	
}