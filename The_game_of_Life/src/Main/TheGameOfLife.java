package Main;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class TheGameOfLife {

	public static JFrame frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame = new GameFrame();
			}
		});

	}

}
