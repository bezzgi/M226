package m226;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class gui_books extends JFrame implements ActionListener{

	private JFrame frame;
	JButton btnLend = new JButton("Ausleihen");
	JButton btnDelete = new JButton("Löschen");
	JButton btnNew = new JButton("Neu");
	JButton btnGiveBack = new JButton("Zurückgeben");
	JList availableList = new JList();
	JList lentList = new JList();
	DefaultListModel DLMAvailable = new DefaultListModel();
	DefaultListModel DLMLent = new DefaultListModel();
	
	
	private String deleteBook = "deleteBook";
	private String lendBook = "lendBook";
	private String giveBack = "giveBack";
	private String newBook = "newBook";
	
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
					gui_books window = new gui_books();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public gui_books() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		availableList.setBounds(20, 65, 445, 133);
		availableList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		frame.getContentPane().add(availableList);
		
		
		JLabel lblAvailableBooks = new JLabel("Verfügbare Bücher");
		lblAvailableBooks.setVerticalAlignment(SwingConstants.TOP);
		lblAvailableBooks.setFont(new Font("Corbel", Font.PLAIN, 35));
		lblAvailableBooks.setBounds(20, 22, 444, 43);
		frame.getContentPane().add(lblAvailableBooks);
		
		
        btnLend.addActionListener(this);
        btnLend.setBounds(20, 220, 120, 30);
        btnLend.setActionCommand(lendBook);
        frame.getContentPane().add(btnLend);

        btnDelete.addActionListener(this);
        btnDelete.setBounds(184, 220, 120, 30);
        btnDelete.setActionCommand(deleteBook);
        frame.getContentPane().add(btnDelete);
        
        btnNew.addActionListener(this);
        btnNew.setBounds(345, 220, 120, 30);
        btnNew.setActionCommand(newBook);
        frame.getContentPane().add(btnNew);
        
        lentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lentList.setBounds(20, 335, 445, 133);
        frame.getContentPane().add(lentList);
        
        
        
        JLabel lblLentBooks = new JLabel("Ausgeliehene Bücher");
        lblLentBooks.setVerticalAlignment(SwingConstants.TOP);
        lblLentBooks.setFont(new Font("Corbel", Font.PLAIN, 35));
        lblLentBooks.setBounds(20, 292, 444, 43);
        frame.getContentPane().add(lblLentBooks);
        
        btnGiveBack.addActionListener(this);
        btnGiveBack.setActionCommand("lendBook");
        btnGiveBack.setBounds(20, 490, 120, 30);
        btnGiveBack.setActionCommand(giveBack);
        frame.getContentPane().add(btnGiveBack);
		
		loadBooks();
	}
	
	public void loadBooks()
	{

		try 
	     {
	    	 String queryAvailable = "SELECT * FROM books as b left join authors as s on s.id_authors = b.authors_id_authors where b.lent = 0";
	    	 
	    	 con = DriverManager.getConnection(this.conStr);
	    	 
	    	 s = con.createStatement();
	    	 
	    	 rs = s.executeQuery(queryAvailable);
	    	 
	    	 DLMAvailable.removeAllElements();
	    	 
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
	    	 
	    	 
	    	 
	    	 String queryLable = "SELECT * FROM books as b left join authors as s on s.id_authors = b.authors_id_authors where b.lent = 1";
	    	 
	    	 con = DriverManager.getConnection(this.conStr);
	    	 
	    	 s = con.createStatement();
	    	 
	    	 rs = s.executeQuery(queryLable);
	    	 
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
	    	 
	    	 
	    	 con.close();
	     }
		 catch (SQLException sqle1) 
	     {
			 
		 }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals(deleteBook))
		{
			try 
			{
				String listValue = availableList.getSelectedValue().toString();
				
				String[] splitList  = listValue.split(" ");
				
				System.out.println(splitList[0]);
				
				try 
			    {
					String query = "DELETE FROM books WHERE id_books = '" + splitList[0] + "'";
			    	 
			    	con = DriverManager.getConnection(this.conStr);
			    	 
			    	s = con.createStatement();
			    	 
			    	s.executeUpdate(query);
			    	 
			    	con.close();
			    } 
			    catch (SQLException sqle) 
			    {
			    	
			    }
				
				loadBooks();
			} 
			catch (NullPointerException npe) 
			{
				
			}
			
		}
		
		if(e.getActionCommand().equals(lendBook))
		{

			String listValue = availableList.getSelectedValue().toString();
			
			String[] splitList  = listValue.split(" ");
			
			System.out.println(splitList[0]);
			
			try 
		    {
				String query = "UPDATE books SET lent = 1 WHERE id_books = '" + splitList[0] + "'";
		    	 
		    	con = DriverManager.getConnection(this.conStr);
		    	 
		    	s = con.createStatement();
		    	 
		    	s.executeUpdate(query);
		    	 
		    	con.close();
		    } 
		    catch (SQLException sqle) 
		    {
		    	
		    }
			
			loadBooks();
			
		}
		
		if(e.getActionCommand().equals(giveBack))
		{
			String listValue = lentList.getSelectedValue().toString();
			
			String[] splitList  = listValue.split(" ");
			
			System.out.println(splitList[0]);
			
			try 
		    {
				String query = "UPDATE books SET lent = 0 WHERE id_books = '" + splitList[0] + "'";
		    	 
		    	con = DriverManager.getConnection(this.conStr);
		    	 
		    	s = con.createStatement();
		    	 
		    	s.executeUpdate(query);
		    	 
		    	con.close();
		    } 
		    catch (SQLException sqle) 
		    {
		    	
		    }
			
			loadBooks();
		}
		
		
		if(e.getActionCommand().equals(newBook))
		{
			gui_swing start = new gui_swing();
			
			start.frame.setVisible(true);
			
		}
	}
}
