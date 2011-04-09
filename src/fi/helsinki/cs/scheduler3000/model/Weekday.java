package fi.helsinki.cs.scheduler3000.model;

/**
 * @author Team TA's
 */


import java.util.HashMap;
import java.util.GregorianCalendar;


public class Weekday {

	public static enum Day {
		MON ("Monday", GregorianCalendar.MONDAY),
		TUE ("Tuesday", GregorianCalendar.TUESDAY),
		WED ("Wednesday", GregorianCalendar.WEDNESDAY),
		THU ("Thursday", GregorianCalendar.THURSDAY),
		FRI ("Friday", GregorianCalendar.FRIDAY),
		SAT ("Saturday", GregorianCalendar.SATURDAY),
		SUN ("Sunday", GregorianCalendar.SUNDAY);
		
		private String name;
		private int calendarValue;
	
		Day(String name, int calendarValue) {
			this.name = name;
			this.calendarValue = calendarValue;
		}
		
		public String getName() {
			return this.name;
		}
		
		public int getCalendarDay() {
			return this.calendarValue;
		}
		
		public String toString() {
			return this.getName();
		}
	
	};	


}
