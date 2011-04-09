package tests;

/**
 * @author Team TA's
 */

import junit.framework.TestCase;

import org.junit.* ;

import fi.helsinki.cs.scheduler3000.*;
import fi.helsinki.cs.scheduler3000.model.Event;
import fi.helsinki.cs.scheduler3000.model.Weekday.Day;

public class EventTest extends TestCase{
	
	Event event;
	
	@Before
	public void setUp() {
		event = new Event( Day.MON, "title", "testlaboratory", 8, 12);
	}
	
	@After
	public void tearDown() {
		event = null;
	}

	@Test
	public void testGetters() {
		assertEquals(Day.MON, event.getDay() );
		assertEquals( "title", event.getTitle() );
		assertEquals( "testlaboratory", event.getLocation() );
		assertEquals( 8, event.getStartTime() );
		assertEquals( 12, event.getEndTime() );
	}
	
	@Test
	public void testSetters() {
		event.setDay( Day.TUE );
		assertEquals( Day.TUE, event.getDay() );
		event.setTitle("title1");
		assertEquals( "title1", event.getTitle() );
		event.setLocation("test");
		assertEquals( "test", event.getLocation() );
		event.setStartTime(10);
		assertEquals( 10, event.getStartTime() );
		event.setEndTime(14);
		assertEquals( 14, event.getEndTime() );
	}
	 
}
