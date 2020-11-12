package train;

import java.io.Serializable;
import java.util.Comparator;

/**
 * This class contains comparators
 */

public abstract class MyComparators {
	
	
	/**
	 * 
	 * @author vladi
	 * This class contains a comparator that compares two "station" classes. 
	 * The comparison is made in relation to the time of each station
	 */
	public static class StationComparator implements Comparator<Station> , Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(Station s1, Station s2) {
			return s1.getmTime().compareTo(s2.getmTime());
		}
	}
	
	/**
	 * 
	 * @author vladi
	 * This class contains a comparator that compares two "train line" classes. 
	 * The comparison is made in relation to the departure time of each line
	 */
	public static class TrainLineComparator implements Comparator<TrainLine> , Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(TrainLine tl1, TrainLine tl2) {
			if (tl1.getDepartureStation().getmTime().isBefore(tl2.getDepartureStation().getmTime())) {
				// if tl1's departure time is before tl2's departure time return -1.
				return -1;
			} else if (tl1.getDepartureStation().getmTime().isAfter(tl2.getDepartureStation().getmTime())) {
				// if tl1's departure time is after tl2's departure time return 1.
				return 1;
			} else {
				// if tl1's departure time is equals to tl2's departure time return 0.
				
				if (tl1.getDepartureStation().equals(tl2.getDepartureStation()) && tl1.getDestinationStation().equals(tl2.getDestinationStation())) {
					return 0;
				}
				return 1;	// If both train lines have the same departure time, but the stations on each line are different, return 1.
			}
		}
		
	}

}
