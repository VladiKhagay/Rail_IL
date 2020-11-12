import java.io.IOException;
import java.util.TreeSet;

import app.RailApp;
import train.MyComparators;
import train.Station;
import train.TrainLine;

public class UserApp {
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		RailApp app = RailApp.getInstance();
		
		if (args.length == 0) {
			app.start();
		} else {
			TreeSet<TrainLine> tl = new TreeSet<TrainLine>(new MyComparators.TrainLineComparator());
			TreeSet<TrainLine> userRide = new TreeSet<TrainLine>(new MyComparators.TrainLineComparator());
			
			tl.addAll(RailApp.readFromFile());
			userRide = app.findUserRide(tl, new Station(args[0], args[2]), new Station(args[1], "23:59"));
			app.printUserRide(userRide);			
			
			
			
		}
		
		
	}
}
