package tomek.it.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// READING DATA
public class ConnectSQLite03 {

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

		// ------------------------------------------------------------------------------------------

		Statement stat = null;
		try {
			stat = conn.createStatement();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		ResultSet rs = null;
		try {
			rs = stat.executeQuery("select * from people;");
			while (rs.next()) {
				System.out.print("name = " + rs.getString("name") + ",  ");
				System.out.println("job = " + rs.getString("occupation"));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 

		// ------------------------------------------------------------------------------------------

		try {
			conn.close();
			System.out.println("INFO: Connection was closed.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
