package tomek.it.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// CHANGING DATA and then run again 4b, 4c and 4d
public class ConnectSQLite04e {

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
			conn = DriverManager.getConnection("jdbc:sqlite:file2.db");
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
			stat.execute("PRAGMA foreign_keys = ON");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			stat.executeUpdate("delete from USER where id=10001;");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 

		try {
			stat.executeUpdate("update USER set id=11111 where id=10004;");
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
