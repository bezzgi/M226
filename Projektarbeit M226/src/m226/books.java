package m226;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class books implements InterfaceBooks{
	
	int idBook;
	String title;
	int pages;
	int lent;
	int idAuthor;
	int idLibrary;
	
	private String conStr = "jdbc:mysql://localhost/library?user=root&password=";
	private Connection con;
	private Statement s;
	private PreparedStatement ps;
	
	
	//Getter und Setter
	public void setIdBook(int id_books) {
		this.idBook = id_books;
	}
	public int getIdBook() {
		return idBook;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getPages() {
		return pages;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setLent(int lent) {
		this.lent = lent;
	}
	public int getLent() {
		return lent;
	}
	public void setIdAuthor(int idAuthor) {
		this.idAuthor = idAuthor;
	}
	public int getIdAuthor() {
		return idAuthor;
	}
	public void setIdLibrary(int idLibrary) {
		this.idLibrary = idLibrary;
	}
	public int getIdLibrary() {
		return idLibrary;
	}
	
	
	
	//Methode um Bücher auszuleihen
	public void lend() 
	{
		try 
	    {
			//Setzt den wert lent auf 1, sodass das Buch als ausgeliehen angezeigt wird
			
			String query = "UPDATE books SET lent = 1 WHERE id_books = '" + idBook + "'";
	    	 
	    	con = DriverManager.getConnection(this.conStr);
	    	 
	    	s = con.createStatement();
	    	 
	    	s.executeUpdate(query);
	    	 
	    	con.close();
	    } 
	    catch (SQLException sqle) 
	    {
	    	
	    }
	}
	
	//Methode um Bücher zurückzugeben
	public void giveBack() 
	{
		try 
	    {
			//Setzt den wert lent auf 0, sodass das Buch zurückgegeben wird
			
			String query = "UPDATE books SET lent = 0 WHERE id_books = '" + idBook + "'";
	    	 
	    	con = DriverManager.getConnection(this.conStr);
	    	 
	    	s = con.createStatement();
	    	 
	    	s.executeUpdate(query);
	    	 
	    	con.close();
	    } 
	    catch (SQLException sqle) 
	    {
	    	
	    }
	}
	
	
	//Methode zum erstellen eines Buchs
	public void createBook(books book) {
		
		try 
		{
			// get data connection and data statement
			con = DriverManager.getConnection(this.conStr);
			
			String query = "insert into books (title, pages, lent, authors_id_authors, library_id_library) values (?,?,?,?,?)";
			ps = con.prepareStatement(query);
			ps.setString(1, book.getTitle());
			ps.setInt(2, book.getPages());
			ps.setInt(3, book.getLent());
			ps.setInt(4, book.getIdAuthor());
			ps.setInt(5, book.getIdLibrary());
			
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
	
	
	//Methode zum löschen eines Buches
	public void deleteBook() 
	{
		try 
	    {
			String query = "DELETE FROM books WHERE id_books = '" + idBook + "'";
	    	 
	    	con = DriverManager.getConnection(this.conStr);
	    	 
	    	s = con.createStatement();
	    	 
	    	s.executeUpdate(query);
	    	 
	    	con.close();
	    } 
	    catch (SQLException sqle) 
	    {
	    	
	    }
	}
}
