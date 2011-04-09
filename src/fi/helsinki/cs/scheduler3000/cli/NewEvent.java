package fi.helsinki.cs.scheduler3000.cli;

import fi.helsinki.cs.scheduler3000.model.Event;
import fi.helsinki.cs.scheduler3000.model.Schedule;
import fi.helsinki.cs.scheduler3000.model.Weekday.Day;

public class NewEvent extends CliCommand {
	
	private Schedule schedule;
	
	NewEvent(Schedule schedule) {
		this.schedule = schedule;
	}
	
	void run() {
		newEventDialog();
	}
	
	private void newEventDialog() {
		String startTime = null, endTime = null, location = null, title = null, eventDayTemp;
		Event event = null;
		Day eventDay = null;

		do {
			System.out.println("");

			System.out.println("Which day is the event?");
			Helpers.printDates();
			printPrompt();
			eventDayTemp =  input.nextLine();

			System.err.println( eventDayTemp );
			System.err.println( endCommand );
			if (eventDayTemp.equals(endCommand)){
				return;
			}

			System.out.println("What is the start time?");
			printPrompt();
			startTime = input.nextLine();

			System.out.println("What is the end time?");
			printPrompt();
			endTime = input.nextLine();

			System.out.println("What this event should be named as?");
			System.out.println("(just press enter to skip this)");
			printPrompt();
			title = input.nextLine();

			System.out.println("Where this event is held?");
			System.out.println("(just press enter to skip this)");
			printPrompt();
			location = input.nextLine();

			try {
				eventDay = Helpers.getDay(eventDayTemp);
				if ( eventDay != null){
					continue;
				}
				event = new Event(startTime, endTime, title, location);
				break; // success, get out of the do-while

			} catch (IllegalArgumentException e) {

				System.out.println("Sorry, but some mistakes were made:");
				System.out.println(e.getMessage());

			}
		} while (true);

		System.out.print("Adding event to schedule...");

		try {
			schedule.addEvent(eventDay, event);
		} catch (IllegalArgumentException e) {
			System.out.println("Something went wrong:");
			System.out.println(e.getMessage());
			System.out.println("Sorry, but once more");
			newEventDialog();
			return; // this is for when newEventDialog finally succeedes, we don't print out the last ok!'s
		}

		System.out.println("ok!");

	}

}
