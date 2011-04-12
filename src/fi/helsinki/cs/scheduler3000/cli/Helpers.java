package fi.helsinki.cs.scheduler3000.cli;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import fi.helsinki.cs.scheduler3000.model.*;
import fi.helsinki.cs.scheduler3000.model.Weekday.Day;

public class Helpers {
	
	static HashMap<Integer, Weekday.Day> intToDays = new HashMap<Integer, Weekday.Day>();
	static HashMap<Day, Integer> daysToInt = new HashMap<Day, Integer>();
	
	static {
		intToDays.put( new Integer(1) , Weekday.Day.MON );
		intToDays.put( new Integer(2) , Weekday.Day.TUE );
		intToDays.put( new Integer(3) , Weekday.Day.WED );
		intToDays.put( new Integer(4) , Weekday.Day.THU );
		intToDays.put( new Integer(5) , Weekday.Day.FRI );
		intToDays.put( new Integer(6) , Weekday.Day.SAT );
		intToDays.put( new Integer(7) , Weekday.Day.SUN );
		
		// fill the other way HashMap
		for( Entry<Integer, Day> day : intToDays.entrySet() ) {
			daysToInt.put( day.getValue() , day.getKey() );
		}
	}

	static Weekday.Day getDay(String in) throws Exception {
		Weekday.Day day = null;
		
		try {
			int dayInt = Integer.parseInt(in);
			day = intToDays.get(dayInt);
			// validate inut here
		} catch (NumberFormatException e) {	
			throw new Exception("Invalid number format");
		}
		
		if( day == null ) {
			throw new Exception("Invalid number given");
		}
		
		return day;
	}
	
	static void printSelection(Collection<Day> dates) {
		if (dates.size() > 0){
			System.out.print("You have selected: ");
			for (Day d : dates){
				System.out.print( d );
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	static void printDates() {
		System.out.print("Dates are: ");
		for (Entry<Integer, Day> day : intToDays.entrySet()){
			System.out.print(day.getKey());
			System.out.print(" - ");
			System.out.print(day.getValue());
			System.out.print(" ");
		}	
		System.out.println();
	}

	static void printDates(Schedule schedule) {
		System.out.print("Dates are: ");
		for (Day d : schedule.getDays() ){
			System.out.print( daysToInt.get(d) );
			System.out.print(" - ");
			System.out.print(d);
			System.out.print(" ");
		}	
		System.out.println();
	}

}
