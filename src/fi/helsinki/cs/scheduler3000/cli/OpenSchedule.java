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
	
	void run() {
		openScheduleDialog();
	}

	private void openScheduleDialog() {
		System.out.println("Give name of the file to be opened");
		boolean done = false;
		
		while ( !done ) {
			printPrompt();
            
			String filename = input.nextLine().trim();

			if ( !filename.toLowerCase().endsWith(".dat")){
				filename += ".dat";
			}

			ScheduleReader scheduleReader = new ScheduleReader(new File(filename), FORMAT.DAT );
			this.schedule = scheduleReader.read();

            done = true;
		}
	}
}
