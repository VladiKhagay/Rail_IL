package train;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalTime;

public class Station implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private LocalTime mTime;

	public Station(String name, String mTime) {
		this.name = name;
		try {
			this.mTime = LocalTime.parse(mTime);
		} catch (IllegalArgumentException | DateTimeException e) {
			this.mTime = LocalTime.of(0, 0);
		}

	}

	public Station(String name, LocalTime mTime) {
		this.name = name;
		try {
			this.mTime = LocalTime.from(mTime);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getName() {
		return name;
	}

	public LocalTime getmTime() {
		return mTime;
	}


	@Override
	public boolean equals(Object obj) {
		Station other = (Station) obj;
		
		return this.name.equalsIgnoreCase(other.getName());
	}

	@Override
	public String toString() {
		return name + ", " + mTime;
	}
	
	

}
