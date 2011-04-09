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
				CliCommand command = new NewReportToScreen(schedule);
				command.run();
				break;
				
			case 'a':
				if (schedule == null){ // cannot do this if schedule is not existing 
					break;
				}
				CliCommand command1 = new NewEvent(schedule);
				command1.run();
				break;
				
			case 's':
				if (schedule == null) { // cannot do this if schedule is not existing
					break;
				}
				CliCommand saveSchedule = new SaveSchedule(schedule);
				saveSchedule.run();
				break;
				
			case 'f':
				if (schedule == null){ // cannot do this if schedule is not existing
					break;
				}
				CliCommand newReportToFile = new NewReportToFile(schedule);
				newReportToFile.run();
				break;
				
			case 'n':
				CliCommand newSchedule = new NewSchedule(schedule);
				newSchedule.run();
				break;
				
			case 'o':
				CliCommand openSchedule = new OpenSchedule(schedule);
				openSchedule.run();
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

}
