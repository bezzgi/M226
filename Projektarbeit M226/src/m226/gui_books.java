package m226;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class gui_books extends JFrame implements ActionListener{

	private JFrame frame;

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
		
		JList list = new JList();
		list.setBounds(20, 65, 445, 133);
		frame.getContentPane().add(list);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(448, 65, 17, 133);
		frame.getContentPane().add(scrollBar);
		
		JLabel lblNewLabel = new JLabel("Verfügbare Bücher");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Corbel", Font.PLAIN, 35));
		lblNewLabel.setBounds(20, 22, 444, 43);
		frame.getContentPane().add(lblNewLabel);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
