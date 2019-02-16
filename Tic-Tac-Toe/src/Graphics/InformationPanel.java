
package Graphics;

import Graphics.BoardPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class InformationPanel extends JPanel {
    JLabel timeInfo;
    JLabel playerInfo;
    double count;
    static String timeCount;
    JPanel timePanel;
    JPanel playerPanel;
    Image bg;
    URL bgURL = InformationPanel.class.getResource("/bg.png");
    public static String player1;
    public static String player2;

    public InformationPanel() {
    	count = 0;
        try {
            bg = ImageIO.read(bgURL);
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        setPreferredSize(new Dimension(500, 100));
        timePanel = new JPanel();
        timePanel.setLayout(new FlowLayout());
        timePanel.setPreferredSize(new Dimension(500, 50));
        timePanel.setOpaque(false);
        timeInfo = new JLabel("0.0");
        timeInfo.setFont(new Font("SansSerif", 3, 18));
        timeInfo.setForeground(Color.blue);
        timePanel.add((Component)timeInfo, 0);
        playerPanel = new JPanel();
        playerPanel.setLayout(new FlowLayout());
        playerPanel.setPreferredSize(new Dimension(500, 50));
        playerPanel.setOpaque(false);
        playerInfo = new JLabel();
        playerInfo.setFont(new Font("Impact", 0, 18));
        playerPanel.add((Component)playerInfo, 0);
        add(timePanel);
        add(playerPanel);
        Timer t = new Timer(100, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                count += 1.0;
                timeCount = String.valueOf((double)Math.round(count * 10.0) / 100.0);
                timeInfo.setText(timeCount);
                changePlayer(BoardPanel.turn);
            }
        });
        t.start();
    }

    public void changePlayer(int turnInfo) {
        if (turnInfo % 2 == 0) {
            playerInfo.setText(String.valueOf(player1) + "'s turn");
            playerInfo.setForeground(new Color(70, 180, 20));
        } else {
            playerInfo.setText(String.valueOf(player2) + "'s turn");
            playerInfo.setForeground(new Color(200, 10, 10));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, null);
    }

}

