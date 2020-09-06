/**
 * 
 * @author Omri Hadad & Vladi Khagay
 * 
 */
package MainPackage;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.TreeSet;

import Exceptions.StationException;
import Exceptions.TimeFormatException;
import Exceptions.TravelTimesException;

public class Main {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws TimeFormatException, StationException, TravelTimesException {

		TreeSet<Travel> ts = new TreeSet<Travel>(new Comparator<Travel>() {

			@Override
			public int compare(Travel t1, Travel t2) {

				return t1.getDepartureTime().compareTo(t2.getDepartureTime());
			}

		});
		boolean flag = true;
		int input;
		while (flag) {
			System.out.println("Hello! please choose an option: " + "\n1: Insert travel details."
					+ "\n2: Print all details sorted by hour." + "\n9: Exit.");

			try {
				input = scanner.nextInt();

				switch (input) {

				// option 1: gets the travel details from the user and stores the data in a tree
				// set
				case 1:
					scanner.nextLine(); // clean the buffer

					System.out.println("Please enter departure station: ");
					String departureStation = scanner.nextLine();

					System.out.println("Please enter departure time: (HH:MM) 24 HOURS");
					String departureTime = scanner.nextLine();

					System.out.println("Please enter destination station: ");
					String destinationStation = scanner.nextLine();

					System.out.println("Please enter arrival time: (HH:MM) 24 HOURS");
					String arrivalTime = scanner.nextLine();

					Travel temp = new Travel(departureStation, destinationStation, departureTime, arrivalTime);
					addIntermediateStations(temp);

					ts.add(temp);

					break;

				// option 2: prints all the travels details in sorted order, sorting by
				// departure time of the departure station
				case 2:

					if (ts.isEmpty()) {
						System.out.println("There is no inforamtion to print.");
					} else {
						for (Travel t : ts) {
							System.out.println(t);
						}
					}
					System.out.println("\n");
					break;

				// option 3: gets the travel details, departure station and time and the
				// destination,
				// and prints 3 travels next trains, starting from the departure time
				case 3:


					System.out.println("Please enter departure station: ");
					departureStation = scanner.nextLine();

					System.out.println("Please enter departure time: (HH:MM) 24 HOURS");
					departureTime = scanner.nextLine();
					System.out.println("Please enter destination station: ");
					destinationStation = scanner.nextLine();
					
					Travel userTravel = new Travel(departureStation, destinationStation, departureTime, null);
					
					Travel travels[] = searchTravel(ts, userTravel);
					
					for (Travel t : travels) {
						if (t != null) {
							System.out.println(t);
						}
					}

					break;

				// option 9: stops the program
				case 9:
					flag = false;
					break;

				default:
					break;
				}
			} catch (StationException e) {
				System.out.println(e.getMessage() + " Please try again\n");
			} catch (TimeFormatException e) {
				System.out.println(e.getMessage() + " Please try again\n");
			} catch (TravelTimesException e) {
				System.out.println(e.getMessage() + " Please try again\n");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	private static void addIntermediateStations(Travel t) throws TimeFormatException {
		String temp, departureTime;
		boolean flag = true;

		while (flag) {
			System.out.println("Please enter intermediate station: (if done enter 0)");
			temp = scanner.nextLine();

			if (temp.equals("0")) {
				flag = false;
				break;
			}
			System.out.println("Please enter departure time: (HH:MM) 24 HOURS");
			departureTime = scanner.nextLine();

			t.addIntermediateStations(temp, departureTime);

		}
	}

	private static Travel[] searchTravel(TreeSet<Travel> ts, Travel travel)
			throws TimeFormatException, StationException, TravelTimesException {
		int counter = 0;
		Travel temp[] = new Travel[3];
		Iterator<Travel> it = ts.iterator();
		LinkedHashSet<Station> mids = new LinkedHashSet<Station>();

		while (it.hasNext() && counter <= 3) {
			Travel t = it.next();

			// check if departure stations are the same
			if (t.getDepartureStation().equalsIgnoreCase(travel.getDepartureStation())) {

				// check if the time range is suitable
				if (travel.getDepartureTime().compareTo(t.getDepartureTime()) <= 0) {

					// check if the destination is the same
					if (travel.getDestinationStation().equalsIgnoreCase(t.getDestinationStation())) {
						temp[counter++] = t;

					} else { // check if the destination is one of the intermediate stations

						mids.addAll(t.getIntermediateStations());
						for (Station s : mids) {
							if (s.getName().equalsIgnoreCase(travel.getDestinationStation())) {
								temp[counter++] = t;
							}
						}
					}
				}
				// check if the departure station is one of the intermediate stations
			} else if (t.isMidStation(travel.getDepartureStation())) {
				// check if the destination is the same
				if (t.getDestinationStation().equalsIgnoreCase(travel.getDestinationStation())) {
					mids.addAll(t.getIntermediateStations());
					for (Station s : mids) {
						if (travel.getDepartureTime().compareTo(s.getDepartureTime()) <= 0) {
							temp[counter++] = new Travel(s.getName(), travel.getDestinationStation(),
									s.getDepartureTime(), t.getArrivalTime());
						}
					}
				}

			} else { // travel not found
				break;
			}

		}

		return temp;
	}

}
