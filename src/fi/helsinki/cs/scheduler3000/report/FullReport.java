package fi.helsinki.cs.scheduler3000.report;

/**
 * @author Team TA's
 */

import java.util.HashMap;

import fi.helsinki.cs.scheduler3000.model.Event;
import fi.helsinki.cs.scheduler3000.model.Schedule;
import fi.helsinki.cs.scheduler3000.model.Weekday;


public class FullReport extends Report {

	public FullReport(Schedule schedule, HashMap<String, Object> options) {
		super(schedule, options);
	}
	
	// FIXME: should this be elsewhere?
	public String toString(){
		String res = "";
		
		for (Weekday.Day day : schedule.getDays() ){
			res += day + ":\n";
			res += "----\n";
			for (Event event : schedule.getEventsOn(day) ){
				res += event.getTitle();
				res += "\nat " + event.getStartTime() + "-" + event.getEndTime();
				res += "\nin " + event.getLocation();
			}
			res += "\n\n";
		}
		
		return res;
	}

}
