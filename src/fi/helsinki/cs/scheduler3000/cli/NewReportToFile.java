package fi.helsinki.cs.scheduler3000.cli;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import fi.helsinki.cs.scheduler3000.model.Schedule;
import fi.helsinki.cs.scheduler3000.report.Report;

public class NewReportToFile extends NewReport {

	NewReportToFile(Schedule schedule) {
		super(schedule);
	}
	
	void run() {
		super.run();
		printPrompt();
	}
	
	private void printReportToFileDialog() {
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

}
