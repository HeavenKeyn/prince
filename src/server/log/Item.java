package server.log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Item {
	public Calendar calendar;public Level level;public Thread thread;public String tag;public Object msg;

	public Item(Calendar calendar, Level level, Thread thread, String tag, Object msg) {
		this.calendar = calendar;
		this.level = level;
		this.thread = thread;
		this.tag = tag;
		this.msg = msg;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "["+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime())+" "+
				level.toString()+" "+thread.getName()+" "+tag+"]"+" "+msg;
	}
	

}
