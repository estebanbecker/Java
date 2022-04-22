package tomek.it.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// READING DATA
public class ConnectSQLite03f {

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

		ResultSet rs = null;
		try {
			rs = stat.executeQuery("select ADDRESS.id address_id, USER.id user_id, name, surname, street, home, room from ADDRESS LEFT JOIN USER on USER.id=ADDRESS.user_id ;");
			while (rs.next()) {
				System.out.print("("+rs.getString("user_id")+")  ");
				System.out.print(rs.getString("name")+" ");
				System.out.print(rs.getString("surname")+ "  ");
				System.out.print("("+rs.getString("address_id")+")  ");
				System.out.print(rs.getString("street")+" ");
				System.out.print(rs.getString("home")+" ");
				System.out.println(rs.getString("room"));
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
