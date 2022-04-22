package tomek.it.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

// CREATING DATA - Advanced!!!
/*
 *  Table: USER
 * 			name
 * 			secondname
 *  		surname	 
 *  		id		 
 *  
 *  Table: ADDRESS
 * 			street
 * 			home
 * 			room	 
 *  		id
 *  		user_id		 
 *  
 *  One user can have none or more than one address, secondname is not needed, name and surname is obligatory. street and home are obligatory.
 *  USER.id and ADDRESS.id are uniq key
 */

public class ConnectSQLite03a {

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

		try {  stat.executeUpdate("drop table if exists USER;");  } catch (SQLException e) { e.printStackTrace(); }
		try {  stat.executeUpdate("drop table if exists ADDRESS;");  } catch (SQLException e) { e.printStackTrace(); }

		try {  stat.execute("PRAGMA foreign_keys = ON");  } catch (SQLException e) { e.printStackTrace(); }
		
		try {
			stat.executeUpdate("create table USER (" +
					"name TEXT NOT NULL, " +
					"secondname TEXT, " +
					"surname TEXT NOT NULL, " +
					"id INTEGER NOT NULL, " +
					"PRIMARY KEY(id));");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			stat.executeUpdate("create table ADDRESS (" +
					"street TEXT NOT NULL, " +
					"home TEXT NOT NULL, " +
					"room TEXT, " +
					"id INTEGER NOT NULL, " +
					"user_id INTEGER NOT NULL, " +
					"PRIMARY KEY(id)" +
					"FOREIGN KEY (user_id) REFERENCES USER(id) ON DELETE CASCADE ON UPDATE CASCADE) ;");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		
		PreparedStatement prep = null;
		try {
			prep = conn.prepareStatement("insert into USER values (?, ?, ?, ?);");

			prep.setString(1, "John");		// OBLIGATORY
			prep.setString(2, null);
			prep.setString(3, "Brown");		// OBLIGATORY
			prep.setString(4, "10001");		// It must be an INTEGER and UNIQUE
			prep.addBatch();

			prep.setString(1, "Paul");		// OBLIGATORY
			prep.setString(2, "Mark");
			prep.setString(3, "Black");		// OBLIGATORY
			prep.setString(4, "10002");		// It must be an INTEGER and UNIQUE
			prep.addBatch();
			
			prep.setString(1, "Paul");		// OBLIGATORY
			prep.setString(2, "Adam");
			prep.setString(3, "Grey");		// OBLIGATORY
			prep.setString(4, "10003");		// It must be an INTEGER and UNIQUE
			prep.addBatch();

			prep.setString(1, "Thomas");		// OBLIGATORY
			prep.setString(2, null);
			prep.setString(3, "Silver");		// OBLIGATORY
			prep.setString(4, "10004");		// It must be an INTEGER and UNIQUE
			prep.addBatch();

			prep.setString(1, "Ann");		// OBLIGATORY
			prep.setString(2, null);
			prep.setString(3, "Brown");		// OBLIGATORY
			prep.setString(4, "10005");		// It must be an INTEGER and UNIQUE
			prep.addBatch();

			prep.setString(1, "Eva");		// OBLIGATORY
			prep.setString(2, null);
			prep.setString(3, "Black");		// OBLIGATORY
			prep.setString(4, "10006");		// It must be an INTEGER and UNIQUE
			prep.addBatch();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}		
		
		try {
			prep.executeBatch();
			prep.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			prep = conn.prepareStatement("insert into ADDRESS values (?, ?, ?, ?, ?);");

			prep.setString(1, "Avenue of Roses");		// OBLIGATORY
			prep.setString(2, "100");					// OBLIGATORY
			prep.setString(3, "17");					
			prep.setString(4, "20001");					// It must be an INTEGER and UNIQUE
			prep.setString(5, "10001");					// It must be an INTEGER
			prep.addBatch();

			prep.setString(1, "Avenue of Angels");		// OBLIGATORY
			prep.setString(2, "909");					// OBLIGATORY
			prep.setString(3, "1");						
			prep.setString(4, "20002");					// It must be an INTEGER and UNIQUE
			prep.setString(5, "10002");					// It must be an INTEGER
			prep.addBatch();

			prep.setString(1, "Avenue of Roses");		// OBLIGATORY
			prep.setString(2, "100");					// OBLIGATORY
			prep.setString(3, "17");					
			prep.setString(4, "20003");					// It must be an INTEGER and UNIQUE
			prep.setString(5, "10005");					// It must be an INTEGER
			prep.addBatch();

			prep.setString(1, "Avenue of Angels");		// OBLIGATORY
			prep.setString(2, "909");					// OBLIGATORY
			prep.setString(3, "1");						
			prep.setString(4, "20004");					// It must be an INTEGER and UNIQUE
			prep.setString(5, "10006");					// It must be an INTEGER
			prep.addBatch();
			
			prep.setString(1, "Patriot Str.");			// OBLIGATORY
			prep.setString(2, "10");					// OBLIGATORY
			prep.setString(3, null);						
			prep.setString(4, "20005");					// It must be an INTEGER and UNIQUE
			prep.setString(5, "10004");					// It must be an INTEGER
			prep.addBatch();

			prep.setString(1, "Av. of Independence");	// OBLIGATORY
			prep.setString(2, "16");					// OBLIGATORY
			prep.setString(3, "16");						
			prep.setString(4, "20006");					// It must be an INTEGER and UNIQUE
			prep.setString(5, "10004");					// It must be an INTEGER
			prep.addBatch();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			prep.executeBatch();
			prep.close();
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
