package MainPackage;

import Exceptions.StationException;
import Exceptions.TimeFormatException;
import Exceptions.TravelTimesException;

public class Travel {
	private String DepartureStation;
	private String DestinationStation;
	private Time DepartureTime;
	private Time ArrivalTime;

	public Travel(String departureStation, String destinationStation, Time departureTime, Time arrivalTime)
			throws StationException, TravelTimesException {
		setDepartureStation(departureStation);
		setDestinationStation(destinationStation);
		if (departureTime.compareTo(arrivalTime) > -1) {
			throw new TravelTimesException();
		}
		setDepartureTime(departureTime);
		setArrivalTime(arrivalTime);
	}
	
	public Travel(String departureStation, String destinationStation, String departureTime, String arrivalTime)
			throws StationException, TravelTimesException, TimeFormatException {
		setDepartureStation(departureStation);
		setDestinationStation(destinationStation);
		DepartureTime = new Time(departureTime);
		ArrivalTime = new Time(arrivalTime);
		if (departureTime.compareTo(arrivalTime) > -1) {
			throw new TravelTimesException();
		}
		
	}

	private void setDepartureStation(String departureStation) throws StationException {
		if (!departureStation.equals("\n") && !departureStation.equals("")) {
			throw new StationException();
		}
		DepartureStation = departureStation;
	}

	public void setDestinationStation(String destinationStation) throws StationException {
		if (!destinationStation.equals("\n") && !destinationStation.equals("")) {
			throw new StationException();
		}
		DestinationStation = destinationStation;
	}

	private void setArrivalTime(Time arrivalTime) {
		ArrivalTime = arrivalTime;
	}

	private void setDepartureTime(Time departureTime) {
		DepartureTime = departureTime;
	}

	public Time getDepartureTime() {
		return DepartureTime;
	}

	@Override
	public String toString() {
		return DepartureStation + ", " + DepartureTime + ", "
				+ DestinationStation + ", " + ArrivalTime;
	}
}
