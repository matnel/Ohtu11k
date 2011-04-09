package fi.helsinki.cs.scheduler3000.cli;

import java.util.ArrayList;

import fi.helsinki.cs.scheduler3000.model.Schedule;
import fi.helsinki.cs.scheduler3000.model.Weekday.Day;

public class NewSchedule extends CliCommand {
	
	NewSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
	void run() {
		newScheduleDialog();
	}
	
	private void newScheduleDialog() {
		String in = null;
		ArrayList<Day> dates = new ArrayList<Day>();

		System.out.println("Enter the period this schedule is for:");
		printPrompt();
		String period = input.nextLine();

		System.out.println("Give dates you want to include in the scedule");
		System.out.println("Stop giving the dates by entering \""+endCommand+"\"");
		System.out.println("One at a time, please");

		do {
			Helpers.printDates();
			Helpers.printSelection(dates);
			printPrompt();
			in = input.nextLine().trim();

			if (in.toLowerCase().equals(endCommand)){
				break;
			}
			else {
				Day day = Helpers.getDay(in.trim());
				if( day != null ) {
					dates.add(day);
				}
			}

		} while (true);

		System.out.print("Creating schedule...");

		this.excecute(period, dates );
		
		System.out.println("ok!");

	}
	
	private void excecute(String period, ArrayList<Day> dates) {
		this.schedule.setPeriod(period);
		this.schedule.setSchedule(dates);
	} 

	
}
