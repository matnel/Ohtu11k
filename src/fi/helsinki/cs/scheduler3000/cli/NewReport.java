package fi.helsinki.cs.scheduler3000.cli;

import java.util.*;

import fi.helsinki.cs.scheduler3000.model.Schedule;
import fi.helsinki.cs.scheduler3000.model.Weekday.Day;
import fi.helsinki.cs.scheduler3000.report.Report;
import fi.helsinki.cs.scheduler3000.report.ReportFactory;

public class NewReport extends CliCommand {

	private Schedule schedule;
	protected Report report = null;
	
	NewReport(Schedule schedule) {
		this.schedule = schedule;
	}

	void run() {
		while( this.report == null ) {
			printReportDialog();
		}
	}
	
	
	private static HashMap<String, Object> getOptions(String key, Day value) {
		HashMap<String, Object> ret = new HashMap<String, Object>();
		ret.put(key, value);
		return ret;
	}

	private static HashMap<String, Object> getOptions(String key, ArrayList<Day> value) {
		HashMap<String, Object> ret = new HashMap<String, Object>();
		ret.put(key, value);
		return ret;
	}
	
	private Report printReportDialog() {
		Character command = null;

		while (true) {
			System.out.print("Which type of report do you want to print? Options are: ");
			for (ReportFactory.ReportType type : ReportFactory.ReportType.values()){
				// make types the way every other option is shown to user
				System.out.print("[" + type.toString().charAt(0) + "]" + type.toString().substring(1).toLowerCase()); 
				System.out.print(" ");
			}
			System.out.println("[N]one");
			printPrompt();
			command = sanitize(input.nextLine());
			String in = null;

			switch (command) {

			case 'd':
				this.report = dayReportDialog();
				return this.report;

			case 'f':
				this.report = fullReportDialog();
				return this.report;
				
			case 'w':
				this.report = weekReportDialog();
				return this.report;
				
			case 'n':
				System.out.println("Returning back to main menu");
				return null;
				
			default:
				System.out.println("Cannot parse " + command);
				break;
			}

		}
	}
	
	private Report fullReportDialog() {
		return ReportFactory.makeReport(ReportFactory.ReportType.FULL, schedule, null); // full report doesen't need options
	}
	
	private Report dayReportDialog() {
	
		while( true ) {
            System.out.println("Which day you want to see your schedule for?");
			Helpers.printSelection( this.schedule.getDays() );
			printPrompt();
			
			String in = input.nextLine();

            System.out.println("Which week is the day in?");
            int week = getInt();
            
			try {
				Day day = Helpers.getDay(in , this.schedule.getDays() );

                HashMap<String, Object> options = getOptions("day", day);
                options.put("week", new Integer(week));
                
				return ReportFactory.makeReport(ReportFactory.ReportType.DAY, schedule, options);
				
			} catch (Exception e) {
				System.out.println("Invalid date given");
			}

		}

	}
	
	private Report weekReportDialog() {
		
		ArrayList<Day> days = new ArrayList<Day>();

		while ( true ){
			
			System.out.println("Which days you want to include in this report? You can end with \""+endCommand+"\"");
			System.out.println("One at the time, please");
			
			// print only available dates
			Helpers.printDates(schedule);
			printPrompt();

			String in = input.nextLine();
			
			if ( in.equals(endCommand) ) {
				break;
			}
			
			try {
				Day day = Helpers.getDay(in, this.schedule.getDays() );
				days.add( Helpers.getDay(in) );
			} catch( Exception e) {
				System.out.println("Mistake detected. Try again");
			}
		}

        System.out.println("Which week(s) do you want to include in this report? You can give an integer or a range expression (eg. 3-7)");
        SortedSet<Integer> weekRange = readIntRange();

        HashMap<String, Object> options = getOptions("days", days);
        options.put("weeks", weekRange);

        return ReportFactory.makeReport(ReportFactory.ReportType.WEEK, schedule, options);
	}

}
