package m226;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class gui_swing  extends JFrame implements ActionListener{

	private JFrame frame;
	private JTextField title;
	private JTextField firstname;
	private JTextField lastname;
	JButton btnBook = new JButton("Buch hinzuf\u00FCgen");
	JButton btnAuthor = new JButton("Author hinzuf\u00FCgen");
	
	private String authorCommand = "author";
	private String bookCommand = "book";
	


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
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JComboBox author = new JComboBox();
		author.setBounds(20, 159, 365, 28);
		frame.getContentPane().add(author);
		
		JLabel lblPages = new JLabel("Seiten");
		lblPages.setFont(new Font("Corbel Light", Font.PLAIN, 15));
		lblPages.setBounds(20, 208, 376, 19);
		frame.getContentPane().add(lblPages);
		
		JSpinner pages = new JSpinner();
		pages.setBounds(20, 228, 365, 28);
		frame.getContentPane().add(pages);
		
		JButton btnBook = new JButton("Buch hinzuf\u00FCgen");
		btnBook.setBounds(20, 279, 150, 35);
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
		
		JButton btnAuthor = new JButton("Author hinzuf\u00FCgen");
		btnAuthor.setBounds(400, 279, 150, 35);
		btnAuthor.addActionListener(this);
		frame.getContentPane().add(btnAuthor);
		btnAuthor.setActionCommand(authorCommand);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		if(e.getActionCommand().equals(authorCommand)) 
		{			
			authors author = new authors();
			author.setFirstname(gui_swing.this.firstname.getText());
			author.setLastname(gui_swing.this.lastname.getText());
			
			author.createAuthor(author);
		}	
		
		
		if(e.getActionCommand().equals(bookCommand)) 
		{			
			
		}	
	}
}
