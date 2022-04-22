package tomek.logger;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;

public class WriteLogs {

	// The log4j.properties in bin directory: "The use of LOG4J part2/bin"
	// debug.log file is in "The use of LOG4J part2" directory
	
	private static Logger log = LogManager.getLogger(WriteLogs.class);
	
	public static void main(String args[])  {

		System.out.println("Show some logs:");
		Configurator.setRootLevel(Level.WARN);
		
		log.trace("This is a TRACE example", new Exception("NOT OK"));
		log.info("This is a INFO example");
		log.debug("This is a DEBUG example");
		log.warn("This is a WARN example");
		log.error("This is a ERROR example");
		log.fatal("This is a FATAL example");
	}
}
