
package Graphics;

import Graphics.MainPanel;
import Main.Frame;
import Main.SQL;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatisticsPanel extends JPanel {
    private JLabel label;
    SQL sql;
    JButton button;

    public StatisticsPanel() {
        setLayout(new GridLayout(0, 1, 0, 15));
        sql = new SQL();
        createLabel("Number of games:", String.valueOf(sql.getGameCount()));
        createLabel("Longest game:", String.valueOf(sql.getLongestGame()));
        createLabel("Shorest game:", String.valueOf(sql.getShortestGame()));
        createLabel("Circle's victories:", String.valueOf(sql.getCircleVictories()));
        createLabel("Cross's victories:", String.valueOf(sql.getCrossVictories()));
        createLabel("Best player:", String.valueOf(String.valueOf(sql.getBestPlayer())) + " wins");
        createLabel("Best player aside AI:", String.valueOf(String.valueOf(sql.getBestPlayerAsideAI())) + " wins");
        sql.closeConnection();
        setOpaque(false);
        button = new JButton("Back");
        add(button);
        button.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.panel.removeAll();
                Frame.panel.add(new MainPanel());
                Frame.panel.repaint();
                Frame.panel.revalidate();
            }
        });
        add(button);
    }

    public void createLabel(String desc, String result) {
        label = new JLabel(String.valueOf(desc) + " " + result, 0);
        label.setFont(new Font("SansSerif", 1, 14));
        add(label);
        label.setOpaque(false);
    }

}

