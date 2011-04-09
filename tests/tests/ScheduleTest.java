package tests;

/**
 * @author Team TA's
 */

import java.util.ArrayList;
import java.util.HashMap;

import junit.framework.TestCase;
import org.junit.*;

import fi.helsinki.cs.scheduler3000.*;
import fi.helsinki.cs.scheduler3000.model.Event;
import fi.helsinki.cs.scheduler3000.model.Schedule;
import fi.helsinki.cs.scheduler3000.model.Weekday.Day;

public class ScheduleTest extends TestCase {

	Schedule schedule;
	ArrayList<Day> week;
	ArrayList<Event> events;
	HashMap<Day, ArrayList<Event>> map;
	
	@Before
	public void setUp() throws Exception {
		week = new ArrayList<Day>();
		week.add(Day.MON);
		week.add(Day.TUE);
		week.add(Day.WED);
		week.add(Day.THU);
		week.add(Day.FRI);
		
		events = new ArrayList<Event>();
		events.add(new Event( Day.MON, "first", "firstLocation", 8, 10));
		events.add(new Event( Day.TUE, "second", "secondLocation", 18, 20));
		events.add(new Event( Day.WED, "third", "thirdLocation", 12, 14));
		events.add(new Event( Day.THU, "fourth", "fourthLocation", 16, 18));
		
	}

	@After
	public void tearDown() throws Exception {
		
		map = null;
		events = null;
		week = null;		
		schedule = null;

	}
	

	@Test
	public void testAllConstructors(){
		schedule = new Schedule(week);
		
		assertNotNull(schedule);
		
		assertEquals(week.size(), schedule.getSchedule().size());
		
		for (Day d : week){
			assertNotNull(schedule.getSchedule().get(d));
		}
		schedule = null;
		
		assertNull(schedule);
		
		String testperiod = "testperiod";
		schedule = new Schedule(week, testperiod);
		
		assertNotNull(schedule);
		
		for (Day d : week){
			assertNotNull(schedule.getSchedule().get(d));
		}
		
		assertNotNull(schedule.getPeriod());
		assertEquals(testperiod, schedule.getPeriod());
		schedule = null;
	}
	
	@Test
	public void testAllSetSchedules(){
		schedule = new Schedule(week, "first period");

		schedule.setSchedule(map);
		assertEquals(map, schedule.getSchedule());
		
		Schedule anotherSchedule = new Schedule(week);
		anotherSchedule.setSchedule(schedule);
		
	}
	
	@Test
	public void testSetPeriod(){
		String period = "first period";
		schedule = new Schedule(week, period);
	
		assertEquals(period, schedule.getPeriod());
		
		period = "totally another period";
		schedule.setPeriod(period);
		assertEquals(period, schedule.getPeriod());
	}
	
	@Test
	public void testAddEvent(){
		schedule = new Schedule(week, "period");
		Event e = new Event( Day.FRI, "testAddEvent", "testAddEventLocation", 16, 20);
		schedule.addEvent(e);
		
		assertEquals(1, schedule.getSchedule().get(Day.FRI).size());
		assertEquals(e, schedule.getSchedule().get(Day.FRI).get(0));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddEventWhenWrongDay(){
		schedule = new Schedule(week, "period");
		
		Event invalid = new Event( Day.SUN, "testAddEvent", "testAddEventLocation", 16, 20 );
		schedule.addEvent(invalid);
		
	}
	
}
