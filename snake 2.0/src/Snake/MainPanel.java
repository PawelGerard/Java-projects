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
		
		//Adding new snake, consisting of 20x20 labels
		//Snake panel is on bottom, while info panel is on top
		snake = new Snake();
		add(snake, BorderLayout.SOUTH);
		previousValues = new int[2];
		//Coordinates with head of snake
		x = 10;
		y = 10;
		previousValues[0] = x;
		previousValues[1] = y;
		//Every element of the list is snake piece. Beginning with 3 elements.
		list = new ArrayList<>();
		list.add(new Coordinates(10,8));
		list.add(new Coordinates(10,9));
		list.add(new Coordinates(10,10));
		//Snake will move right at the beginning. 
		lastKey = "right";
		//Painting apple and returning coordinates of it
		apple = snake.getAndPaintApple(list);
		//Timer with 20 actions per sec
		//Each action may change text of info labels and snake position 
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
				//If snake is outside board game will end
				if (x>19 || x<0 || y<0 || y>19) gameOver();
				else {
					//In every action snake's head is repainted
					head = new Coordinates(x,y);
					//If snake's head already exist in body this means he "bait" himself, which means game is over
					if (list.contains(head)) {
						list.add(head);
						snake.paintSnake(list, lastKey);
						snake.paintRest(list, apple);
						gameOver();}
					//If everything is ok game continues 
					list.add(head);
					snake.paintSnake(list, lastKey);
					snake.paintRest(list, apple);
					//If snake didn't catch apple one part of him is removed. As new head is added the size remains the same
					if(!list.contains(apple)) list.remove(0);
					//If snake catched apple new one need to be painted
					else apple = snake.getAndPaintApple(list);
				}
			}
			}
		});
		//Staring timer
		//Focus is required due to key listeners
		t.start();
		addKeyListener(this);
		setFocusable(true);
		requestFocus();
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		//Defining direction depending on arrows pressed
		//Cannot move in opposite direction if the adequate coordinate didn't change
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
		//Informing about lose
	if (0==JOptionPane.showConfirmDialog(Main.frame, "Game Over","The End",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE))
	System.exit(0);
	}
	
	private class InfoPanel extends JPanel {
		//Info panel with two labels: one for time, on for points
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
