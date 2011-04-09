package tests;

/**
 * @author Team TA's
 */


import java.util.GregorianCalendar;

import junit.framework.TestCase;
import org.junit.*;

import fi.helsinki.cs.scheduler3000.model.Weekday.Day;

public class WeekdayTest extends TestCase {

	
	@Test
	public void testEnumToString() {
		assertEquals( "Monday", Day.MON.toString() );
		assertEquals( "Tuesday", Day.TUE.toString() );
		assertEquals( "Wednesday", Day.WED.toString() );
		assertEquals( "Thursday", Day.THU.toString() );
		assertEquals( "Friday", Day.FRI.toString() );
		assertEquals( "Saturday", Day.SAT.toString() );
		assertEquals( "Sunday", Day.SUN.toString() );
	}
	
	@Test
	public void testEnumNames() {
		assertEquals( "Monday", Day.MON.getName() );
		assertEquals( "Tuesday", Day.TUE.getName() );
		assertEquals( "Wednesday", Day.WED.getName() );
		assertEquals( "Thursday", Day.THU.getName() );
		assertEquals( "Friday", Day.FRI.getName() );
		assertEquals( "Saturday", Day.SAT.getName() );
		assertEquals( "Sunday", Day.SUN.getName() );
	}
	
	@Test
	public void testEnumValues() {
		assertEquals( GregorianCalendar.MONDAY , Day.MON.getCalendarDay() );
		assertEquals( GregorianCalendar.TUESDAY , Day.TUE.getCalendarDay() );
		assertEquals( GregorianCalendar.WEDNESDAY , Day.WED.getCalendarDay() );
		assertEquals( GregorianCalendar.THURSDAY , Day.THU.getCalendarDay() );
		assertEquals( GregorianCalendar.FRIDAY , Day.FRI.getCalendarDay() );
		assertEquals( GregorianCalendar.SATURDAY , Day.SAT.getCalendarDay() );
		assertEquals( GregorianCalendar.SUNDAY , Day.SUN.getCalendarDay() );
	}

}
