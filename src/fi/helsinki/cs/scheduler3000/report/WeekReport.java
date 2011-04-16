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

public class WeekReport extends Report {

	public WeekReport(Schedule schedule, HashMap<String, Object> options) {
		super(schedule, options);
	}

	@Override
	public String toString() {
		
		if (this.options.containsKey("days")){
			ArrayList<Weekday.Day> days = (ArrayList<Day>)this.options.get("days");			
			String[][] res = new String[days.size() + 1][ Event.VALID_TIMES.length + 1]; // +1 for header row

			// Time slots
			res[0][0] = "";
			
			for (int i = 1, j = 0; j < Event.VALID_TIMES.length ; i++, j++){
				res[0][i] = "" + Event.VALID_TIMES[j];
			}	

			// Day-headers
			int i = 1;
			for (Day day : days){
				res[i][0] = day.getName();
				i++;
			}
			
			// star from first real column
			i = 1;
			for (Day d : days){		
				Collection<Event> events = this.schedule.getEventsOn(d);
                if (events == null) {
                    return null;
                }

				// initially fill everything as empty
				for (int x = 1; x < res[i].length; x++) {
					res[i][x] = "";
				}

				for (Event event : events){
					String entry = ""; // if event is null
						
					if (event.getTitle() != null) { 
					  entry = event.getTitle() + "";
					}
					
					
					// find location in this days rows 
					for( int k = 0; k < Event.VALID_TIMES.length; k++ ) {
						if( event.getStartTime() == Event.VALID_TIMES[k]) {
							res[i][k+1] = entry;
							break;
						}
						
					}
					
				}
				i++;
			}
						
			String response = "";
			
			for (int j = 0; j < res.length; j++){
				for (int k = 0; k < res[0].length; k++){
					response += res[j][k] + "\t";
				}
				response += "\n";
			}
			
			return response;
		}
		return null;
	}
	
}
