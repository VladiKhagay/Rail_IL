/**
 * 
 * @author Omri Hadad & Vladi Khagay
 * 
 */
package app;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * The Utils class provides a number of basic methods for receiving input from
 * the user and checking the integrity of the input
 */
public abstract class Utils {

	public static final DateTimeFormatter GLOBAL_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;

	/* final static variables */
	private static final int CHAR_ZERO_VALUE = 48;
	private static final int CHAR_NINE_VALUE = 57;
	private static final int MAX_HOURS = 23;
	private static final int MIN_HOURS = 0;
	private static final int MAX_MINUTES = 59;
	private static final int MIN_MINUTES = 0;

	/**
	 * @param s
	 * @return boolean Given a String s , this method checks if the string contains
	 *         digits only
	 */
	public static boolean isDigits(String s) {

		for (int i = 0; i < s.length(); i++) {

			if (!(s.charAt(i) >= CHAR_ZERO_VALUE && s.charAt(i) <= CHAR_NINE_VALUE))
				return false; /* The character at location i is not a digit */
		}

		return true; /* all characters are digits */

	}

	/**
	 * @param instruction
	 * @return This method get an "instruction" String, the tells the user which
	 *         input he should enter. Then the method reads the user's input. if the
	 *         input is valid the method returns the user's input in a string
	 */
	@SuppressWarnings("resource")
	public static String readStringFromUSer(String instruction) {
		if (!instruction.isEmpty()) {
			System.out.print("\n" + instruction); /* prints the instruction */
		}

		Scanner scanner = new Scanner(System.in); /* Scanner for reading the input from user */

		String input = scanner.nextLine(); /* read user's input into string variable */

		if (input.isEmpty() || input.isBlank()) {
			System.out.println("Cannot accept empty string");
			return readStringFromUSer(instruction); // the input was empty or blank, call the method again.
		}

		if (isDigits(input)) {
			System.out.println("Type text only");
			return readStringFromUSer(instruction); // the input was digits only, call the method again.
		}
		return input; // the input is valid, returns the input string.
	}

	/**
	 * @param instruction
	 * @return LocalTime
	 *         <p>
	 *         This method get an "instruction" String, the tells the user which
	 *         input he should enter. Then the method reds the user's input. If the
	 *         input is valid, the method returns a LocalTime object that contains
	 *         the user info.
	 */
	public static LocalTime readTimeFromUser(String instruction) {

		if (!instruction.isEmpty()) {
			System.out.print("\n" + instruction); /* prints the instruction */
		}
		LocalTime lt;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine(); /* Scanner for reading the input from user */

		if (input.isEmpty() || input.isBlank()) {
			System.out.println("Cannot accept empty string");
			return readTimeFromUser(instruction); // the input was empty or blank, call the method again.
		}
		if (!isValidTimeFormat(input)) {
			System.out.println("Invalid Time Format");
			return readTimeFromUser(instruction); // the input was in invalid time format.
		}

		try {
			lt = LocalTime.parse(input, GLOBAL_TIME_FORMATTER);

			return lt; // returns the LocalTime object
		} catch (Exception e) {
			System.out.println("Invalid time format");
			return readTimeFromUser(instruction);
		}

	}

	/**
	 * @param input
	 * @return boolean
	 *         <p>
	 *         This method examines whether the user's input is in a valid format or
	 *         not. The desired format is: (HH: MM) 24 hours
	 */
	public static boolean isValidTimeFormat(final String input) {
		String temp = input.trim(); /* Divides the string into 2 by colon */
		String[] digits; /* Contains the split string */
		int hours, minutes;

		if (input.isEmpty() || input.isBlank()) {
			return false; // The input is empty or blank.
		}

		if (input.length() < 4 || input.length() > 5) {
			return false; // The input length is to short or to long.
		}

		if (input.contains(":")) {
			digits = temp.split(":");
		} else {
			return false; // The input does not contain colon
		}

		if (digits.length != 2 || digits[1].length() != 2) {
			return false;
		}

		for (int i = 0; i < digits.length; i++) {
			if (!isDigits(digits[i])) {
				return false; // The input does not contain only digits.
			}
		}

		hours = Integer.parseInt(digits[0]);
		minutes = Integer.parseInt(digits[1]);

		if (hours < MIN_HOURS || hours > MAX_HOURS) {
			return false; // The hours component is not in the correct range
		}

		if (minutes < MIN_MINUTES || minutes > MAX_MINUTES) {
			return false; // The minutes component is not in the correct range
		}

		return true;
	}

}
