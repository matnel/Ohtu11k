package fi.helsinki.cs.scheduler3000.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Reader;

import fi.helsinki.cs.scheduler3000.model.Schedule;

public class ScheduleReader {

	public enum FORMAT {
		DAT
	};

	private Schedule schedule;
	private File file;
	private FORMAT format;

	public ScheduleReader(Schedule schedule, File from, FORMAT format) {
		this.schedule = schedule;
		this.file = from;
		this.format = format;
	}

	public boolean read() {
		if (format == FORMAT.DAT) {
			ObjectInputStream objectInput;
			FileInputStream fos;

			try {
				fos = new FileInputStream(this.file);
				objectInput = new ObjectInputStream(fos);
				schedule.setSchedule((Schedule) objectInput.readObject());
				return true;
			} catch (Exception e) {
			}

			return false;
		}
		return false;
	}

}
