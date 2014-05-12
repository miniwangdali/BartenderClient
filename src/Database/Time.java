package Database;

import java.text.DateFormat;
import java.util.Date;

public class Time {

	public String getcurrentTime(){

		DateFormat df = DateFormat.getTimeInstance(DateFormat.MEDIUM);
		return df.format(new Date(System.currentTimeMillis()));
	}
	
}
