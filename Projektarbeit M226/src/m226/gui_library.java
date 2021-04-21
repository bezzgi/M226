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

public class gui_library  extends JFrame implements ActionListener {

	public JFrame frame;
	private JTextField name;
	JComboBox chooseLibrary = new JComboBox();
	
	private String create = "create";
	private String choose = "choose";
	
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
		frame.setBounds(100, 100, 800, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLibrary = new JLabel("Neue Bibliothek");
		lblNewLibrary.setVerticalAlignment(SwingConstants.TOP);
		lblNewLibrary.setFont(new Font("Corbel Light", Font.PLAIN, 35));
		lblNewLibrary.setBounds(20, 20, 360, 36);
		frame.getContentPane().add(lblNewLibrary);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Corbel Light", Font.PLAIN, 15));
		lblName.setBounds(20, 70, 376, 19);
		frame.getContentPane().add(lblName);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(20, 90, 365, 28);
		frame.getContentPane().add(name);
		
		JLabel lblLocation = new JLabel("Standort");
		lblLocation.setFont(new Font("Corbel Light", Font.PLAIN, 15));
		lblLocation.setBounds(20, 140, 376, 19);
		frame.getContentPane().add(lblLocation);
		
		location = new JTextField();
		location.setColumns(10);
		location.setBounds(20, 160, 365, 28);
		frame.getContentPane().add(location);
		
		JButton btnCreate = new JButton("Bibliothek erstellen");
		btnCreate.setBounds(20, 210, 150, 35);
		btnCreate.addActionListener(this);
		frame.getContentPane().add(btnCreate);
		btnCreate.setActionCommand(create);
		
		
		
		
		
		JLabel lblChooseLibrary = new JLabel("Bibliothek wählen");
		lblChooseLibrary.setVerticalAlignment(SwingConstants.TOP);
		lblChooseLibrary.setFont(new Font("Corbel Light", Font.PLAIN, 35));
		lblChooseLibrary.setBounds(400, 20, 360, 36);
		frame.getContentPane().add(lblChooseLibrary);
		
		JLabel lblLibrary = new JLabel("Bibliothek");
		lblLibrary.setFont(new Font("Corbel Light", Font.PLAIN, 15));
		lblLibrary.setBounds(400, 70, 376, 19);
		frame.getContentPane().add(lblLibrary);
		
		chooseLibrary.setBounds(400, 90, 365, 28);
		frame.getContentPane().add(chooseLibrary);
		
		JButton btnChoose = new JButton("Bibliothek ausw\u00E4hlen");
		btnChoose.setBounds(400, 210, 150, 35);
		btnChoose.addActionListener(this);
		frame.getContentPane().add(btnChoose);
		btnChoose.setActionCommand(choose);
		
		loadLibraries();
		
		String gay= lblLibrary.getText();
	}
	 
	 public void loadLibraries()
	 { 
	     try 
	     {
	    	 String query = "SELECT * FROM library";
	    	 
	    	 con = DriverManager.getConnection(this.conStr);
	    	 
	    	 s = con.createStatement();
	    	 
	    	 rs = s.executeQuery(query);
	    	 
	    	 chooseLibrary.removeAllItems();
	    	 
	    	 while (rs.next())
			 {
	    		 int id_library = rs.getInt("id_library");
	    		 String name = rs.getString("name");
	    		 String location = rs.getString("location");
	    		 String fullname = id_library + " | " + name + " | " + location;
	    		 
	    		 chooseLibrary.addItem(fullname);
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
		if(e.getActionCommand().equals(create))
		{
			
			libraries library = new libraries();
			library.setName(gui_library.this.name.getText());
			library.setLocation(gui_library.this.location.getText());
			
			library.createLibrary(library);
			
			loadLibraries();
		}
		 
		if(e.getActionCommand().equals(choose))
		{
			String selectedLibrary = chooseLibrary.getSelectedItem().toString();
			String[] splitLibrary = selectedLibrary.split(" ");
			
			int idLibrary = Integer.parseInt(splitLibrary[0]);
			
			gui_books gui = new gui_books();
			
			gui.setIdLibrary(idLibrary);
			
			gui.frame.setVisible(true);
			
			gui.loadBooks();
			
			this.frame.dispose();
		}
	}
}
