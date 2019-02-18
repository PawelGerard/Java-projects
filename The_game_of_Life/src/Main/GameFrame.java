package Main;

import javax.swing.JFrame;

public class GameFrame extends JFrame{

	GamePanel myPanel = new GamePanel(); 

	public GameFrame() {
		super("The Game of Life");
		setResizable(false);
		setLocation(350, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		add(myPanel);
		pack();
	}
}
