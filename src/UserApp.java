
/**
 * 
 * @author Omri Hadad & Vladi Khagay
 * 
 */
import java.io.IOException;
import java.util.TreeSet;

import app.RailApp;
import app.Utils;
import train.MyComparators;
import train.Station;
import train.TrainLine;

public class UserApp {

	public static void main(String[] args) throws ClassNotFoundException, IOException {

		RailApp app = RailApp.getInstance(); // Creation of the app instance

//		
//		The expected arguments are:
//			1. Departure station = args[0]
//			2. Destination station = args[1]
//			3. Departure time (two digits) = args[2]
//			4. Exit minutes (two digits) = args[3]
//			5. Output format = args[4]

		if (args.length != 5) { // If the number of arguments does not match the expected number, run the java
								// application
			app.userStart();

		} else { // Otherwise, run the search on the received arguments
			boolean isHtml = false;

			if (args[4].equalsIgnoreCase("html")) { // Checking the output format

				isHtml = true;
			}
			String departure = args[0];

			if (departure.contains("-")) { // Replacing the character '' '- character ""
				departure = departure.replace('-', ' ');
			}
			String destination = args[1];

			if (destination.contains("-")) { // Replacing the character '' '- character ""
				destination = destination.replace('-', ' ');
			}

			String time = args[2] + ":" + args[3]; // Build the string that represents the time

			if (!Utils.isValidTimeFormat(time)) { // Checking the time format

				System.out.println("Invalid time format");
				System.out.println("The required format is: (HH:MM) 24H");
				System.exit(-1);
			}

			// Creates a sorted data structure to store the lines entered in the system.
			TreeSet<TrainLine> tl = new TreeSet<TrainLine>(new MyComparators.TrainLineComparator());

			// Creates a sorted data structure to store the lines that match the user's
			// search
			TreeSet<TrainLine> userRide = new TreeSet<TrainLine>(new MyComparators.TrainLineComparator());

			// Filling the data structure with data from the file
			tl.addAll(RailApp.readFromFile());

			// Perform the search
			userRide = app.findUserRide(tl, new Station(departure, time), new Station(destination, "23:59"));

			// Print search results
			app.printUserRide(userRide, isHtml);

		}

	}
}
