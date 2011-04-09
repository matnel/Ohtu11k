package fi.helsinki.cs.scheduler3000.report;

/**
 * @author Team TA's
 */


import java.util.HashMap;

import fi.helsinki.cs.scheduler3000.model.Schedule;


public class ReportFactory {

	public static enum ReportType { DAY, WEEK, FULL }
	
	public static Report makeReport(ReportType type, Schedule schedule, HashMap<String, Object> options){
		switch (type) {
		case DAY:
			return new DayReport(schedule, options);
		case WEEK:
			return new WeekReport(schedule, options);
		case FULL:
			return new FullReport(schedule, options);
		default:
			return null;
		}
	}
	
}
