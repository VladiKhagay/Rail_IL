package app;

import train.MyComparators;
import train.Station;
import train.TrainLine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * This class contains all the functional methods of the application 1. Enter
 * line information. 2. Print all the lines. 3. User travel planning
 */

public class RailApp {

	/* Strings containing instructions */
	private static final String MAIN_TITLE = "welcome to Israel Rail Ways!";
	private static final String EXIT = "Exit.";
	private static final String MAIN_OP_1 = "Enter new train line.";
	private static final String MAIN_OP_2 = "Print all train lines.";
	private static final String MAIN_OP_3 = "Plan your journey.";
	private static final String MAIN_OP_4 = "Save data to file.";
	private static final String MAIN_OP_5 = "Load data from file.";
	private static final String ENTER_DEPARTURE_STATION = "Please enter departure station : ";
	private static final String ENTER_MID_STATION = "Enter mid station : *('done' to FINISH)*";
	private static final String ENTER_DESTINATION_STATION = "Please enter destination station : ";
	private static final String TIME = "Please enter arrival time : *(HH:MM 24H FORMAT)*";
	private static final String RIDE_TIME = "Please enter departure time : *(HH:MM 24H FORMAT)*";

	private static RailApp instance = null; /* Instance of this class */
	private TreeSet<TrainLine> mLines; /* Contains all the information entered by the system operator */
	private TreeSet<TrainLine> userRide; /* Saves the relevant rides of the user in each search */

	/* Private constructor */
	private RailApp() {

	}

	/* Returns an instance of this class */
	/* Only a single instance of this class can be created */
	public static RailApp getInstance() {
		if (instance == null) {
			instance = new RailApp();
		}

		return instance;
	}

	/**
	 * The method contains 4 "cases" Each case activates a different function of the
	 * application
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void start() throws FileNotFoundException, IOException {

		System.out.println(MAIN_TITLE); // Prints The main title of the application.
		boolean done = false;
		while (!done) {
			switch (menu()) {
			case 1:
				addNewRoute();
				break;
			case 2:
				printAllrouts();
				break;
			case 3:
				findUserRide();
				printUserRide();
				break;
			case 4:
				try {
					Utils.saveToFile(mLines);
				} catch (Exception e) {
					System.out.println("Failed to save data to file");
					e.printStackTrace();
				}
				break;
			case 5:
				try {
					mLines = Utils.readFromFile();
				} catch (Exception e) {
					System.out.println("Failed to read data from file");
					e.printStackTrace();
				}

				break;
			case 9:
				done = true;
				

				try {
					System.in.close();
				} catch (IOException e) {
					// do nothing
					System.out.println("scanner close failed");
				}
				break;
			}

		}

	}

	/**
	 * This method shows the user the different options available to him and the
	 * number of each option. It then reads the input entered by the user and if its
	 * valid, returns it as an integer.
	 */
	@SuppressWarnings("resource")
	private static int menu() {

		System.out.println("1 : " + MAIN_OP_1 + "\n" + "2 : " + MAIN_OP_2 + "\n" + "3 : " + MAIN_OP_3 + "\n" + "4 : "
				+ MAIN_OP_4 + "\n" + "5 : " + MAIN_OP_5 + "\n" + "9 : " + EXIT);

		System.out.print("your choice: ");
		Scanner scanner = new Scanner(System.in);

		String userInput = scanner.nextLine();
		
		if (userInput.isEmpty() || userInput.isBlank()) { /* Check if the input is empty or contains only spaces */
			System.out.println("Must type something. ");
			return menu();
		}

		if (!Utils.isDigits(userInput)) { /* Check if the input contains only digits. */
			System.out.println("must type an integer");
			return menu();

		}

		int option = 0;

		try {
			option = Integer.parseInt(userInput);
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}

		return option;

	}

	/**
	 * Method for the system operator, for adding a new train line.
	 */
	private void addNewRoute() {
		TreeSet<Station> stations = new TreeSet<>(new MyComparators.StationComparator());
		String midChoice;
		stations.add(new Station(Utils.readStringFromUSer(ENTER_DEPARTURE_STATION), Utils.readTimeFromUser(TIME)));

		while (!(midChoice = Utils.readStringFromUSer(ENTER_MID_STATION)).equalsIgnoreCase("done")) {
			stations.add(new Station(midChoice, Utils.readStringFromUSer(TIME)));
			midChoice = null;
		}
		stations.add(new Station(Utils.readStringFromUSer(ENTER_DESTINATION_STATION), Utils.readTimeFromUser(TIME)));
		if (this.mLines == null) {
			this.mLines = new TreeSet<TrainLine>(new MyComparators.TrainLineComparator());
		}

		this.mLines.add(new TrainLine(stations));

	}

	/**
	 * Method for printing all lines entered by the system operator.
	 */
	private void printAllrouts() {
		int i = 1;
		if (mLines != null && !mLines.isEmpty()) {
			Iterator<TrainLine> it = mLines.iterator();
			System.out.println("Train lines list: ");
			while (it.hasNext()) {
				System.out.println(i++ + "." + it.next() + "\n");
			}
			System.out.println("End of train lines list\n");
		} else {
			System.out.println("No lines registered in system\n");
		}

	}

	/**
	 * Method for performing a user search from the information entered in the
	 * system
	 */
	private void findUserRide() {
		if (mLines == null || mLines.isEmpty()) {
			System.out.println("No lines registered in system");
			return;
		}
		Station from, to;

		from = new Station(Utils.readStringFromUSer(ENTER_DEPARTURE_STATION), Utils.readTimeFromUser(RIDE_TIME));
		to = new Station(Utils.readStringFromUSer(ENTER_DESTINATION_STATION), "23:59");

		for (TrainLine tl : mLines) {
			TrainLine temp = tl.getSubLine(from, to);
			if (temp != null && !from.getmTime().isAfter(temp.getDepartureTime())) {

				if (userRide == null) {
					userRide = new TreeSet<TrainLine>(new MyComparators.TrainLineComparator());
				}
				this.userRide.add(temp);
			}
		}

	}

	/**
	 * Method for printing the user's search result
	 */
	private void printUserRide() {
		int counter = 0;
		if (userRide != null && !userRide.isEmpty()) {
			for (TrainLine tl : userRide) {

				if (counter >= 3) {
					break;
				}
				System.out.println(tl);
				counter++;
			}

			userRide.clear();
		} else {
			System.out.println("\nNo applicable routes found.\n");
		}

	}

}
