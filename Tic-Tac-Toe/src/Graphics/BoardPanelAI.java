
package Graphics;

import Graphics.AIPossibilities;
import Graphics.AIPossibilitiesInString;
import Graphics.InformationPanelAI;
import Graphics.MainPanel;
import Graphics.OnePlayerFrame;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

public class BoardPanelAI extends JPanel {
    URL crossURL = BoardPanelAI.class.getResource("/cross.png");
    ImageIcon cross = new ImageIcon(crossURL);
    URL circleURL = BoardPanelAI.class.getResource("/circle.png");
    ImageIcon circle = new ImageIcon(circleURL);
    ActionListener al = new MyActionListener();
    public static int toWin = 5;
    public static int boardSize;
    public static int turn;
    JButton[][] fields;
    static boolean firstPlayer;
    int[][] fieldsValues;
    int limit;
    List<AIPossibilities> list;
    List<AIPossibilitiesInString> list2;
    boolean win;
    Boolean wait;
    List<JButton> toRemoveList;
    String winner = null;
    SQL sql;
    JPanel panel;

    static {
        firstPlayer = true;
    }

    public BoardPanelAI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 600));
        panel = new JPanel();
        OnePlayerFrame opf = new OnePlayerFrame();
        opf.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosed(WindowEvent e) {
                add((Component)new InformationPanelAI(), "North");
                add((Component)panel, "South");
                createBoard();
            }
        });
    }

    public void createBoard() {
        win = false;
        list = new ArrayList<AIPossibilities>();
        list2 = new ArrayList<AIPossibilitiesInString>();
        toRemoveList = new ArrayList<JButton>();
        limit = boardSize - toWin;
        fields = new JButton[boardSize][boardSize];
        fieldsValues = new int[boardSize][boardSize];
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
        compFirstMove();
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
        if (checkWin()) {
            if (turn % 2 == 1) {
                winner = InformationPanelAI.player1;
            } else if (turn % 2 == 0) {
                winner = InformationPanelAI.player2;
            }
            win = true;
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

    public void compFirstMove() {
        if (turn == 0 && firstPlayer) {
            int firstPoint = boardSize / 2;
            fields[firstPoint][firstPoint].setIcon(circle);
            toRemoveList.add(fields[firstPoint][firstPoint]);
            fieldsValues[firstPoint][firstPoint] = 1;
            ++turn;
        }
    }

    public void compMove() {
        if (turn == 1) {
            int firstPoint = boardSize / 2;
            if (fieldsValues[firstPoint][firstPoint] != 0) {
                firstPoint = boardSize / 2 - 1;
            }
            fields[firstPoint][firstPoint].setIcon(cross);
            toRemoveList.add(fields[firstPoint][firstPoint]);
            fieldsValues[firstPoint][firstPoint] = 10;
            ++turn;
        } else if (turn % 2 == 0) {
            int[] xAndY = defenceAndMove();
            int x = xAndY[0];
            int y = xAndY[1];
            fields[x][y].setIcon(circle);
            toRemoveList.add(fields[x][y]);
            fieldsValues[x][y] = 1;
            ++turn;
        } else if (turn % 2 == 1) {
            int[] xAndY = defenceAndMove();
            int x = xAndY[0];
            int y = xAndY[1];
            fields[x][y].setIcon(cross);
            toRemoveList.add(fields[x][y]);
            fieldsValues[x][y] = 10;
            ++turn;
        }
    }

    public void createListOfStrings() {
        int j;
        int result;
        list.clear();
        int i = 0;
        while (i < boardSize) {
            j = 0;
            while (j <= limit) {
                result = fieldsValues[i][j] + fieldsValues[i][j + 1] + fieldsValues[i][j + 2] + fieldsValues[i][j + 3] + fieldsValues[i][j + 4];
                list.add(new AIPossibilities(i, j, 0, 1, result % 10, result / 10, fieldsValues[i][j] + fieldsValues[i][j + 4]));
                ++j;
            }
            ++i;
        }
        i = 0;
        while (i <= limit) {
            j = 0;
            while (j < boardSize) {
                result = fieldsValues[i][j] + fieldsValues[i + 1][j] + fieldsValues[i + 2][j] + fieldsValues[i + 3][j] + fieldsValues[i + 4][j];
                list.add(new AIPossibilities(i, j, 1, 0, result % 10, result / 10, fieldsValues[i][j] + fieldsValues[i + 4][j]));
                ++j;
            }
            ++i;
        }
        i = 0;
        while (i <= limit) {
            j = 0;
            while (j <= limit) {
                result = fieldsValues[i][j] + fieldsValues[i + 1][j + 1] + fieldsValues[i + 2][j + 2] + fieldsValues[i + 3][j + 3] + fieldsValues[i + 4][j + 4];
                list.add(new AIPossibilities(i, j, 1, 1, result % 10, result / 10, fieldsValues[i][j] + fieldsValues[i + 4][j + 4]));
                ++j;
            }
            ++i;
        }
        i = 0;
        while (i <= limit) {
            j = 4;
            while (j < boardSize) {
                result = fieldsValues[i][j] + fieldsValues[i + 1][j - 1] + fieldsValues[i + 2][j - 2] + fieldsValues[i + 3][j - 3] + fieldsValues[i + 4][j - 4];
                list.add(new AIPossibilities(i, j, 1, -1, result % 10, result / 10, fieldsValues[i][j] + fieldsValues[i + 4][j - 4]));
                ++j;
            }
            ++i;
        }
    }

    public void sortForAttack() {
        Collections.sort(list, new Comparator<AIPossibilities>(){

            @Override
            public int compare(AIPossibilities ai1, AIPossibilities ai2) {
                int x;
                if (firstPlayer) {
                    x = ai1.getCrossAmount() - ai2.getCrossAmount();
                    if (x == 0 && (x = ai2.getCircleAmount() - ai1.getCircleAmount()) == 0) {
                        x = ai1.getFirstSumLast() - ai2.getFirstSumLast();
                    }
                } else {
                    x = ai1.getCircleAmount() - ai2.getCircleAmount();
                    if (x == 0 && (x = ai2.getCrossAmount() - ai1.getCrossAmount()) == 0) {
                        x = ai1.getFirstSumLast() - ai2.getFirstSumLast();
                    }
                }
                return x;
            }
        });
        Iterator<AIPossibilities> iter = list.iterator();
        while (iter.hasNext()) {
            if (iter.next().getAvailableFields() != 0) continue;
            iter.remove();
        }
    }

    public int[] GetBestAttackOption() {
        sortForAttack();
        return getBestOptionInString(list.get(0).getFirstX(), list.get(0).getFirstY(), list.get(0).getxDirection(), list.get(0).getyDirection());
    }

    public int[] getBestOptionInString(int x, int y, int xDirection, int yDirection) {
        list2.clear();
        int searchFor = firstPlayer ? 1 : 10;
        int i = 0;
        while (i < 5) {
            if (fieldsValues[x + i * xDirection][y + i * yDirection] == 0) {
                int n;
                if (i == 0) {
                    n = fieldsValues[x + (i + 1) * xDirection][y + (i + 1) * yDirection] / searchFor;
                    list2.add(new AIPossibilitiesInString(x + i * xDirection, y + i * yDirection, n));
                } else if (i > 0 && i < 4) {
                    n = fieldsValues[x + (i + 1) * xDirection][y + (i + 1) * yDirection] / searchFor + fieldsValues[x + (i - 1) * xDirection][y + (i - 1) * yDirection] / searchFor;
                    list2.add(new AIPossibilitiesInString(x + i * xDirection, y + i * yDirection, n));
                } else if (i == 4) {
                    n = fieldsValues[x + (i - 1) * xDirection][y + (i - 1) * yDirection] / searchFor;
                    list2.add(new AIPossibilitiesInString(x + i * xDirection, y + i * yDirection, n));
                }
            }
            ++i;
        }
        Collections.sort(list2, new Comparator<AIPossibilitiesInString>(){

            @Override
            public int compare(AIPossibilitiesInString ai1, AIPossibilitiesInString ai2) {
                int x = ai2.getWeight() - ai1.getWeight();
                return x;
            }
        });
        int[] result = new int[]{list2.get(0).getX(), list2.get(0).getY()};
        return result;
    }

    public int[] defenceAndMove() {
        createListOfStrings();
        sortForAttack();
        if (list.get(0).getCircleAmount() == 4 || list.get(0).getCrossAmount() == 4) {
            return getBestOptionInString(list.get(0).getFirstX(), list.get(0).getFirstY(), list.get(0).getxDirection(), list.get(0).getyDirection());
        }
        Collections.sort(list, new Comparator<AIPossibilities>(){

            @Override
            public int compare(AIPossibilities ai1, AIPossibilities ai2) {
                int x;
                if (firstPlayer) {
                    x = ai1.getCircleAmount() - ai2.getCircleAmount();
                    if (x == 0) {
                        x = ai2.getCrossAmount() - ai1.getCrossAmount();
                    }
                } else {
                    x = ai1.getCrossAmount() - ai2.getCrossAmount();
                    if (x == 0) {
                        x = ai2.getCircleAmount() - ai1.getCircleAmount();
                    }
                }
                return x;
            }
        });
        if (list.get(0).getCircleAmount() > 2 && list.get(0).getCrossAmount() == 0 || list.get(0).getCrossAmount() > 2 && list.get(0).getCircleAmount() == 0) {
            return getBestOptionInString(list.get(0).getFirstX(), list.get(0).getFirstY(), list.get(0).getxDirection(), list.get(0).getyDirection());
        }
        return GetBestAttackOption();
    }

    public void MovewithWaitAI() {
        Timer t = new Timer(1, new ActionListener(){
            int count;

            @Override
            public void actionPerformed(ActionEvent e) {
                ++count;
                if (count == 1) {
                    addActionListener(false);
                }
                if (count == 100) {
                    compMove();
                    ((Timer)e.getSource()).stop();
                    checkResult();
                    addActionListener(true);
                }
            }
        });
        t.start();
    }

    public void addActionListener(boolean TrueForAddFalseForRemove) {
        int i = 0;
        while (i < boardSize) {
            int j = 0;
            while (j < boardSize) {
                if (TrueForAddFalseForRemove) {
                    if (!toRemoveList.contains(fields[i][j])) {
                        fields[i][j].addActionListener(al);
                    }
                } else {
                    fields[i][j].removeActionListener(al);
                }
                ++j;
            }
            ++i;
        }
    }

    public void win() {
        sql = new SQL();
        sql.insertGame("One player", String.valueOf(boardSize) + "x" + boardSize, InformationPanelAI.player1, InformationPanelAI.player2, "Victory", winner, Float.parseFloat(InformationPanelAI.timeCount));
        sql.closeConnection();
        turn = 0;
        int i = JOptionPane.showConfirmDialog(Main.frame, String.valueOf(winner) + " Victory! Time " + Float.parseFloat(InformationPanelAI.timeCount), "Game is finished", -1, 1);
        closeGame(i);
    }

    public void draw() {
        sql = new SQL();
        sql.insertGame("One player", String.valueOf(boardSize) + "x" + boardSize, InformationPanelAI.player1, InformationPanelAI.player2, "Draw", null, Float.parseFloat(InformationPanelAI.timeCount));
        sql.closeConnection();
        turn = 0;
        int i = JOptionPane.showConfirmDialog(Main.frame, "Draw! Time " + Float.parseFloat(InformationPanelAI.timeCount), "Game is finished", -1, 1);
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

    public class MyActionListener
    implements ActionListener {
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
            toRemoveList.add(source);
            if (!win) {
                MovewithWaitAI();
            }
        }
    }

}

