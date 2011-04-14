package fi.helsinki.cs.scheduler3000.report;

/**
 * @author Team TA's
 */


import java.util.HashMap;

import fi.helsinki.cs.scheduler3000.model.Event;
import fi.helsinki.cs.scheduler3000.model.Schedule;
import fi.helsinki.cs.scheduler3000.model.Weekday;

public abstract class Report {

	// visible to subclasses
	protected Schedule schedule;
	protected HashMap<String, Object> options;
	
	public Report(Schedule schedule, HashMap<String, Object> options) {
		this.schedule = schedule;
		this.options = options;
	}
	
}
