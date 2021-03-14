package fw.settings;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
	private static final Logger logger = LogManager.getLogger();

	public void pass(String logMessage) {
		logger.log(Level.forName("PASS", 350), logMessage);
	}

	public void fail(String logMessage) {
		logger.error(logMessage);
	}

	public void info(String logMessage) {
		logger.info(logMessage);
	}

	public void debug(String logMessage) {
		logger.debug(logMessage);
	}
}
