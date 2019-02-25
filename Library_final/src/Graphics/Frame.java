package Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame {

	Menu menu = new Menu();
	
	public Frame() {
		
		
		super("Library");
		setResizable(false);
		add(menu);
		setJMenuBar(Menu.menuBar);
		setLocation(200, 00);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		pack();
		
	}
	
	
}
