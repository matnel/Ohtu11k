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

        for (Integer week : Event.getAllWeeks()) {
            for (Weekday.Day day : schedule.getDays() ){
                for (Event event : schedule.getEventsOn(day) ){
                    if (event.getWeek() == week.intValue()) {
                        res += "WEEK " + week.intValue() + ":\n";
                        res += day + ":\n";
                        res += "----\n";
                        res += event.getTitle();
                        res += "\nat " + event.getStartTime() + "-" + event.getEndTime();
                        res += "\nin " + event.getLocation() + "\n";
                        res += "\n\n";
                    }
                }
            }
        }

        return res;
	}

}
