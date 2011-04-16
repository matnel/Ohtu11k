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

	private File file;
	private FORMAT format;

	public ScheduleReader(File from, FORMAT format) {
		this.file = from;
		this.format = format;
	}

	public Schedule read() {
		if (format == FORMAT.DAT) {
			return readDat();
		} else {
            return null;
        }
	}

    private Schedule readDat() {
        ObjectInputStream objectInput;
        FileInputStream fos = null;

        try {
            fos = new FileInputStream(this.file);
            objectInput = new ObjectInputStream(fos);

            return (Schedule) objectInput.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
                
            }
        }

        return null;
    }
}
