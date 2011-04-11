package fi.helsinki.cs.scheduler3000.report;

/**
 * @author Team TA's
 */


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import fi.helsinki.cs.scheduler3000.model.Event;
import fi.helsinki.cs.scheduler3000.model.Schedule;
import fi.helsinki.cs.scheduler3000.model.Weekday;
import fi.helsinki.cs.scheduler3000.model.Weekday.Day;

public class DayReport extends Report {

	public DayReport(Schedule schedule, HashMap<String, Object> options) {
		super(schedule, options);
	}
	
	
	@Override
	public String toString() {
		if (this.options.containsKey("day")){
			String res = "";
			Weekday.Day weekday = (Day)this.options.get("day");
			Collection<Event> events = this.schedule.getEventsOn(weekday);
			
			if (events == null) {
				return null;
			}
			
			res += weekday + ":\n------\n";
			
			for (Event e : events){
				if(e.getTitle().equals("")){
					res += "<no title for this event>";
				}
				else{
					res += e.getTitle();	
				}
				
				//event must always have start time and end time
				res += "\nat " + e.getStartTime() + " to " + e.getEndTime(); 
				
				if (e.getLocation().equals("")){
					res += "\n<no location specified>";
				}
				else {
					res += "\nin " + e.getLocation();
				}
				res += "\n\n";
			}
			return res;
		}

		return null;
	}
	
}
