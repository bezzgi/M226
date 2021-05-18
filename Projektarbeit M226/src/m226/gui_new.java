package m226;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class gui_new implements ActionListener {

	private int idLibrary;
	
	//Erstellen der Swing Objekte
	
	public JFrame frame;
	private JTextField title;
	private JTextField firstname;
	private JTextField lastname;
	JSpinner pages = new JSpinner();
	JComboBox authorBox = new JComboBox();
	JComboBox deleteAuthorBox = new JComboBox();
	JButton btnBook = new JButton("Buch hinzuf\u00FCgen");
	JButton btnAuthor = new JButton("Autor hinzuf\u00FCgen");
	JButton btnDeleteAuthor = new JButton("Autor l\u00F6schen");
	
	//Actions
	
	private String authorCommand = "author";
	private String bookCommand = "book";
	private String deleteAuthorCommand = "deleteAuthor";
	
	//Datenbankverbindung
	
	private String conStr = "jdbc:mysql://localhost/library?user=root&password=";
	private Connection con;
	private Statement s;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public void setIdLibrary(int idLibrary) {
		this.idLibrary = idLibrary;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui_new window = new gui_new();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	//Konstruktor des GUIs
	
	 public gui_new()
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
		lblNeuesBuch.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 35));
		lblNeuesBuch.setBounds(20, 20, 360, 36);
		frame.getContentPane().add(lblNeuesBuch);
		
		JLabel lblTitle = new JLabel("Titel\r\n");
		lblTitle.setVerticalAlignment(SwingConstants.TOP);
		lblTitle.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblTitle.setBounds(20, 70, 376, 19);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblAuthor = new JLabel("Autor\r\n");
		lblAuthor.setVerticalAlignment(SwingConstants.TOP);
		lblAuthor.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblAuthor.setBounds(20, 139, 376, 19);
		frame.getContentPane().add(lblAuthor);
		authorBox.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		
		authorBox.setBounds(20, 159, 365, 28);
		frame.getContentPane().add(authorBox);
		
		JLabel lblPages = new JLabel("Seiten");
		lblPages.setVerticalAlignment(SwingConstants.TOP);
		lblPages.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblPages.setBounds(20, 208, 376, 19);
		frame.getContentPane().add(lblPages);
		pages.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		
		pages.setBounds(20, 228, 365, 28);
		frame.getContentPane().add(pages);
		
		JButton btnBook = new JButton("Buch hinzuf\u00FCgen");
		btnBook.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		btnBook.setBounds(20, 280, 150, 35);
		btnBook.addActionListener(this);
		frame.getContentPane().add(btnBook);
		btnBook.setActionCommand(bookCommand);
		
		
		
		
		
		JLabel lblNeuerAutor = new JLabel("Neuer Autor");
		lblNeuerAutor.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 35));
		lblNeuerAutor.setBounds(400, 20, 360, 36);
		frame.getContentPane().add(lblNeuerAutor);
		
		title = new JTextField();
		title.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		title.setBounds(20, 90, 365, 28);
		frame.getContentPane().add(title);
		title.setColumns(10);
		
		JLabel lblFirstname = new JLabel("Vorname");
		lblFirstname.setVerticalAlignment(SwingConstants.TOP);
		lblFirstname.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblFirstname.setBounds(400, 70, 376, 19);
		frame.getContentPane().add(lblFirstname);
		
		firstname = new JTextField();
		firstname.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		firstname.setColumns(10);
		firstname.setBounds(400, 90, 365, 28);
		frame.getContentPane().add(firstname);
		
		JLabel lblLastname = new JLabel("Nachname");
		lblLastname.setVerticalAlignment(SwingConstants.TOP);
		lblLastname.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblLastname.setBounds(400, 139, 376, 19);
		frame.getContentPane().add(lblLastname);
		
		lastname = new JTextField();
		lastname.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		lastname.setColumns(10);
		lastname.setBounds(400, 159, 365, 28);
		frame.getContentPane().add(lastname);
		
		JButton btnAuthor = new JButton("Autor hinzuf\u00FCgen");
		btnAuthor.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		btnAuthor.setBounds(400, 280, 150, 35);
		btnAuthor.addActionListener(this);
		frame.getContentPane().add(btnAuthor);
		btnAuthor.setActionCommand(authorCommand);
		
		JLabel lblAuthors = new JLabel("Autoren");
		lblAuthors.setVerticalAlignment(SwingConstants.TOP);
		lblAuthors.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblAuthors.setBounds(780, 70, 376, 19);
		frame.getContentPane().add(lblAuthors);
		
		JLabel lblAutorLoeschen = new JLabel("Autor l\u00F6schen");
		lblAutorLoeschen.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 35));
		lblAutorLoeschen.setBounds(780, 20, 360, 36);
		frame.getContentPane().add(lblAutorLoeschen);
		deleteAuthorBox.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		
		
		
		
		
		deleteAuthorBox.setBounds(780, 90, 280, 28);
		frame.getContentPane().add(deleteAuthorBox);
		
		JButton btnDeleteAuthor = new JButton("Autor l\u00F6schen");
		btnDeleteAuthor.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		btnDeleteAuthor.setBounds(780, 280, 150, 35);
		btnDeleteAuthor.addActionListener(this);
		frame.getContentPane().add(btnDeleteAuthor);
		btnDeleteAuthor.setActionCommand(deleteAuthorCommand);
		
		
		//Lädt die Autoren
		loadAuthors();
	 }
	 
	 //Methode welche die Autoren lädt und die Liste bereinigt.
	 
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
	    	 
	    	 //Falls ein Autor existiert, wird er in die Combobox eingefügt
	    	 
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
	     
	     // Fehlerbehandlung
	     
	     catch (SQLException sqle) 
	     {
	    	 sqle.printStackTrace();
	     }
	 }

	 
	 //Action Event Listener
	 @Override
	 public void actionPerformed(ActionEvent e) 
	 {	
		 //Autor erstellen
		 
		 if(e.getActionCommand().equals(authorCommand)) 
		 {			
			 authors author = new authors();
			 author.setFirstname(gui_new.this.firstname.getText());
			 author.setLastname(gui_new.this.lastname.getText());
			 
			//Falls die Eingabe korrekt ist, wird sie weitergeleitet und ein neuer Autor erstellt
			 
			 if(gui_new.this.firstname.getText().equals("") || gui_new.this.lastname.getText().equals(""))
			 {
				 
			 }
			 else
			 {
				 author.createAuthor(author);
				 loadAuthors();
			 } 
		 }	
		
		//Buch erstellen
		
		 if(e.getActionCommand().equals(bookCommand)) 
		 {			
			 books book = new books();
			 book.setTitle(gui_new.this.title.getText());
			 
			 
			 int valuePages = (Integer) pages.getValue();
			 book.setPages(valuePages);
			 
			 //Das Buch ist am Anfang noch nicht ausgeliehen
			 book.setLent(0);
			 
			 //Nimmt die Eingabe und schneidet alles ausser die ID weg
			 String selectedAuthor = authorBox.getSelectedItem().toString();
			 String[] splitAuthor = selectedAuthor.split(" ");	 
			 int idAuthor = Integer.parseInt(splitAuthor[0]);
			 book.setIdAuthor(idAuthor);
			 book.setIdLibrary(idLibrary);
			 
			//Falls die Eingabe korrekt ist, wird sie weitergeleitet und ein neues Buch wird erstellt
			 
			 if(gui_new.this.title.getText().equals("") || valuePages == 0)
			 {
				 
			 }
			 else
			 {
				 book.createBook(book);
			 } 
		 }
		 
		 //Autor löschen
		 
		 if(e.getActionCommand().equals(deleteAuthorCommand))
		 {
			 //Nimmt die ID des Autors, löscht sie mit der Methode und reloaded die Combobox
			 
			 String selectedAuthor = deleteAuthorBox.getSelectedItem().toString();
			 String[] splitAuthor = selectedAuthor.split(" ");	
			 
			 authors author = new authors();
			 
			 int idAuthor = Integer.parseInt(splitAuthor[0]);
			 
			 author.setIdAuthors(idAuthor);
			 
			 author.deleteAuthor();
			 
			 loadAuthors();
		 }
	 }
}
