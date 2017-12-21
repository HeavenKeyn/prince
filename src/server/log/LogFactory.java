package server.log;

import java.util.Hashtable;

public class LogFactory {
	private final static Hashtable<String, Log> container = new Hashtable<>();
	
	public synchronized static Log getLog(String tag) {
		Log log = container.get(tag);
		if (log==null) {
			log = new Log(tag);
			container.put(tag, log);
		}
		return log;
	}
	
	public synchronized static Log getLog(Class<?> tag) {
		return getLog(tag.getName());
	}

}
