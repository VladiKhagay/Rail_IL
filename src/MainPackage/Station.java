package MainPackage;

import Exceptions.TimeFormatException;

public class Station {
	private String name;
	private Time DepartureTime;

	public Station(String name, String departureTime) throws TimeFormatException {
		this.name = name;
		DepartureTime = new Time(departureTime);
		
	}
	
	public String getName() {
		return name;
	}
	
	public Time getDepartureTime() {
		return DepartureTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DepartureTime == null) ? 0 : DepartureTime.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Station other = (Station) obj;
		if (DepartureTime == null) {
			if (other.DepartureTime != null)
				return false;
		} else if (!DepartureTime.equals(other.DepartureTime))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name + ", " + DepartureTime;
	}
	
	
	
	
}
