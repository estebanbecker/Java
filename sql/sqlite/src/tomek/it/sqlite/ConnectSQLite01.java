package tomek.it.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// ESTABLISHING CONNECTION
public class ConnectSQLite01 {

	public static void main(String[] args) {
		try { 
			Class.forName("org.sqlite.JDBC");			
			System.out.println("INFO: Driver was found.");
		}
		catch (ClassNotFoundException e) { 
			System.err.println("ERROR: You should download driver first:  https://bitbucket.org/xerial/sqlite-jdbc");
			System.exit(1);
		}
	
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:file.db");
			System.out.println("INFO: Connection was established.");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		// THE REST OF PROGRAMME COULD BE PUT HERE!
		
		try {
			conn.close();
			System.out.println("INFO: Connection was closed.");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
