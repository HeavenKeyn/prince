package server.log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Log implements Logger{
	private String tag;
	private List<Logger> loggers;

	Log(String tag) {
		this.tag = tag;
		loggers = new ArrayList<>();
		loggers.add(this);
	}
	
	public Log add(Logger logger) {
		loggers.add(logger);
		return this;
	}
	
	public void info(Object msg) {
		log(Level.INFO, msg);
	}
	
	public void warn(Object msg) {
		log(Level.WARN, msg);
	}
	
	public void error(Object msg) {
		log(Level.ERROR, msg);
	}
	
	private void log(Level level,Object msg) {
		Calendar calendar = Calendar.getInstance();
		Thread thread = Thread.currentThread();
		for (Logger logger : loggers) {
			logger.print(new Item(calendar, level, thread, tag, msg));
		}
	}

	@Override
	public void print(Item item) {
		System.out.println(item);
	}
	


}
