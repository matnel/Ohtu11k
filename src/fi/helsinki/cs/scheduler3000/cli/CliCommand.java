package fi.helsinki.cs.scheduler3000.cli;

import java.util.Scanner;

import fi.helsinki.cs.scheduler3000.model.Schedule;

public class CliCommand {

	void run() {
		
	}
	
	protected static void printPrompt() {
		System.out.print("?>");
	}
	
	protected static Character sanitize(String rawInput){
		return new Character(rawInput.toLowerCase().charAt(0));
	}
	
	protected static Scanner input = new Scanner(System.in); 
	protected static final String endCommand = "/q";
	
}