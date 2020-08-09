/**
 * 
 * @author Omri Hadad & Vladi Khagay
 * 
 */
package MainPackage;

import java.util.Comparator;
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
					ts.add(new Travel("Tel Aviv HaHagan", "Beer Sheva", "16:12", "18:15"));
					ts.add(new Travel("Tel Aviv HaHagana", "Jerusalem", "15:50", "16:50"));
					ts.add(new Travel(departureStation, destinationStation, departureTime, arrivalTime));

					break;
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

}
