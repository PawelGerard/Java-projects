
package Graphics;

import Graphics.InformationPanel;
import Graphics.MainPanel;
import Graphics.TwoPlayersFrame;
import Main.Frame;
import Main.Main;
import Main.SQL;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class BoardPanel extends JPanel {
    URL crossURL = BoardPanel.class.getResource("/cross.png");
    ImageIcon cross = new ImageIcon(crossURL);
    URL circleURL = BoardPanel.class.getResource("/circle.png");
    ImageIcon circle = new ImageIcon(circleURL);
    ActionListener al = new MyActionListener();
    public static int toWin = 5;
    public static int boardSize;
    public static int turn;
    JButton[][] fields;
    JPanel panel;
    int[][] fieldsValues;
    int limit;
    SQL sql;
    String winner;

    public BoardPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 600));
        panel = new JPanel();
        TwoPlayersFrame tpf = new TwoPlayersFrame();
        tpf.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosed(WindowEvent e) {
               add(new InformationPanel(), "North");
               add(panel, "South");
               createBoard();
            }
        });
    }

    public void createBoard() {
        fields = new JButton[boardSize][boardSize];
        fieldsValues = new int[boardSize][boardSize];
        limit = boardSize - toWin;
        panel.setLayout(new GridLayout(boardSize, boardSize));
        panel.setPreferredSize(new Dimension(500, 500));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.white);
        int i = 0;
        while (i < boardSize) {
            int j = 0;
            while (j < boardSize) {
                fields[i][j] = new JButton();
                fields[i][j].setBackground(Color.white);
                panel.add(fields[i][j]);
                fields[i][j].addActionListener(al);
                ++j;
            }
            ++i;
        }
    }

    public void win() {
        sql = new SQL();
        sql.insertGame("Two players", String.valueOf(boardSize) + "x" + boardSize, InformationPanel.player1, InformationPanel.player2, "Victory", winner, Float.parseFloat(InformationPanel.timeCount));
        sql.closeConnection();
        turn = 0;
        int i = JOptionPane.showConfirmDialog(Main.frame, String.valueOf(winner) + " Victory! Time " + Float.parseFloat(InformationPanel.timeCount), "Game is finished", -1, 1);
        closeGame(i);
    }

    public void draw() {
        sql = new SQL();
        sql.insertGame("Two players", String.valueOf(boardSize) + "x" + boardSize, InformationPanel.player1, InformationPanel.player2, "Draw", null, Float.parseFloat(InformationPanel.timeCount));
        sql.closeConnection();
        turn = 0;
        int i = JOptionPane.showConfirmDialog(Main.frame, "Draw! Time " + Float.parseFloat(InformationPanel.timeCount), "Game is finished", -1, 1);
        closeGame(i);
    }

    public void closeGame(int PlayerChoice) {
        if (PlayerChoice == 0) {
            Frame.panel.removeAll();
            Frame.panel.add(new MainPanel());
            Frame.panel.repaint();
            Frame.panel.revalidate();
        }
    }

    public class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton)e.getSource();
            if (turn % 2 == 0) {
                source.setIcon(circle);
                fieldsValues[getX((JButton)source)][getY((JButton)source)] = 1;
            } else {
                source.setIcon(cross);
                fieldsValues[getX((JButton)source)][getY((JButton)source)] = 10;
            }
            ++turn;
            checkResult();
            source.removeActionListener(this);
        }

        public int getX(JButton b) {
            int i = 0;
            while (i < boardSize) {
                int j = 0;
                while (j < boardSize) {
                    if (b.equals(fields[i][j])) {
                        return i;
                    }
                    ++j;
                }
                ++i;
            }
            return -1;
        }

        public int getY(JButton b) {
            int i = 0;
            while (i < boardSize) {
                int j = 0;
                while (j < boardSize) {
                    if (b.equals(fields[i][j])) {
                        return j;
                    }
                    ++j;
                }
                ++i;
            }
            return -1;
        }

        public boolean checkWin() {
            int j;
            int result;
            int possiblitiesToWin = 0;
            int i = 0;
            while (i < boardSize) {
                j = 0;
                while (j <= limit) {
                    result = fieldsValues[i][j] + fieldsValues[i][j + 1] + fieldsValues[i][j + 2] + fieldsValues[i][j + 3] + fieldsValues[i][j + 4];
                    if (result == 5 || result == 50) {
                        colorFields(fields[i][j], fields[i][j + 1], fields[i][j + 2], fields[i][j + 3], fields[i][j + 4]);
                        return true;
                    }
                    if (result < 5 || result > 10 && result % 10 == 0 || result == 0) {
                        ++possiblitiesToWin;
                    }
                    ++j;
                }
                ++i;
            }
            i = 0;
            while (i <= limit) {
                j = 0;
                while (j < boardSize) {
                    result = fieldsValues[i][j] + fieldsValues[i + 1][j] + fieldsValues[i + 2][j] + fieldsValues[i + 3][j] + fieldsValues[i + 4][j];
                    if (result == 5 || result == 50) {
                        colorFields(fields[i][j], fields[i + 1][j], fields[i + 2][j], fields[i + 3][j], fields[i + 4][j]);
                        return true;
                    }
                    if (result < 5 || result > 10 && result % 10 == 0 || result == 0) {
                        ++possiblitiesToWin;
                    }
                    ++j;
                }
                ++i;
            }
            i = 0;
            while (i <= limit) {
                j = 0;
                while (j <= limit) {
                    result = fieldsValues[i][j] + fieldsValues[i + 1][j + 1] + fieldsValues[i + 2][j + 2] + fieldsValues[i + 3][j + 3] + fieldsValues[i + 4][j + 4];
                    if (result == 5 || result == 50) {
                        colorFields(fields[i][j], fields[i + 1][j + 1], fields[i + 2][j + 2], fields[i + 3][j + 3], fields[i + 4][j + 4]);
                        return true;
                    }
                    if (result < 5 || result > 10 && result % 10 == 0 || result == 0) {
                        ++possiblitiesToWin;
                    }
                    ++j;
                }
                ++i;
            }
            i = 0;
            while (i <= limit) {
                j = 4;
                while (j < boardSize) {
                    result = fieldsValues[i][j] + fieldsValues[i + 1][j - 1] + fieldsValues[i + 2][j - 2] + fieldsValues[i + 3][j - 3] + fieldsValues[i + 4][j - 4];
                    if (result == 5 || result == 50) {
                        colorFields(fields[i][j], fields[i + 1][j - 1], fields[i + 2][j - 2], fields[i + 3][j - 3], fields[i + 4][j - 4]);
                        return true;
                    }
                    if (result < 5 || result > 10 && result % 10 == 0 || result == 0) {
                        ++possiblitiesToWin;
                    }
                    ++j;
                }
                ++i;
            }
            if (possiblitiesToWin == 0) {
                draw();
            }
            return false;
        }

        public void checkResult() {
            winner = null;
            if (checkWin()) {
                if (turn % 2 == 1) {
                    winner = InformationPanel.player1;
                } else if (turn % 2 == 0) {
                    winner = InformationPanel.player2;
                }
                win();
            }
        }

        public void colorFields(JButton b1, JButton b2, JButton b3, JButton b4, JButton b5) {
            b1.setBackground(new Color(255, 215, 0));
            b2.setBackground(new Color(255, 215, 0));
            b3.setBackground(new Color(255, 215, 0));
            b4.setBackground(new Color(255, 215, 0));
            b5.setBackground(new Color(255, 215, 0));
        }
    }

}

