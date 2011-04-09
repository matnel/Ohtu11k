package fi.helsinki.cs.scheduler3000.cli;

/**
 * @author Team TA's
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import fi.helsinki.cs.scheduler3000.model.Event;
import fi.helsinki.cs.scheduler3000.model.Schedule;
import fi.helsinki.cs.scheduler3000.model.Weekday;
import fi.helsinki.cs.scheduler3000.model.Weekday.Day;
import fi.helsinki.cs.scheduler3000.report.Report;
import fi.helsinki.cs.scheduler3000.report.ReportFactory;
import fi.helsinki.cs.scheduler3000.report.ReportFactory.ReportType;

public class Cli extends CliCommand {
	
	private static Schedule schedule = null;
	private static ObjectOutputStream objectOutput;
	private static ObjectInputStream objectInput;
	
	
	
	public void run(){

		Character foo;

		do {
			System.out.println();
			printCommands();
			printPrompt();
			foo = sanitize(input.nextLine());

			switch (foo) {
			
			case 'p':
				if (schedule == null){ // cannot do this if schedule is not existing
					break;
				}
				printReportDialogToScreenDialog();
				break;
				
			case 'a':
				if (schedule == null){ // cannot do this if schedule is not existing 
					break;
				}
				CliCommand command = new NewEvent(schedule);
				command.run();
				break;
				
			case 's':
				if (schedule == null) { // cannot do this if schedule is not existing
					break;
				}
				saveScheduleDialog();
				break;
				
			case 'f':
				if (schedule == null){ // cannot do this if schedule is not existing
					break;
				}
				printReportToFileDialog();
				break;
				
			case 'n':
				newScheduleDialog();
				break;
				
			case 'o':
				openScheduleDialog();
				break;
				
			case 'q':
				System.exit(0);
				break;

			default:
				System.out.println("Don't know what that command is");
				break;
			
			}
		} while (true);

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

	private static void newScheduleDialog() {
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
		schedule = new Schedule(dates, period);

		System.out.println("ok!");

	}

	private static boolean open(String filename) {
		
		objectInput = null; // nullify in case something is wrong and it's open
		
		FileInputStream fos = null;
		
		try {
			fos = new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			System.out.println("File \"" + filename + "\" couldn't be opened");
			return false;
		}
		
		try {
			objectInput = new ObjectInputStream(fos);
		} catch (IOException e) {
			System.out.println("Cannot read \"" + filename + "\" from FileInputStream");
			return false;
		}
		
		try {
			schedule.setSchedule( (Schedule) objectInput.readObject()); // have to cast the object
			return true;
		} catch (IOException e) {
			System.out.println("Cannot read \"" + filename + "\" from ObjectInputStream");
		} catch (ClassNotFoundException e) {
			System.out.println("Cannot find class for the object when reading \"" + filename + "\"");
		}
		
		return false;
	}

	private static void openScheduleDialog() {
		System.out.println("Give name of the file to be opened");
		printPrompt();
		String filename = input.nextLine().trim();
		while (true) {
			
			if (!filename.endsWith(".dat")){
				filename += ".dat";
			}
			
			if (open(filename)){
				break;
			}
			else {
				System.out.println("Please enter the name of the file again");
				System.out.println("You can exit with " + endCommand);
				
				filename = input.nextLine().trim();
				if (filename.equals(endCommand)){
					return;
				}
				
			}
		}
		
		
	}

	private static void printCommands() {
		System.out.println("Commands");
		System.out.println("--------");
		System.out.println("[N]ew schedule");
		System.out.println("[O]pen schedule from file");
		if (schedule != null){
			System.out.println("[A]dd event to schedule");
			System.out.println("[S]ave schedule to file");
			System.out.println("[P]rint a report on screen");
			System.out.println("Print a report to [F]ile");
		}
		System.out.println("[Q]uit");
	}

	private static Report printReportDialog() {
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

				System.out.println("Which day you want to see your schedule for?");
				Helpers.printDates();
				printPrompt();
				in = input.nextLine();
				Day day = Helpers.getDay(in);
				if (day == null){
					System.out.println("Unvalid date");
					break;
				}
				return ReportFactory.makeReport(ReportFactory.ReportType.DAY, schedule, getOptions("day", day));

			case 'f':
				return ReportFactory.makeReport(ReportFactory.ReportType.FULL, schedule, null); // full report doesen't need options

			case 'w':
				ArrayList<Day> days = new ArrayList<Day>();

				System.out.println("Which days you want to include in this report? You can end with \""+endCommand+"\"");
				System.out.println("One at the time, please");

				while (true){
					// print only available dates
					Helpers.printDates(schedule);
					printPrompt();

					in = input.nextLine();
					if (in.equals(endCommand)) {
						break;
					}
					else {
						// validate date
						Day dayT = Helpers.getDay(in);
						if( dayT == null || schedule.getSchedule().containsKey(dayT)) {
							System.out.println("Unvalid date");
						} else {
							days.add( Helpers.getDay(in) );
						}
					}
				}

				return ReportFactory.makeReport(ReportFactory.ReportType.WEEK, schedule, getOptions("days", days));


			case 'n':
				System.out.println("Returning back to main menu");
				return null;
				
			default:
				System.out.println("Cannot parse " + command);
				break;
			}

		}
	}

	private static void printReportToFileDialog() {
		Report report = printReportDialog();
		if (report != null) {
			PrintWriter out = null;
			String filename = null;
			
			System.out.println("Give full file name and path (if applicable)");
			
			while (true){
				printPrompt();
				try {
					filename = input.nextLine().trim();
					out = new PrintWriter(filename);
					break; // break out of the loop
				} catch (FileNotFoundException e) {
					System.out.println("File " + filename + " was not found");
				}
			}
			
			System.out.print("Writing the file...");
			out.print(report);
			out.close();
			System.out.println("ok!");
		}
	}

	private static void printReportDialogToScreenDialog() {
		Report report = printReportDialog();
		if (report != null){
			System.out.println(printReportDialog());
		}
		
	}

	private static boolean save(String filename) {
	
		// nullify static variable to be sure
		objectOutput = null;
		
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(filename);	
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open \"" + filename + "\", something's wrong with it");
			return false;
		}
		
		try {
			//output is Cli's static variable
			objectOutput = new ObjectOutputStream(fos);
		} catch (IOException e) {
			System.out.println("Cannot write to \"" + filename + "\"");
			return false;
		}
		
		try {
			objectOutput.writeObject(schedule);
			objectOutput.close();
			return true;
		} catch (IOException e) {
			System.out.println("Writing to \"" + filename + "\" failed");
			return false;
		}
	
	}

	private static void saveScheduleDialog() {
		System.out.println("Give name of the file to open");
		System.out.println("Notice that file will be saved with .dat-extension, eg. \"myfile\" will be \"myfile.dat\" ");
		printPrompt();
		String filename = input.nextLine().trim() + ".dat";
		while (true){
			if (save(filename)){
				break;
			}
			else {
				System.out.println("Please enter the name of the file again");
				System.out.println("You can exit with " + endCommand);
				filename = input.nextLine().trim() + ".dat";
				
				if (filename.trim().toLowerCase().equals(endCommand)) {
					return;
				}
				

			}
		}
		System.out.println("Schedule saved as \"" + filename + "\"");
	}	

}
