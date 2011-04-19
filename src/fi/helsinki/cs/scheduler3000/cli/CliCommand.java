package fi.helsinki.cs.scheduler3000.cli;

import java.util.*;
import java.util.regex.*;
import java.io.IOException;

import fi.helsinki.cs.scheduler3000.model.Schedule;

public abstract class CliCommand {
	
	protected Schedule schedule;

    public Schedule getSchedule() {
        return this.schedule;
    }

	abstract void run();
	
	protected static void printPrompt() {
		System.out.print("?>");
	}
	
	protected static Character sanitize(String rawInput){
        if (rawInput.length() == 0) {
            return '\0';
        }
		return new Character(rawInput.toLowerCase().charAt(0));
	}
	
	protected static Scanner input = new Scanner(System.in); 
	protected static final String endCommand = "/q";

    protected int getInt() {
        int ret = 0;
        boolean success = false;

        {
            printPrompt();
            try {
                ret = Integer.parseInt(input.nextLine());
                success = true;
            }
            catch (NumberFormatException e) { }
        } while ( !success );

        return ret;
    }

    private Pattern rangeExpression = Pattern.compile("\\s*(\\d+)\\s*-\\s*(\\d+)\\s*");
    protected SortedSet<Integer> readIntRange() {
        while (true) {
            try {
                TreeSet<Integer> ret = new TreeSet<Integer>();

                printPrompt();
                String in = input.nextLine();

                if (in.indexOf('-') == -1) {
                    ret.add(new Integer(Integer.parseInt(in)));
                    return ret;
                }

                Matcher m = rangeExpression.matcher(in);
                if (!m.matches()) {
                    continue;
                }

                int end = Integer.parseInt(m.group(2));
                for (int start=Integer.parseInt(m.group(1)); start<=end; start++) {
                    ret.add(new Integer(start));
                }
                return ret;
            }
            catch (NumberFormatException e) {
                continue;
            }
        }
    }
}
