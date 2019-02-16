
package Graphics;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
    JButton button;
    static JPanel buttonPanel;
    BufferedImage mainBG;
    URL bgURL = MainPanel.class.getResource("/mainbg.png");
    boolean shouldDrawBG = true;

    public MainPanel() {
        try {
            mainBG = ImageIO.read(bgURL);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        setPreferredSize(new Dimension(500, 600));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1, 25, 40));
        buttonPanel.setPreferredSize(new Dimension(200, 500));
        buttonPanel.setOpaque(false);
        createButton("One Player", "Graphics.BoardPanelAI");
        createButton("Two Players", "Graphics.BoardPanel");
        createButton("History", "Graphics.HistoryPanel");
        createButton("Statistics", "Graphics.StatisticsPanel");
        createButton("Exit", "Exit");
        add(buttonPanel, 0);
    }

    public void createButton(String name, String panelName) {
        button = new JButton(name);
        buttonPanel.add(button);
        button.addActionListener(new MyListener(panelName));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (shouldDrawBG) {
            g.drawImage(mainBG, 0, 0, null);
        }
    }

    public class MyListener
    implements ActionListener {
        public String panelName;
        JPanel panel;

        public MyListener(String panelName) {
            this.panelName = panelName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!panelName.equals("Exit")) {
                try {
                    panel = (JPanel)Class.forName(panelName).newInstance();
                }
                catch (ClassNotFoundException | IllegalAccessException | InstantiationException e1) {
                    e1.printStackTrace();
                }
                shouldDrawBG = panelName.equals("Graphics.HistoryPanel") || panelName.equals("Graphics.StatisticsPanel");
                removeAll();
                add(panel);
                repaint();
                revalidate();
            } else {
                System.exit(0);
            }
        }
    }

}

