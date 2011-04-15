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

import fi.helsinki.cs.scheduler3000.model.*;
import fi.helsinki.cs.scheduler3000.report.*;
import fi.helsinki.cs.scheduler3000.io.*;

public class Cli extends CliCommand {
	
	private Schedule schedule = null;
	
	private final static String programName = "KaiSei";
	
	public void run(){

		// print application name
		
		Helpers.manifestData( programName );
		
		Character foo;

		do {
			System.out.println();
			printCommands();
			printPrompt();
			foo = sanitize(input.nextLine());
			CliCommand command = null;
			switch (foo) {
			
			case 'p':
				if (schedule == null){ // cannot do this if schedule is not existing
					break;
				}
				command = new NewReportToScreen(schedule);
				break;
				
			case 'a':
				if (schedule == null){ // cannot do this if schedule is not existing 
					break;
				}
				command = new NewEvent(schedule);
				break;
				
			case 's':
				if (schedule == null) { // cannot do this if schedule is not existing
					break;
				}
				command = new SaveSchedule(schedule, ScheduleWriter.FORMAT.DAT );
				break;
				
			case 'c':
				if (schedule == null) { // cannot do this if schedule is not existing
					break;
				}
				command = new SaveSchedule(schedule, ScheduleWriter.FORMAT.CSV );
				break;
				
			case 'f':
				if (schedule == null){ // cannot do this if schedule is not existing
					break;
				}
				command = new NewReportToFile(schedule);
				break;
				
			case 'n':
				this.schedule = new Schedule();
				command = new NewSchedule(schedule);
				break;
				
			case 'o':
				command = new OpenSchedule(schedule);
				break;
				
			case 'q':
				System.exit(0);
				break;
				
			default:
				System.out.println("Don't know what that command is");
				break;
			
			}
			if( command != null ) {
				command.run();
			}
		} while (true);

	}

	private void printCommands() {
		System.out.println("Commands");
		System.out.println("--------");
		System.out.println("[N]ew schedule");
		System.out.println("[O]pen schedule from file");
		if (schedule != null){
			System.out.println("[A]dd event to schedule");
			System.out.println("[S]ave schedule to file");
			System.out.println("[P]rint a report on screen");
			System.out.println("Print a report to [F]ile");
			System.out.println("Export to [C]SV");
		}
		System.out.println("[Q]uit");
	}

}
