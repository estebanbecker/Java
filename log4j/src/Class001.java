//import org.apache.logging.log4j.BasicConfigurator;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class Class001 {

	/*
	 * To use the log4j you have to download the package: Download:
	 * http://logging.apache.org/log4j/1.2/download.html Add: log4j-1.2.16.jar
	 * Look here: http://logging.apache.org/log4j/1.2/manual.html
	 */

	// Levels: TRACE, DEBUG, INFO, WARN, ERROR and FATAL	
	final static Logger logger = LogManager.getLogger(Class001.class);

	public static void main(String[] args) {

		// Set up a simple configuration that logs on the console.
		//BasicConfigurator.configure();
		
		
		logger.info("Entering application.");
		logger.trace("Entering application (trace).");
		logger.debug("Entering application (debug).");
		logger.error("Entering application (error).");
		logger.fatal("Entering application (fatal).");
		
		System.out.println("Application is working");
		
		logger.info("Exiting application.");
	}
}