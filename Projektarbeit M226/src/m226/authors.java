package m226;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class authors {
	
	public authors(){
		// init data driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	int idAuthors;
	String firstname;
	String lastname;
	
	//Datenbank

	private String conStr = "jdbc:mysql://localhost/library?user=root&password=";
	private Connection con;
	private Statement s;
	private PreparedStatement ps;
	private ResultSet rs;
	
	//Getter und Setter
	
	public void setIdAuthors(int idAuthors) {
		this.idAuthors = idAuthors;
	}
	public int getIdAuthors() {
		return idAuthors;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getLastname() {
		return lastname;
	}
	
	//Erstellt einen Autor
	
	public void createAuthor(authors author) 
	{	
		try 
		{
			// get data connection and data statement
			con = DriverManager.getConnection(this.conStr);
			
			String query = "insert into authors (firstname, lastname) values (?,?)";
			ps = con.prepareStatement(query);
			ps.setString(1, author.getFirstname());
			ps.setString(2, author.getLastname());
			
			ps.execute();
			// close data statement and data connection
			ps.close();
			con.close();
		} 
		
		//Fehlerbehandlung
		
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
	}
	
	//Löscht einen Autor
	
	public void deleteAuthor()
	{
		 try 
	     {
	    	 String query = "DELETE FROM authors WHERE id_authors = '" + idAuthors + "'";
	    	 
	    	 con = DriverManager.getConnection(this.conStr);
	    	 
	    	 s = con.createStatement();
	    	 
	    	 s.executeUpdate(query);
	    	 
	    	 con.close();
	     } 
		 
		 //Fehlerbehandlung
		 
	     catch (SQLException sqle) 
	     {
	    	 sqle.printStackTrace();
	     }
	}
}
