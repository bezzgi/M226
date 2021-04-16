package m226;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class gui_swing  extends JFrame implements ActionListener {

	public JFrame frame;
	private JTextField title;
	private JTextField firstname;
	private JTextField lastname;
	JSpinner pages = new JSpinner();
	JComboBox authorBox = new JComboBox();
	JComboBox deleteAuthorBox = new JComboBox();
	JButton btnBook = new JButton("Buch hinzufügen");
	JButton btnAuthor = new JButton("Author hinzufügen");
	JButton btnDeleteAuthor = new JButton("Autor löschen");
	
	private String authorCommand = "author";
	private String bookCommand = "book";
	private String deleteAuthorCommand = "deleteAuthor";
	
	private String conStr = "jdbc:mysql://localhost/library?user=root&password=";
	private Connection con;
	private Statement s;
	private PreparedStatement ps;
	private ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui_swing window = new gui_swing();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	/**
	 * Initialize the contents of the frame.
	 */
	 public gui_swing()
	 {	
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		 
		frame = new JFrame();
		frame.setBounds(100, 100, 1100, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNeuesBuch = new JLabel("Neues Buch");
		lblNeuesBuch.setVerticalAlignment(SwingConstants.TOP);
		lblNeuesBuch.setFont(new Font("Corbel Light", Font.PLAIN, 35));
		lblNeuesBuch.setBounds(20, 20, 360, 36);
		frame.getContentPane().add(lblNeuesBuch);
		
		JLabel lblTitle = new JLabel("Titel\r\n");
		lblTitle.setFont(new Font("Corbel Light", Font.PLAIN, 15));
		lblTitle.setBounds(20, 70, 376, 19);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblAuthor = new JLabel("Autor\r\n");
		lblAuthor.setFont(new Font("Corbel Light", Font.PLAIN, 15));
		lblAuthor.setBounds(20, 139, 376, 19);
		frame.getContentPane().add(lblAuthor);
		
		authorBox.setBounds(20, 159, 365, 28);
		frame.getContentPane().add(authorBox);
		
		JLabel lblPages = new JLabel("Seiten");
		lblPages.setFont(new Font("Corbel Light", Font.PLAIN, 15));
		lblPages.setBounds(20, 208, 376, 19);
		frame.getContentPane().add(lblPages);
		
		pages.setBounds(20, 228, 365, 28);
		frame.getContentPane().add(pages);
		
		JButton btnBook = new JButton("Buch hinzufügen");
		btnBook.setBounds(20, 280, 150, 35);
		btnBook.addActionListener(this);
		frame.getContentPane().add(btnBook);
		btnBook.setActionCommand(bookCommand);
		
		
		
		
		
		JLabel lblNeuerAutor = new JLabel("Neuer Autor");
		lblNeuerAutor.setVerticalAlignment(SwingConstants.TOP);
		lblNeuerAutor.setFont(new Font("Corbel Light", Font.PLAIN, 35));
		lblNeuerAutor.setBounds(400, 20, 360, 36);
		frame.getContentPane().add(lblNeuerAutor);
		
		title = new JTextField();
		title.setBounds(20, 90, 365, 28);
		frame.getContentPane().add(title);
		title.setColumns(10);
		
		JLabel lblFirstname = new JLabel("Vorname");
		lblFirstname.setFont(new Font("Corbel Light", Font.PLAIN, 15));
		lblFirstname.setBounds(400, 70, 376, 19);
		frame.getContentPane().add(lblFirstname);
		
		firstname = new JTextField();
		firstname.setColumns(10);
		firstname.setBounds(400, 90, 365, 28);
		frame.getContentPane().add(firstname);
		
		JLabel lblLastname = new JLabel("Nachname");
		lblLastname.setFont(new Font("Corbel Light", Font.PLAIN, 15));
		lblLastname.setBounds(400, 139, 376, 19);
		frame.getContentPane().add(lblLastname);
		
		lastname = new JTextField();
		lastname.setColumns(10);
		lastname.setBounds(400, 159, 365, 28);
		frame.getContentPane().add(lastname);
		
		JButton btnAuthor = new JButton("Author hinzufügen");
		btnAuthor.setBounds(400, 280, 150, 35);
		btnAuthor.addActionListener(this);
		frame.getContentPane().add(btnAuthor);
		btnAuthor.setActionCommand(authorCommand);
		
		JLabel lblAuthors = new JLabel("Autoren");
		lblAuthors.setFont(new Font("Corbel Light", Font.PLAIN, 15));
		lblAuthors.setBounds(780, 70, 376, 19);
		frame.getContentPane().add(lblAuthors);
		
		JLabel lblAutorLoeschen = new JLabel("Autor löschen");
		lblAutorLoeschen.setVerticalAlignment(SwingConstants.TOP);
		lblAutorLoeschen.setFont(new Font("Corbel Light", Font.PLAIN, 35));
		lblAutorLoeschen.setBounds(780, 20, 360, 36);
		frame.getContentPane().add(lblAutorLoeschen);
		
		
		
		
		
		deleteAuthorBox.setBounds(780, 90, 280, 28);
		frame.getContentPane().add(deleteAuthorBox);
		
		JButton btnDeleteAuthor = new JButton("Autor löschen");
		btnDeleteAuthor.setBounds(780, 280, 150, 35);
		btnDeleteAuthor.addActionListener(this);
		frame.getContentPane().add(btnDeleteAuthor);
		btnDeleteAuthor.setActionCommand(deleteAuthorCommand);
		
		
		
		loadAuthors();
	 }
	 
	 public void loadAuthors()
	 { 
	     try 
	     {
	    	 String query = "SELECT * FROM authors;";
	    	 
	    	 con = DriverManager.getConnection(this.conStr);
	    	 
	    	 s = con.createStatement();
	    	 
	    	 rs = s.executeQuery(query);
	    	 
	    	 authorBox.removeAllItems();
	    	 deleteAuthorBox.removeAllItems();
	    	 
	    	 while (rs.next())
			 {
	    		 int id_authors = rs.getInt("id_authors");
	    		 String firstname = rs.getString("firstname");
	    		 String lastname = rs.getString("lastname");
	    		 String fullname = id_authors + " | " + firstname + " " + lastname;
	    		 
	    		 authorBox.addItem(fullname);
	    		 
	    		 deleteAuthorBox.addItem(fullname);
			 }
	    	 
	    	 con.close();
	     } 
	     catch (SQLException sqle) 
	     {
	    	 sqle.printStackTrace();
	     }
	 }

	 @Override
	 public void actionPerformed(ActionEvent e) 
	 {	
		 if(e.getActionCommand().equals(authorCommand)) 
		 {			
			 authors author = new authors();
			 author.setFirstname(gui_swing.this.firstname.getText());
			 author.setLastname(gui_swing.this.lastname.getText());
			 
			 if(gui_swing.this.firstname.getText().equals("") || gui_swing.this.lastname.getText().equals(""))
			 {
				 
			 }
			 else
			 {
				 author.createAuthor(author);
				 loadAuthors();
			 } 
		 }	
		
		
		 if(e.getActionCommand().equals(bookCommand)) 
		 {			
			 books book = new books();
			 book.setTitle(gui_swing.this.title.getText());
			 
			 
			 int valuePages = (Integer) pages.getValue();
			 book.setPages(valuePages);
			 
			 
			 String selectedAuthor = authorBox.getSelectedItem().toString();
			 String[] splitAuthor = selectedAuthor.split(" ");	 
			 
			 
			 book.setLent(0);
			 
			 
		     try 
		     {
		    	 String query = "SELECT id_authors FROM authors where id_authors = '" + splitAuthor[0] + "'";
		    	 
		    	 con = DriverManager.getConnection(this.conStr);
		    	 
		    	 s = con.createStatement();
		    	 
		    	 rs = s.executeQuery(query);
		    	 
		    	 while (rs.next())
				 {
		    		 int idAuthor = rs.getInt("id_authors");
		    		 
		    		 book.setIdAuthor(idAuthor);
		    		 
				 }
		    	 
		    	 con.close();
		     } 
		     catch (SQLException sqle) 
		     {
		    	 sqle.printStackTrace();
		     }
		     
		     book.createBook(book);
		 }
		 
		 if(e.getActionCommand().equals(deleteAuthorCommand))
		 {
			 String selectedAuthor = deleteAuthorBox.getSelectedItem().toString();
			 String[] splitAuthor = selectedAuthor.split(" ");	
			 
			 try 
		     {
		    	 String query = "DELETE FROM authors WHERE id_authors = '" + splitAuthor[0] + "'";
		    	 
		    	 con = DriverManager.getConnection(this.conStr);
		    	 
		    	 s = con.createStatement();
		    	 
		    	 s.executeUpdate(query);
		    	 
		    	 con.close();
		     } 
		     catch (SQLException sqle) 
		     {
		    	 sqle.printStackTrace();
		     }
			 
			 loadAuthors();
		 }
	 }
}
