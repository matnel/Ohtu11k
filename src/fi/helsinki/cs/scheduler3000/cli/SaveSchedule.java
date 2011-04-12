package fi.helsinki.cs.scheduler3000.cli;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import fi.helsinki.cs.scheduler3000.io.ScheduleWriter;
import fi.helsinki.cs.scheduler3000.io.ScheduleWriter.FORMAT;
import fi.helsinki.cs.scheduler3000.model.Schedule;

public class SaveSchedule extends CliCommand {

	private Schedule schedule;
	
	public SaveSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
	void run() {
		saveScheduleDialog();
	}

	private void saveScheduleDialog() {
		System.out.println("Give name of the file to open");
		System.out.println("Notice that file will be saved with .dat-extension, eg. \"myfile\" will be \"myfile.dat\" ");
		printPrompt();
		String filename = input.nextLine().trim() + ".dat";
		while (true){
			ScheduleWriter writer =
				new ScheduleWriter(schedule, new File(filename), FORMAT.DAT );
			if ( writer.write() ){
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
