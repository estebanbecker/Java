package tomek.it.sqlite;


// USING DRIVER
public class ConnectSQLite00 {

	public static void main(String[] args) {
		try { 
			Class.forName("org.sqlite.JDBC");			
			System.out.println("Driver was found.");
		}
		catch (ClassNotFoundException e) { 
			e.printStackTrace();
			System.exit(1);
		}
	
	}
}

// If you have a problem - first download:  https://bitbucket.org/xerial/sqlite-jdbc/downloads
// Then add this file to Referenced Libraries and run this application again.