package fi.helsinki.cs.scheduler3000;

import fi.helsinki.cs.scheduler3000.cli.Cli;
import java.util.*;

public class Main {

	/**
	 * Runs the application.
	 */
	public static void main(String[] args) {
		Cli cli = new Cli();

        try {
            cli.run();
        } catch (NoSuchElementException e) {
            /* Allow quitting the program with ^D without error */
            System.out.println();
        }
	}

}
