package fi.helsinki.cs.scheduler3000.cli;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import fi.helsinki.cs.scheduler3000.model.Schedule;

public class SaveSchedule extends CliCommand {

	private ObjectOutputStream objectOutput;
	private Schedule schedule;
	
	public SaveSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
	void run() {
		saveScheduleDialog();
	}


	private boolean save(String filename) {
	
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

	private void saveScheduleDialog() {
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
