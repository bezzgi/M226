package m226;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.awt.*;

//Bücher GUI

public class gui_books implements ActionListener{

	private int idLibrary;
	
	//Swing Objekte
	
	public JFrame frame;
	JButton btnLend = new JButton("Ausleihen");
	JButton btnDelete = new JButton("L\u00F6schen");
	JButton btnNew = new JButton("Neu");
	JButton btnGiveBack = new JButton("Zur\u00FCckgeben");
	JButton btnReload = new JButton("Neu laden");
	JList availableList = new JList();
	JList lentList = new JList();
	DefaultListModel DLMAvailable = new DefaultListModel();
	DefaultListModel DLMLent = new DefaultListModel();
	JLabel lblLibrary = new JLabel("Bibliothek: ");
	JLabel lblLocation = new JLabel("Standort: ");
	
	//Actions
	
	private String deleteBook = "deleteBook";
	private String lendBook = "lendBook";
	private String giveBack = "giveBack";
	private String newBook = "newBook";
	private String reload = "reload";
	
	//Datenbank
	
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
					gui_books window = new gui_books();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	//Konstruktor des GUIs
	
	public gui_books() {
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		availableList.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		
		availableList.setBounds(20, 65, 445, 133);
		availableList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		frame.getContentPane().add(availableList);
		
		
		JLabel lblAvailableBooks = new JLabel("Verf\u00FCgbare B\u00FCcher");
		lblAvailableBooks.setVerticalAlignment(SwingConstants.TOP);
		lblAvailableBooks.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 35));
		lblAvailableBooks.setBounds(20, 22, 444, 43);
		frame.getContentPane().add(lblAvailableBooks);
        btnLend.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		
		
        btnLend.addActionListener(this);
        btnLend.setBounds(20, 220, 120, 30);
        btnLend.setActionCommand(lendBook);
        frame.getContentPane().add(btnLend);
        btnDelete.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));

        btnDelete.addActionListener(this);
        btnDelete.setBounds(184, 220, 120, 30);
        btnDelete.setActionCommand(deleteBook);
        frame.getContentPane().add(btnDelete);
        btnNew.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
        
        btnNew.addActionListener(this);
        btnNew.setBounds(345, 220, 120, 30);
        btnNew.setActionCommand(newBook);
        frame.getContentPane().add(btnNew);
        lentList.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
        
        lentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lentList.setBounds(20, 335, 445, 133);
        frame.getContentPane().add(lentList);
        
        
        
        JLabel lblLentBooks = new JLabel("Ausgeliehene B\u00FCcher");
        lblLentBooks.setVerticalAlignment(SwingConstants.TOP);
        lblLentBooks.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 35));
        lblLentBooks.setBounds(20, 292, 444, 43);
        frame.getContentPane().add(lblLentBooks);
        btnGiveBack.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
        
        btnGiveBack.addActionListener(this);
        btnGiveBack.setBounds(20, 490, 120, 30);
        btnGiveBack.setActionCommand(giveBack);
        frame.getContentPane().add(btnGiveBack);
        btnReload.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
        
        btnReload.addActionListener(this);
        btnReload.setBounds(345, 490, 120, 30);
        btnReload.setActionCommand(reload);
        frame.getContentPane().add(btnReload);
        
        
        lblLibrary.setHorizontalAlignment(SwingConstants.LEFT);
        lblLibrary.setVerticalAlignment(SwingConstants.TOP);
        lblLibrary.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
        lblLibrary.setBounds(20, 530, 218, 19);
        frame.getContentPane().add(lblLibrary);
        
        
        lblLocation.setHorizontalAlignment(SwingConstants.RIGHT);
        lblLocation.setVerticalAlignment(SwingConstants.TOP);
        lblLocation.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
        lblLocation.setBounds(247, 530, 218, 19);
        frame.getContentPane().add(lblLocation);
	}
	
	//Methode welche die Bücherliste löscht und sie neu lädt
	public void loadBooks()
	{	
		try 
	     {					
	    	 String queryAvailable = "SELECT * FROM books as b left join authors as s on s.id_authors = b.authors_id_authors where b.lent = 0 and b.library_id_library = " + idLibrary + " order by title";
	    	 
	    	 con = DriverManager.getConnection(this.conStr);
	    	 
	    	 s = con.createStatement();
	    	 
	    	 rs = s.executeQuery(queryAvailable);
	    	 
	    	 DLMAvailable.removeAllElements();
	    	 
	    	 //Falls Bücher existieren, werden sie in die Combobox geschrieben
	    	 
	    	 while (rs.next())
			 {
	    		 int id_books = rs.getInt("id_books");
	    		 String title = rs.getString("title");
	    		 int pages = rs.getInt("pages");
	    		 String firstname = rs.getString("firstname");
	    		 String lastname = rs.getString("lastname");
	    		 
	    		 DLMAvailable.addElement(id_books + " | " + title + " | " + pages + " | " + firstname + " " + lastname);
	    		 availableList.setModel(DLMAvailable);
			 }
	    	 
	    	 //Derselbe Code, einfach für die andere Combobox
	    	 
	    	 String queryLent = "SELECT * FROM books as b left join authors as s on s.id_authors = b.authors_id_authors where b.lent = 1 and b.library_id_library = " + idLibrary + " order by title";
	    	 
	    	 con = DriverManager.getConnection(this.conStr);
	    	 
	    	 s = con.createStatement();
	    	 
	    	 rs = s.executeQuery(queryLent);
	    	 
	    	 DLMLent.removeAllElements();
	    	 
	    	 while (rs.next())
			 {
	    		 int id_books = rs.getInt("id_books");
	    		 String title = rs.getString("title");
	    		 int pages = rs.getInt("pages");
	    		 String firstname = rs.getString("firstname");
	    		 String lastname = rs.getString("lastname");
	    		 
	    		 DLMLent.addElement(id_books + " | " + title + " | " + pages + " | " + firstname + " " + lastname);
	    		 lentList.setModel(DLMLent);
			 }
	    	 
	    	 
	    	 
	    	 
	    	 String queryIdLibrary = "SELECT * FROM library where id_library = " + idLibrary;
	    	 
	    	 con = DriverManager.getConnection(this.conStr);
	    	 
	    	 s = con.createStatement();
	    	 
	    	 rs = s.executeQuery(queryIdLibrary);
	    	 
	    	 while (rs.next())
			 {
	    		 String name = rs.getString("name");
	    		 String location = rs.getString("location");
	    		 
	    		 lblLibrary.setText("Bibliothek: " + name);
	    		 lblLocation.setText("Standort: " + location);
			 }
	    	 
	    	 con.close();
	     }
		 catch (SQLException sqle1) 
	     {
			 
		 }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		//Löscht das Buch
		if(e.getActionCommand().equals(deleteBook))
		{
			try
			{
				
				//Nimmt die ID und löst eine Methode aus
				String listValue = availableList.getSelectedValue().toString();
				
				String[] splitList  = listValue.split(" ");
				
				books book = new books();
				
				book.setIdBook(Integer.parseInt(splitList[0]));
				
				book.deleteBook();
				
				loadBooks();
			}
			catch(NullPointerException npe)
			{
				
			}
		}
		
		//Leiht das Buch aus mit der lend Methode
		if(e.getActionCommand().equals(lendBook))
		{
			try
			{
				//Nimmt die ID und löst eine Methode aus
				String listValue = availableList.getSelectedValue().toString();
				
				String[] splitList  = listValue.split(" ");
				
				books book = new books();
				
				book.setIdBook(Integer.parseInt(splitList[0]));
				
				book.lend();
				
				loadBooks();
			}
			catch(NullPointerException npe)
			{
				
			}
		}
		
		//Gibt das Buch zurück mit der giveBack Methode
		if(e.getActionCommand().equals(giveBack))
		{
			try 
			{
				//Nimmt die ID und löst eine Methode aus
				String listValue = lentList.getSelectedValue().toString();
				
				String[] splitList  = listValue.split(" ");
				
				books book = new books();
				
				book.setIdBook(Integer.parseInt(splitList[0]));
				
				book.giveBack();
				
				loadBooks();
			} 
			catch (NullPointerException npe) 
			{
				
			}
		}
		
		//Öffnet das New GUI
		if(e.getActionCommand().equals(newBook))
		{
			gui_new start = new gui_new();
			
			start.frame.setVisible(true);
			
			start.setIdLibrary(idLibrary);
		}
		
		if(e.getActionCommand().equals(reload))
		{
			loadBooks();
		}
	}
}
