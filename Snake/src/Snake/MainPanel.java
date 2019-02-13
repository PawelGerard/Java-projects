package Snake;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class MainPanel extends JPanel implements KeyListener {

	
	String lastKey;
	int x, y, count;
	Snake snake;
	List<Coordinates> list;
	Coordinates head;
	int[] previousValues;
	Coordinates apple;
	JLabel time;
	JLabel score;
	
	public MainPanel() {
		
		setPreferredSize(new Dimension(420, 470));
		setLayout(new BorderLayout());
		add(new InfoPanel(), BorderLayout.NORTH);
		
		
		snake = new Snake();
		add(snake, BorderLayout.SOUTH);
		previousValues = new int[2];
		
		x = 10;
		y = 10;
		previousValues[0] = x;
		previousValues[1] = y;
		
		list = new ArrayList<>();
		list.add(new Coordinates(10,8));
		list.add(new Coordinates(10,9));
		list.add(new Coordinates(10,10));
		
		lastKey = "right";
		apple = snake.getAndPaintApple(list);
		
		Timer t = new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			count++;
			int result = list.size()*10;
			time.setText(String.valueOf((count/2)/10.0));
			score.setText(result + " pts!");
			if (count % 5 == 0 ) {
				
				if (lastKey.equals("left")) 
				y--;
				else if (lastKey.equals("up"))
				--x;
				else if (lastKey.equals("right")) 
				y++;
				else if (lastKey.equals("down"))
				++x;
				
				if (x>19 || x<0 || y<0 || y>19) gameOver();
				else {
					head = new Coordinates(x,y);
					if (list.contains(head)) {
						list.add(head);
						snake.paintSnake(list, lastKey);
						snake.paintRest(list, apple);
						gameOver();}
					list.add(head);
					snake.paintSnake(list, lastKey);
					snake.paintRest(list, apple);
					if(!list.contains(apple)) list.remove(0);
					else apple = snake.getAndPaintApple(list);
				}
			}
			}
		});
		t.start();
		addKeyListener(this);
		setFocusable(true);
		requestFocus();
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		if (e.getExtendedKeyCode()==37 && previousValues[0]!=x)
		lastKey = "left";
		else if (e.getExtendedKeyCode()==38 && previousValues[1]!=y)
		lastKey = "up";
		else if (e.getExtendedKeyCode()==39 && previousValues[0]!=x)
		lastKey = "right";
		else if (e.getExtendedKeyCode()==40 && previousValues[1]!=y)
		lastKey = "down";
		
		previousValues[0] = x;
		previousValues[1] = y;
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void gameOver() {
	if (0==JOptionPane.showConfirmDialog(Main.frame, "Game Over","The End",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE))
	System.exit(0);
	}
	
	private class InfoPanel extends JPanel {
		
		InfoPanel() {
		
		setPreferredSize(new Dimension(420, 50));
		
		JPanel timePanel = new JPanel();
		time = new JLabel();
		timePanel.add(time, SwingConstants.CENTER);
		
		JPanel scorePanel = new JPanel();
		score = new JLabel();
		scorePanel.add(score, SwingConstants.CENTER);
		
		add(timePanel);
		add(scorePanel);
		}
	}
	
}
