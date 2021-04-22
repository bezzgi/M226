package m226;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class libraries {

	int idLibrary;
	String name;
	String location;

	private String conStr = "jdbc:mysql://localhost/library?user=root&password=";
	private Connection con;
	private Statement s;
	private PreparedStatement ps;

	public void setIdLibrary(int idLibrary) {
		this.idLibrary = idLibrary;
	}
	public int getIdLibrary() {
		return idLibrary;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLocation() {
		return location;
	}

	

	public void createLibrary(libraries library) {
		
		try
		{
			// get data connection and data statement
			con = DriverManager.getConnection(this.conStr);
			
			String query = "insert into library (name, location) values (?,?)";
			ps = con.prepareStatement(query);
			ps.setString(1, library.getName());
			ps.setString(2, library.getLocation());

			
			ps.execute();
			// close data statement and data connection
			ps.close();
			con.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
	}
	
	public void deleteLibrary()
	{
		 try 
	     {
	    	 String query = "DELETE FROM library WHERE id_library = '" + idLibrary + "'";
	    	 
	    	 con = DriverManager.getConnection(this.conStr);
	    	 
	    	 s = con.createStatement();
	    	 
	    	 s.executeUpdate(query);
	    	 
	    	 con.close();
	     } 
	     catch (SQLException sqle) 
	     {
	    	 sqle.printStackTrace();
	     }
	}
}
