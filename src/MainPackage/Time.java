package MainPackage;

import Exceptions.TimeFormatException;

public class Time implements Comparable<Time> {

	private static final int MIN_MINUTES = 0;
	private static final int MAX_MINUTES = 59;
	private static final int MIN_HOURS = 0;
	private static final int MAX_HOURS = 23;
	private int hours;
	private int minutes;

	public Time(int hours, int minutes) throws TimeFormatException {
		setHours(hours);
		setMinutes(minutes);
	}

	public Time(String time) throws TimeFormatException {
		int hours = -1, minutes = -1;
		
		if (!(time.equals("\n")) && !time.equals("")){
			String[] str = time.split(":");
			hours = Integer.parseInt(str[0]);
			minutes = Integer.parseInt(str[1]);
		}
		setHours(hours);
		setMinutes(minutes);
	}

	private void setHours(int hours) throws TimeFormatException {
		if (hours < MIN_HOURS || hours > MAX_HOURS) {
			throw new TimeFormatException();
		}
		this.hours = hours;

	}

	private void setMinutes(int minutes) throws TimeFormatException {
		if (minutes < MIN_MINUTES || minutes > MAX_MINUTES) {
			throw new TimeFormatException();
		}
		this.minutes = minutes;

	}

	public int getHours() {
		return hours;
	}

	public int getMinutes() {
		return minutes;
	}

	@Override
	public String toString() {
		if (hours < 10 && minutes < 10) {
			return "0" + hours + ":" + "0" + minutes;
		} else if (hours < 10 && minutes >= 10) {
			return "0" + hours + ":" + minutes;
		} else if (hours >= 10 && minutes < 10) {
			return hours + ":" + "0" + minutes;
		} else {
			return hours + ":" + minutes;
		}
	}

	/**
	 * if current time > other time returns 1 
	 * if current time < other time returns -1
	 * if current time = other time returns 0
	 */
	@Override
	public int compareTo(Time t) {

		if (hours > t.getHours()) {
			return 1;
		} else if (hours < t.getHours()) {
			return -1;
		} else if (hours == t.getHours()) {
			if (minutes > t.getMinutes()) {
				return 1;
			} else if (minutes < t.getMinutes()) {
				return -1;
			}
		}
		return 0;
	}

}
