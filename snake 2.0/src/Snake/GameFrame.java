package Snake;

import javax.swing.JFrame;

public class GameFrame extends JFrame{

	
	public GameFrame() {
		super("Snake");
		add(new MainPanel());
		setLocation(200,200);
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
