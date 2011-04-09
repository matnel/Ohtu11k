package fi.helsinki.cs.scheduler3000.cli;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

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
	
	// TODO: this should be removed to separate IO-module
	private boolean open(String filename) {
		
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

	private void openScheduleDialog() {
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
	
}
