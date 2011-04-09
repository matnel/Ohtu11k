package fi.helsinki.cs.scheduler3000.model;

/**
 * @author Team TA's
 */


public class Event {

	private String title;
	private String location;

	private int startTime;
	private int endTime;
	
	public Event(int startTime, int endTime){
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public Event(String title, int startTime, int endTime){
		this(startTime, endTime);
		this.setTitle(title);
	}
	
	public Event(String title, String location, int startTime, int endTime){
		this(title, startTime, endTime);
		this.setLocation(location);
	}
	
	public String getLocation() {
		return location;
	} 

	public void setLocation(String location) {
		this.location = location;
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

}
