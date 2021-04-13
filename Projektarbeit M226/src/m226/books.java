package m226;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class books implements InterfaceBooks{
	
	String title;
	int pages;
	int lent;
	int idAuthor;
	
	private String conStr = "jdbc:mysql://localhost/library?user=root&password=";
	private Connection con;
	private Statement s;
	private PreparedStatement ps;
	private ResultSet rs;
	
	
	//Setter und Getter für alle Variablen
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
	
	
	
	
	public void lend() {
		
	}
	
	public void giveBack() {
		
	}

	public void createBook(books book) {
		
		try 
		{
			// get data connection and data statement
			con = DriverManager.getConnection(this.conStr);
			
			String query = "insert into books (title, pages, lent, authors_id_authors) values (?,?,?,?)";
			ps = con.prepareStatement(query);
			ps.setString(1, book.getTitle());
			ps.setInt(2, book.getPages());
			ps.setInt(3, book.getLent());
			ps.setInt(4, book.getIdAuthor());
			
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
	
	
	
	public void deleteBook() {

	}
}
