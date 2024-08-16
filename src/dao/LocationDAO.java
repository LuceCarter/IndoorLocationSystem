package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LocationDAO {
	
	private static LocationDAO uniqueInstance; //Variable which is used to hold unique instance of the DAO
	private Connection conn;
	private String database, user, password, url, selectSql;
	
	private LocationDAO() 
	
	{
		
		conn =null; // Create connection object
		database = "carterl"; // Name of database
		user = "carterl"; //username
		password = "jagflewP6"; //password
		url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk/" + database; //full url for access to database
		
		try{
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch(Exception e) {
		    System.err.println(e);
		}
		
		// connecting to database
		try{
		    conn = DriverManager.getConnection(url, user, password);
			
		}
		catch(SQLException se) {
		    System.err.println(se);
		}		
		
	} //Declared private so that only this class can instantiate, keeps it Singleton
	
	/*
	 * getInstance: A method to allow the creation of the unique instance of the class but also return the instance if already exists
	 * It checks if an instance already exists, if not then instantiate, else return the currnet instance
	 */
	public static LocationDAO getInstance() {
		
		if(uniqueInstance == null) {
			System.out.println("Database instance created");
			uniqueInstance = new LocationDAO();
		}
		
		
		return uniqueInstance;
	}
	
public String getLocation(String readerID) {
		
		try {
		
		String location;
		
		 
		String selectSQL = "SELECT id FROM users"
				+ " WHERE id = '" + readerID + "'"; //pull id from database
		
		Statement stmt = conn.createStatement();
		
		
		ResultSet rs1 = stmt.executeQuery(selectSQL);
		
		
		if(rs1.next()) { //If instead of while because only want one result
			String loc = rs1.getString("id");
			location = loc;
			return location;
		}
		
		else { return("Location not found");}
		
		
		
		}catch(SQLException se) {
			
			System.err.println(se);
			return se.toString();
		}
		
		
	}
	
}
