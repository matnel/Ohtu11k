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
	private ScheduleWriter.FORMAT format;
	
	public SaveSchedule(Schedule schedule, ScheduleWriter.FORMAT format) {
		this.schedule = schedule;
		this.format = format;
	}
	
	void run() {
		saveScheduleDialog();
	}

	private void saveScheduleDialog() {
		boolean done = false;
		
		String filename = "";
		while( !done ) {
			filename = getFilename( this.format.toString() );
			ScheduleWriter writer =
				new ScheduleWriter(schedule, new File(filename), this.format );
			done = writer.write();
		}
		
		System.out.println("Schedule saved as \"" + filename + "\"");
	}
	
	private String getFilename(String extension) {
		System.out.println("Give name of the file to open");
		System.out.println("Notice that file will be saved with ." + extension + "-extension, eg. \"myfile\" will be \"myfile." + extension + "\" ");
		printPrompt();
		String filename = input.nextLine().trim();
		if( ! filename.endsWith( extension ) ) {
			filename += "." + extension;
		}
		return filename;
	}
	
}
