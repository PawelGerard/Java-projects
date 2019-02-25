package Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;



public class Snake extends JPanel{
	//20 different icons for snake moving in each directions
	JLabel[][] label;
	ImageIcon icon1, icon2, icon3, icon4, icon5, icon6, icon7, icon8, icon9, icon10, icon11, icon12, icon13, icon14, icon15, icon16, icon17, icon18, icon19, icon20, iconBorder;
	URL iconBorderURL = Snake.class.getResource("/border.png");
	URL icon1URL = Snake.class.getResource("/1.png");
	URL icon2URL = Snake.class.getResource("/2.png");
	URL icon3URL = Snake.class.getResource("/3.png");
	URL icon4URL = Snake.class.getResource("/4.png");
	URL icon5URL = Snake.class.getResource("/5.png");
	URL icon6URL = Snake.class.getResource("/6.png");
	URL icon7URL = Snake.class.getResource("/7.png");
	URL icon8URL = Snake.class.getResource("/8.png");
	URL icon9URL = Snake.class.getResource("/9.png");
	URL icon10URL = Snake.class.getResource("/10.png");
	URL icon11URL = Snake.class.getResource("/11.png");
	URL icon12URL = Snake.class.getResource("/12.png");
	URL icon13URL = Snake.class.getResource("/13.png");
	URL icon14URL = Snake.class.getResource("/14.png");
	URL icon15URL = Snake.class.getResource("/15.png");
	URL icon16URL = Snake.class.getResource("/16.png");
	URL icon17URL = Snake.class.getResource("/17.png");
	URL icon18URL = Snake.class.getResource("/18.png");
	URL icon19URL = Snake.class.getResource("/19.png");
	URL icon20URL = Snake.class.getResource("/20.png");
	
	
	
