package fi.helsinki.cs.scheduler3000.cli;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import fi.helsinki.cs.scheduler3000.io.ScheduleReader;
import fi.helsinki.cs.scheduler3000.io.ScheduleReader.FORMAT;
import fi.helsinki.cs.scheduler3000.model.Schedule;

public class OpenSchedule extends CliCommand {
	
	private ObjectInputStream objectInput;
	private Schedule schedule;
	
	
	public OpenSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	void run() {
		openScheduleDialog();
	}
	

	private void openScheduleDialog() {
		System.out.println("Give name of the file to be opened");
		printPrompt();
		String filename = input.nextLine().trim();
		while (true) {
			
			if (!filename.endsWith(".dat")){
				filename += ".dat";
			}
			
			ScheduleReader scheduleReader = 
				new ScheduleReader(this.schedule, new File(filename), FORMAT.DAT );
			
			if ( scheduleReader.read() ){
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
	
}
