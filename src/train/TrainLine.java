package train;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.TreeSet;


public class TrainLine implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TreeSet<Station> stations;

	public TrainLine(TreeSet<Station> stations) {
		try {
			this.stations = stations;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addStation(Station station) {
		if (this.stations == null) {
			this.stations = new TreeSet<Station>(new MyComparators.StationComparator());
		}
		this.stations.add(station);
	}

	public TreeSet<Station> getStations() {
		TreeSet<Station> ts = new TreeSet<Station>();
		if (ts.addAll(this.stations)) {
			return ts;
		}
		return null;
	}

	public Station getDepartureStation() {
		return this.stations.first();
	}

	public Station getDestinationStation() {
		return this.stations.last();
	}

	public LocalTime getDepartureTime() {
		return this.stations.first().getmTime();
	}

	public LocalTime getArrivalTime() {
		return this.stations.last().getmTime();
	}

	@Override
	public String toString() {
		String str = stations.toString();

		return str;
	}

	public TrainLine getSubLine(Station from, Station to) {
		TrainLine tl = null;

		int indexFrom = 0, indexTo = 0;

		boolean fromFlag = false, toFlag = false;

		int index = 0;
		Iterator<Station> it = this.stations.iterator();
		while (it.hasNext()) {

			Station tmp = it.next();
			if (tmp.equals(from)) {
				fromFlag = true;
				indexFrom = index;
			}

			if (tmp.equals(to)) {
				toFlag = true;
				indexTo = index;
			}
			index++;

		}

		if (fromFlag && toFlag && indexFrom < indexTo) {

			try {
				Object[] ts = new Station[stations.size()];
				Station temp;
				ts = stations.toArray();
				tl = new TrainLine(null);

				for (int i = indexFrom; i <= indexTo; i++) {
					temp = (Station) ts[i];
					tl.addStation(temp);

				}

				return tl;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;

	}
}
