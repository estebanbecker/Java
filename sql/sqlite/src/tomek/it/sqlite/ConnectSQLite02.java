package tomek.it.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

// CREATING DATA
public class ConnectSQLite02 {

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
		try {
			stat.executeUpdate("drop table if exists people;");
			stat.executeUpdate("create table people (name, occupation);");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		PreparedStatement prep = null;
		try {
			prep = conn.prepareStatement("insert into people values (?, ?);");
			prep.setString(1, "Gandhi");
			prep.setString(2, "politics");
			prep.addBatch();
			prep.setString(1, "Turing");
			prep.setString(2, "computers");
			prep.addBatch();
			prep.setString(1, "Darwin");
			prep.setString(2, "biology");
			prep.addBatch();
			prep.setString(1, "Cialdini");
			prep.setString(2, "psychology");
			prep.addBatch();
			prep.setString(1, "Homer");
			prep.setString(2, "poems");
			prep.addBatch();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			prep.executeBatch();
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
