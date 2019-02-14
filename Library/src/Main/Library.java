package Main;

import java.awt.EventQueue;

import Graphics.Frame;

public class Library {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Frame();
			}
		});
	}

}
