package fi.helsinki.cs.scheduler3000.model;

import java.io.Serializable;
import java.util.*;

/**
 * @author Team TA's
 */


public class Event implements Serializable {
	
	private Weekday.Day day;

	private String title;
	private String location;

	private int startTime;
	private int endTime;
    private int week;
	
	public Event(Weekday.Day day, String title, String location, int startTime, int endTime, int week){
		this.setDay(day);
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.setTitle(title);
		this.setLocation(location);
        this.setWeek(week);
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

	public int getWeek() {
        return this.week;
    }

    public void setWeek(int newWeek) {
        if ( isValidWeek(newWeek) ) {
            this.week = newWeek;
        } else {
            throw new IllegalArgumentException("Invalid week number");
        }
    }

    // separate validation?
	public void setStartTime(int startTime) {
		if( isValidStartTime(startTime) ) {
			this.startTime = startTime;
		} else {
			throw new IllegalArgumentException("Invalid end time");
		}
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		if( isValidEndTime(startTime, endTime) ) {
			this.endTime = endTime;
		} else {
			throw new IllegalArgumentException("Invalid end time");
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	// valid times saved here. should be fixed?
	public static final int[] VALID_TIMES = {8,10,12,14,16,18};
	
	public static boolean isValidStartTime(int time) {
		return Arrays.binarySearch( VALID_TIMES , time ) >= 0;
	}
	
	public static boolean isValidEndTime(int startTime, int endTime ) {
		return endTime > startTime;
	}

    private static final int minWeek = 0;
    private static final int maxWeek = 53;

    public static SortedSet<Integer> getAllWeeks() {
        SortedSet<Integer> all = new TreeSet<Integer>();
        for (int i = minWeek; i<=maxWeek; i++) {
            all.add(new Integer(i));
        }
        return all;
    }

	public static boolean isValidWeek(int week) {
        return week >= minWeek && week <= maxWeek;
    }
}
