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
	JList list = new JList();
	DefaultListModel DLM = new DefaultListModel();
	
	
	private String deleteBook = "deleteBook";
	
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
		
		list.setBounds(20, 65, 445, 133);
		list.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		frame.getContentPane().add(list);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(448, 65, 17, 133);
		frame.getContentPane().add(scrollBar);
		
		JLabel lblNewLabel = new JLabel("Verfügbare Bücher");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Corbel", Font.PLAIN, 35));
		lblNewLabel.setBounds(20, 22, 444, 43);
		frame.getContentPane().add(lblNewLabel);
		
		
        btnLend.addActionListener(this);
        btnLend.setBounds(20, 220, 120, 30);
        frame.getContentPane().add(btnLend);

        btnDelete.addActionListener(this);
        btnDelete.setBounds(184, 220, 120, 30);
        btnDelete.setActionCommand(deleteBook);
        frame.getContentPane().add(btnDelete);
        
        btnNew.addActionListener(this);
        btnNew.setBounds(345, 220, 120, 30);
        frame.getContentPane().add(btnNew);
		
		loadBooks();
	}
	
	public void loadBooks()
	{

		try 
	     {
	    	 String query = "SELECT * FROM books as b left join authors as s on s.id_authors = b.authors_id_authors where b.lent = 0";
	    	 
	    	 con = DriverManager.getConnection(this.conStr);
	    	 
	    	 s = con.createStatement();
	    	 
	    	 rs = s.executeQuery(query);
	    	 
	    	 DLM.removeAllElements();
	    	 
	    	 while (rs.next())
			 {
	    		 int id_books = rs.getInt("id_books");
	    		 String title = rs.getString("title");
	    		 int pages = rs.getInt("pages");
	    		 String firstname = rs.getString("firstname");
	    		 String lastname = rs.getString("lastname");
	    		 
	    		 DLM.addElement(id_books + " | " + title + " | " + pages + " | " + firstname + " " + lastname);
	    		 list.setModel(DLM);
			 }
	    	 
	    	 con.close();
	     }
			catch (SQLException sqle) 
	     {
	    	 sqle.printStackTrace();
	     }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals(deleteBook))
		{
			try 
			{
				String listValue = list.getSelectedValue().toString();
				
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
			    	sqle.printStackTrace();
			    }
				
				loadBooks();
			} 
			catch (NullPointerException npe) 
			{
				
			}
			
		}
		
	}
}