	public Snake() {
		//Loading icons from URLs
		iconBorder = new ImageIcon(iconBorderURL);
		icon1 = new ImageIcon(icon1URL);
		icon2 = new ImageIcon(icon2URL);
		icon3 = new ImageIcon(icon3URL);
		icon4 = new ImageIcon(icon4URL);
		icon5 = new ImageIcon(icon5URL);
		icon6 = new ImageIcon(icon6URL);
		icon7 = new ImageIcon(icon7URL);
		icon8 = new ImageIcon(icon8URL);
		icon9 = new ImageIcon(icon9URL);
		icon10 = new ImageIcon(icon10URL);
		icon11 = new ImageIcon(icon11URL);
		icon12 = new ImageIcon(icon12URL);
		icon13 = new ImageIcon(icon13URL);
		icon14 = new ImageIcon(icon14URL);
		icon15 = new ImageIcon(icon15URL);
		icon16 = new ImageIcon(icon16URL);
		icon17 = new ImageIcon(icon17URL);
		icon18 = new ImageIcon(icon18URL);
		icon19 = new ImageIcon(icon19URL);
		icon20 = new ImageIcon(icon20URL);
		
		
		//Board is 20x20
		setLayout(new GridLayout(20,20));
		setPreferredSize(new Dimension(420, 420));
		setBackground(Color.white);
		setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, iconBorder));
		
		label = new JLabel[20][20];
		//Starting point with no snake painted
		for (int i =0; i<20;i++) {
			for (int j = 0;j<20;j++) {
				label[i][j] = new JLabel(icon7);
				add(label[i][j]);
			}
		}
	}
	//Painting rest of board, basically all beside snake and apple
	public void paintRest(List<Coordinates> snake, Coordinates apple) {
		for (int i =0; i<20;i++) {
			for (int j = 0;j<20;j++) {
				if (!new Coordinates(i,j).equals(apple) && !snake.contains(new Coordinates(i,j)))
				label[i][j].setIcon(icon7);
			}
		}
	}
	//Painting snake with given list of elements and direction of snake's move
	//Each element from a list is one element of a snake
	public void paintSnake(List<Coordinates> snake, String direction) {
		int size = snake.size();
		
		for (int i = 0;i<size;i++) {
		//4 different head icons depending on snake's direction
		if (i == size-1) {
			if (direction.equals("left"))
				label[snake.get(i).getxCord()][snake.get(i).getyCord()].setIcon(icon9);
				else if (direction.equals("up"))
				label[snake.get(i).getxCord()][snake.get(i).getyCord()].setIcon(icon4);
				else if (direction.equals("right"))
				label[snake.get(i).getxCord()][snake.get(i).getyCord()].setIcon(icon5);
				else if (direction.equals("down"))
				label[snake.get(i).getxCord()][snake.get(i).getyCord()].setIcon(icon10);
		}
		//4 different tail icons depending on snake's direction
		else if (i == 0) {
			int xDiff = snake.get(i).getxCord()-snake.get(i+1).getxCord();
			int yDiff = snake.get(i).getyCord()-snake.get(i+1).getyCord();
				if (xDiff==0 && yDiff==1)
				label[snake.get(i).getxCord()][snake.get(i).getyCord()].setIcon(icon19);
				else if (xDiff==0 && yDiff==-1)
				label[snake.get(i).getxCord()][snake.get(i).getyCord()].setIcon(icon15);
				else if (xDiff==1 && yDiff==0)
				label[snake.get(i).getxCord()][snake.get(i).getyCord()].setIcon(icon14);
				else if (xDiff==-1 && yDiff==0)
				label[snake.get(i).getxCord()][snake.get(i).getyCord()].setIcon(icon20);
		}
		//Rest of snake included here
		else {
			int xDiffNext = snake.get(i).getxCord()-snake.get(i+1).getxCord();
			int xDiffPrev = snake.get(i).getxCord()-snake.get(i-1).getxCord();
			int yDiffNext = snake.get(i).getyCord()-snake.get(i+1).getyCord();
			int yDiffPrev = snake.get(i).getyCord()-snake.get(i-1).getyCord();
			//Choosing the proper icon for snake's body
			//Elements needed to make turns realistic are based on next and previous elements from snake's body
			if (yDiffNext==1 && yDiffPrev==-1)
			label[snake.get(i).getxCord()][snake.get(i).getyCord()].setIcon(icon2);
			else if (yDiffNext==-1 && yDiffPrev==1)
			label[snake.get(i).getxCord()][snake.get(i).getyCord()].setIcon(icon2);
			else if (xDiffNext==1 && xDiffPrev==-1)
			label[snake.get(i).getxCord()][snake.get(i).getyCord()].setIcon(icon8);
			else if (xDiffNext==-1 && xDiffPrev==1)
			label[snake.get(i).getxCord()][snake.get(i).getyCord()].setIcon(icon8);
			else if ((xDiffNext==-1 && yDiffPrev==1) || (xDiffPrev ==-1 && yDiffNext==1))
			label[snake.get(i).getxCord()][snake.get(i).getyCord()].setIcon(icon3);
			else if ((xDiffPrev==1 && yDiffNext==-1) || (xDiffNext==1 && yDiffPrev==-1))
			label[snake.get(i).getxCord()][snake.get(i).getyCord()].setIcon(icon6);
			else if ((xDiffNext==1 && yDiffPrev==1) || (xDiffPrev==1 && yDiffNext==1))
			label[snake.get(i).getxCord()][snake.get(i).getyCord()].setIcon(icon13);
			else if ((xDiffNext==-1 && yDiffPrev==-1) || (xDiffPrev==-1 && yDiffNext==-1))
			label[snake.get(i).getxCord()][snake.get(i).getyCord()].setIcon(icon1);
		}	
		}
	}
	//Painting apple (snake's body excluded)
	//Shuffling all possibilities and taking first
	public Coordinates getAndPaintApple(List<Coordinates> snake) {
	
		List<Coordinates> allSlots = new ArrayList<>();
		for (int i =0; i<20;i++) {
			for (int j = 0;j<20;j++) {
				allSlots.add(new Coordinates(i, j));
			}
		}
		allSlots.removeAll(snake);
		Collections.shuffle(allSlots);
		label[allSlots.get(0).getxCord()][allSlots.get(0).getyCord()].setIcon(icon16);
		return allSlots.get(0);
	}
	
}
