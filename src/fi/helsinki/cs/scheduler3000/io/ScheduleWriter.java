package fi.helsinki.cs.scheduler3000.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

import com.csvreader.CsvWriter;

import fi.helsinki.cs.scheduler3000.model.*;
import fi.helsinki.cs.scheduler3000.model.Weekday.Day;

public class ScheduleWriter {

	private Schedule schedule;
	private File file;
	private FORMAT format;

	public enum FORMAT {
		DAT, CSV
	};

	public ScheduleWriter(Schedule schedule, File to, FORMAT format) {
		this.schedule = schedule;
		this.file = to;
		this.format = format;
	}

	public boolean write() {

		if (format == FORMAT.DAT) {

			FileOutputStream fos;
			ObjectOutputStream objectOutput;

			try {
				fos = new FileOutputStream(this.file);
				objectOutput = new ObjectOutputStream(fos);
				objectOutput.writeObject(schedule);
				objectOutput.close();
				return true;
			} catch (Exception e) {

			}
		}
		if (format == FORMAT.CSV) {
			
			try {
				FileWriter fos = new FileWriter(this.file);
			
				CsvWriter writer = new CsvWriter(fos, ',' );
				ArrayList<Event> events = new ArrayList<Event>();
				// Collect all events
				for(Day day : schedule.getDays() ) {
					Collection<Event> e = schedule.getEventsOn(day);
					events.addAll(e);
				}
				for( Event e : events) {
					String[] headers = new String[5];
					headers[0] = e.getDay().getName();
					headers[1] = e.getTitle();
					headers[2] = e.getLocation();
					headers[3] = "" + e.getStartTime();
					headers[4] = "" + e.getEndTime();
					writer.writeRecord(headers);
				}
				writer.close();
				return true;
			
			} catch (IOException e1) {
			}
			
		}

		return false;
	}

}
