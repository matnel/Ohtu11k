package fi.helsinki.cs.scheduler3000.model;

/**
 * @author Team TA's
 */


public class Event {
	
	private Weekday.Day day;

	private String title;
	private String location;

	private int startTime;
	private int endTime;
	
	public Event(Weekday.Day day, String title, String location, int startTime, int endTime){
		this.setDay(day);
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.setTitle(title);
		this.setLocation(location);
	}
	
	public String getLocation() {
		return location;
	} 

	public void setLocation(String location) {
		this.location = location;
	}
	
	public Weekday.Day getDay() {
		return day;
	}
	
	public void setDay(Weekday.Day day) {
		this.day = day;
	}

	public int getStartTime() {
		return startTime;
	}

	// separate validation?
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	// valid times saved here. should be fixed?
	public static final int[] VALID_TIMES = {8,10,12,14,16,18};

}
