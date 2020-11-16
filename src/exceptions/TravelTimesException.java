/**
 * 
 * @author Omri Hadad & Vladi Khagay
 * 
 */
package exceptions;

public class TravelTimesException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public TravelTimesException() {
		this ("Arrival Time can not be before departure time!");
	}
	
	public TravelTimesException(String message) {
		super(message);
	}
	
}
