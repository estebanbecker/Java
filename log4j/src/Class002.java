//import org.apache.logging.log4j.BasicConfigurator;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class Class002 {
	/*
	 * To use the log4j you have to download the package: Download:
	 * http://logging.apache.org/log4j/1.2/download.html Add: lo44j-1.2.16.jar
	 * Look here: http://logging.apache.org/log4j/1.2/manual.html
	 */

	// Levels: TRACE, DEBUG, INFO, WARN, ERROR and FATAL	
	Logger logger = LogManager.getLogger(this.getClass().getName());

	public Class002() {
		logger.info("Entering application.");
		
		System.out.println("Application is working");
		System.out.println(logger);
		logger.info("Exiting application.");		
	}
	
	public static void main(String[] args) {

		// Set up a simple configuration that logs on the console.
		//BasicConfigurator.configure();
		
		new Class002();
		new Class002();
	}
}