package MainPackage;

import java.util.Iterator;
import java.util.LinkedHashSet;

import Exceptions.StationException;
import Exceptions.TimeFormatException;
import Exceptions.TravelTimesException;

public class Travel {
	private String DepartureStation;
	private String DestinationStation;
	private LinkedHashSet<Station> IntermediateStations;
	private Time DepartureTime;
	private Time ArrivalTime;

	public Travel(String departureStation, String destinationStation, String departureTime, String arrivalTime)
			throws StationException, TravelTimesException, TimeFormatException {
		setDepartureStation(departureStation);
		setDestinationStation(destinationStation);
		IntermediateStations = new LinkedHashSet<Station>();

		DepartureTime = new Time(departureTime);
		ArrivalTime = new Time(arrivalTime);
		if (departureTime.compareTo(arrivalTime) > -1) {
			throw new TravelTimesException();
		}

	}

	public Travel(String departureStation, String destinationStation, Time departureTime, Time arrivalTime)
			throws TimeFormatException, StationException, TravelTimesException {
		setDepartureStation(departureStation);
		setDestinationStation(destinationStation);
		IntermediateStations = new LinkedHashSet<Station>();

		DepartureTime = new Time(departureTime.toString());
		ArrivalTime = new Time(arrivalTime.toString());
		if (departureTime.compareTo(arrivalTime) > -1) {
			throw new TravelTimesException();
		}

	}

	private void setDepartureStation(String departureStation) throws StationException {
		if (departureStation.equals("\n") && departureStation.equals("")) {
			throw new StationException();
		}
		DepartureStation = departureStation;
	}

	public void setDestinationStation(String destinationStation) throws StationException {
		if (destinationStation.equals("\n") && destinationStation.equals("")) {
			throw new StationException();
		}
		DestinationStation = destinationStation;
	}

	public void addIntermediateStations(String name, String departureTime) throws TimeFormatException {
		IntermediateStations.add(new Station(name, departureTime));
	}

	public LinkedHashSet<Station> getIntermediateStations() {
		LinkedHashSet<Station> temp = new LinkedHashSet<Station>();

		temp.addAll(IntermediateStations);

		return temp;
	}

	public boolean isMidStation(String s) {
		Iterator<Station> it = IntermediateStations.iterator();
		while (it.hasNext()) {
			if (it.next().getName().equalsIgnoreCase(s)) {
				return true;
			}
		}

		return false;
	}

	public Time getArrivalTime() {
		return ArrivalTime;
	}

	public String getDepartureStation() {
		return DepartureStation;
	}

	public String getDestinationStation() {
		return DestinationStation;
	}

	public Time getDepartureTime() {
		return DepartureTime;
	}

	@Override
	public String toString() {
		return DepartureStation + ", " + DepartureTime + ", " + IntermediateStations.toString() + " "
				+ DestinationStation + ", " + ArrivalTime;
	}
}
