package fi.helsinki.cs.scheduler3000.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import fi.helsinki.cs.scheduler3000.model.Schedule;

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
			// TODO: implement
		}

		return false;
	}

}
