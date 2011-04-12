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
		boolean done = false;
		
		while ( !done ) {
			
			printPrompt();
			String filename = input.nextLine().trim();
			
			if ( !filename.endsWith(".dat")){
				filename += ".dat";
			}
			
			ScheduleReader scheduleReader = new ScheduleReader(this.schedule, new File(filename), FORMAT.DAT );
			
			done = scheduleReader.read();
			
			if( !done ) {
				System.out.println("Something gone bad.");
			}
			
		}
		
	}
	
}
