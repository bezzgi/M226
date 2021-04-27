package m226;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class gui_library implements ActionListener {
	
	//Swing objekte

	public JFrame frame;
	private JTextField name;
	JComboBox chooseLibrary = new JComboBox();
	JComboBox deleteLibrary = new JComboBox();
	
	//Actions
	
	private String create = "create";
	private String choose = "choose";
	private String delete = "delete";
	
	//Datenbank
	
	private String conStr = "jdbc:mysql://localhost/library?user=root&password=";
	private Connection con;
	private Statement s;
	private PreparedStatement ps;
	private ResultSet rs;
	private JTextField location;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui_library window = new gui_library();
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
	 public gui_library()
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
		frame.setBounds(100, 100, 1100, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLibrary = new JLabel("Neue Bibliothek");
		lblNewLibrary.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 35));
		lblNewLibrary.setBounds(20, 20, 360, 36);
		frame.getContentPane().add(lblNewLibrary);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblName.setBounds(20, 70, 376, 19);
		frame.getContentPane().add(lblName);
		
		name = new JTextField();
		name.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		name.setColumns(10);
		name.setBounds(20, 90, 365, 28);
		frame.getContentPane().add(name);
		
		JLabel lblLocation = new JLabel("Standort");
		lblLocation.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblLocation.setBounds(20, 140, 376, 19);
		frame.getContentPane().add(lblLocation);
		
		location = new JTextField();
		location.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		location.setColumns(10);
		location.setBounds(20, 160, 365, 28);
		frame.getContentPane().add(location);
		
		JButton btnCreate = new JButton("Bibliothek erstellen");
		btnCreate.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		btnCreate.setBounds(20, 210, 150, 35);
		btnCreate.addActionListener(this);
		frame.getContentPane().add(btnCreate);
		btnCreate.setActionCommand(create);
		
		
		
		
		
		JLabel lblChooseLibrary = new JLabel("Bibliothek w\u00E4hlen");
		lblChooseLibrary.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 35));
		lblChooseLibrary.setBounds(400, 20, 360, 36);
		frame.getContentPane().add(lblChooseLibrary);
		
		JLabel lblLibrary1 = new JLabel("Bibliothek");
		lblLibrary1.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblLibrary1.setBounds(400, 70, 376, 19);
		frame.getContentPane().add(lblLibrary1);
		
		chooseLibrary.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		chooseLibrary.setBounds(400, 90, 365, 28);
		frame.getContentPane().add(chooseLibrary);
		
		JButton btnChoose = new JButton("Bibliothek ausw\u00E4hlen");
		btnChoose.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		btnChoose.setBounds(400, 210, 150, 35);
		btnChoose.addActionListener(this);
		btnChoose.setActionCommand(choose);
		frame.getContentPane().add(btnChoose);
		
		deleteLibrary.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		deleteLibrary.setBounds(780, 90, 280, 28);
		frame.getContentPane().add(deleteLibrary);
		
		JButton btnDelete = new JButton("Bibliothek l\u00F6schen");
		btnDelete.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		btnDelete.addActionListener(this);
		btnDelete.setBounds(780, 210, 150, 35);
		btnDelete.setActionCommand(delete);
		frame.getContentPane().add(btnDelete);
		
		JLabel lblDeleteLibrary = new JLabel("Bibliothek l\u00F6schen");
		lblDeleteLibrary.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 35));
		lblDeleteLibrary.setBounds(780, 20, 360, 36);
		frame.getContentPane().add(lblDeleteLibrary);
		
		JLabel lblLibrary2 = new JLabel("Bibliothek");
		lblLibrary2.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 15));
		lblLibrary2.setBounds(780, 70, 376, 19);
		frame.getContentPane().add(lblLibrary2);
		
		loadLibraries();
	}
	 
	 //Methode welche die Bibliotheken Comboboxen löscht und neu lädt
	 public void loadLibraries()
	 { 
	     try 
	     {
	    	 String query = "SELECT * FROM library";
	    	 
	    	 con = DriverManager.getConnection(this.conStr);
	    	 
	    	 s = con.createStatement();
	    	 
	    	 rs = s.executeQuery(query);
	    	 
	    	 chooseLibrary.removeAllItems();
	    	 deleteLibrary.removeAllItems();
	    	 
	    	 //Falls es Bibliotheken gibt, werden sie zur Combobox hinzugefügt
	    	 
	    	 while (rs.next())
			 {
	    		 int id_library = rs.getInt("id_library");
	    		 String name = rs.getString("name");
	    		 String location = rs.getString("location");
	    		 String fullname = id_library + " | " + name + " | " + location;
	    		 
	    		 chooseLibrary.addItem(fullname);
	    		 deleteLibrary.addItem(fullname);
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
		//Die Bibliothek wird erstellt
		if(e.getActionCommand().equals(create))
		{
			
			libraries library = new libraries();
			library.setName(gui_library.this.name.getText());
			library.setLocation(gui_library.this.location.getText());
			
			library.createLibrary(library);
			
			//Lädt die Bibliotheken neu nach dem Erstellen
			loadLibraries();
		}
		 
		//Wählt die Bibliotheke aus und öffnet das Bücher GUI
		if(e.getActionCommand().equals(choose))
		{
			String selectedLibrary = chooseLibrary.getSelectedItem().toString();
			String[] splitLibrary = selectedLibrary.split(" ");
			
			int idLibrary = Integer.parseInt(splitLibrary[0]);
			
			//Lädt alle Bücher und schliesst danach das Bibliotheken fenster
			
			gui_books gui = new gui_books();
			
			gui.setIdLibrary(idLibrary);
			
			gui.frame.setVisible(true);
			
			gui.loadBooks();
			
			this.frame.dispose();
		}
		
		//Löscht eine Bibliothek
		
		if(e.getActionCommand().equals(delete))
		{
			String selectedLibrary = deleteLibrary.getSelectedItem().toString();
			String[] splitLibrary = selectedLibrary.split(" ");	
			 
			libraries library = new libraries();
			 
			int idLibrary = Integer.parseInt(splitLibrary[0]);
			 
			library.setIdLibrary(idLibrary);
			 
			library.deleteLibrary();
			 
			loadLibraries();
		}
	}
}
