package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel {
	
	private int boardSize;
	JPanel boardPanel;
	JButton[][] button;
	JLabel boardSizeLabel;
	JLabel numberOfCycles;
	Timer t;
	Set<JButton> activeCells;
	boolean running;
	
	
	public GamePanel() {
		//Creating Main Panel
		setBoardSize(10);
		setPreferredSize(new Dimension(500,350));
		setLayout(new BorderLayout());
		activeCells = new HashSet<>();
		
		//Creating Board Panel
		boardPanel = new JPanel();
		boardPanel.setPreferredSize(new Dimension(350,350));
		createBoard();
		add(boardPanel, BorderLayout.WEST);
		
		//Creating Navigation Panel
		NavPanel navPanel = new NavPanel();
		add(navPanel, BorderLayout.EAST);
				
		//Timer with ActionListener to change cycles of life
		t = new Timer(1000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		getnextCycle();
		}
		});
		//Showing introduction message 
		JOptionPane.showConfirmDialog(TheGameOfLife.frame, "Hello. This is Conway's Game of Life simulator. Press ok, select your cells and you are ready to start :)", "Conway's Game of Life", JOptionPane.PLAIN_MESSAGE);

	}

	public int getBoardSize() {
		return boardSize;
	}

	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}

	public void createBoard() {
		//Board with columns and rows equal to boardSize field
		button = new JButton[boardSize][boardSize];
		boardPanel.setLayout(new GridLayout(boardSize, boardSize));
		for (int i = 0; i<boardSize;i++) {
			for (int j = 0; j<boardSize;j++) {
				button[i][j] = new JButton();
				button[i][j].setBackground(Color.red);
				//ActionListener to choose cells for start
				ActionListener listener = new StartListener(i,j);
				button[i][j].addActionListener(listener);
				boardPanel.add(button[i][j]);
			}
		}
	}
	
	public void getnextCycle() {
		Map<JButton, Integer> neighboursAlive = new HashMap<>();
		for (int i = 0; i<boardSize;i++) {
			for (int j = 0; j<boardSize;j++) {
				int count = 0;
				//Checking neighbour cells to check if they are alive. Using exceptions for cells, which don't have 8 cells around
				try {if (button[i-1][j-1].getBackground().equals(Color.green)) count++;} catch(ArrayIndexOutOfBoundsException e) {}
				try {if (button[i-1][j].getBackground().equals(Color.green)) count++;} catch(ArrayIndexOutOfBoundsException e) {}
				try {if (button[i-1][j+1].getBackground().equals(Color.green)) count++;} catch(ArrayIndexOutOfBoundsException e) {}
				try {if (button[i][j-1].getBackground().equals(Color.green)) count++;} catch(ArrayIndexOutOfBoundsException e) {}
				try {if (button[i+1][j+1].getBackground().equals(Color.green)) count++;} catch(ArrayIndexOutOfBoundsException e) {}
				try {if (button[i][j+1].getBackground().equals(Color.green)) count++;} catch(ArrayIndexOutOfBoundsException e) {}
				try {if (button[i+1][j-1].getBackground().equals(Color.green)) count++;} catch(ArrayIndexOutOfBoundsException e) {}
				try {if (button[i+1][j].getBackground().equals(Color.green)) count++;} catch(ArrayIndexOutOfBoundsException e) {}
				//adding 100 to cells, which are alive
				if (button[i][j].getBackground().equals(Color.green)) count+=100;
				neighboursAlive.put(button[i][j], count);
			}
		} 
		//if cell is alive and has 2 or 3 neighbour cells, which are alive the cell will live in next cycle
		//if cell is dead and has 3 neighbour cells, which are alive the cell will live in next cycle
		for (Map.Entry<JButton, Integer> x:neighboursAlive.entrySet()) {
			if (x.getValue()==102 || x.getValue()==103) x.getKey().setBackground(Color.green);
			else if (x.getValue()==3) x.getKey().setBackground(Color.green);
			else x.getKey().setBackground(Color.red);
		}
	}
	private class NavPanel extends JPanel {
		
		JButton getBiggerBoard, getSmallerBoard;
		ActionListener listenerBigger, listenerSmaller;
		JLabel infoLabel;
		
		NavPanel() {
		setPreferredSize(new Dimension(150, 350));
		setLayout(new GridLayout(0,1,20,20));
		
		//Button to extend size of board
		getBiggerBoard = new JButton("Size +1");
		add(getBiggerBoard);
		listenerBigger = new SizeListener(1);
		getBiggerBoard.addActionListener(listenerBigger);
		
		//Button to reduce size of board
		getSmallerBoard = new JButton("Size -1");
		add(getSmallerBoard);
		listenerSmaller = new SizeListener(-1);
		getSmallerBoard.addActionListener(listenerSmaller);
		
		//Label with board size
		boardSizeLabel = new JLabel();
		boardSizeLabel.setText("Size of board: " + boardSize);
		add(boardSizeLabel);
		
		//Information Label for proper start 
		infoLabel = new JLabel("Choose your cells to start!");
		infoLabel.setForeground(Color.red);
		add(infoLabel);
		
		//Button to start timer. While running changes are not allowed.
		JButton startButton = new JButton("Start!");
		add(startButton);
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				t.start();
				getBiggerBoard.removeActionListener(listenerBigger);
				getSmallerBoard.removeActionListener(listenerSmaller);
				infoLabel.setText("Running...");
			}
		});
		//Button to stop timer. If app is stopped size changes can be made.
		JButton stopButton = new JButton("Stop!");
		add(stopButton);
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			t.stop();
			getBiggerBoard.addActionListener(listenerBigger);
			getSmallerBoard.addActionListener(listenerSmaller);
			infoLabel.setText("Choose your cells to start!");
			}
		});
		}
	}
	
	private class SizeListener implements ActionListener {
		//ActionListener to extend or reduce (direction) board
		private int direction;
		
		SizeListener(int direction) {
		this.direction = direction;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			//If board size is not too small or too big changes are applied
			boardSize = boardSize + direction;
			if (boardSize>=1 && boardSize<=50) {
			boardPanel.removeAll();
			createBoard();
			boardPanel.repaint();
			boardPanel.revalidate();
			boardSizeLabel.setText("Size of board: " + boardSize);
			}
			else boardSize = boardSize - direction;
		}
	}
	
	private class StartListener implements ActionListener {
		//ActionListener to choose cells to start with
		int x, y;
		
		StartListener(int x, int y) {
			this.x=x;
			this.y=y;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if (button[x][y].getBackground().equals(Color.green)) button[x][y].setBackground(Color.red);
			else button[x][y].setBackground(Color.green);
		}
	}
		
}
