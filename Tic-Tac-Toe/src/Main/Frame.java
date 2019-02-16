
package Main;

import Graphics.MainPanel;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame {
    public static JPanel panel;

    public Frame() {
        super("Tic-Tac-Toe");
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(500, 600));
        panel.add(new MainPanel());
        add(panel);
        pack();
        setResizable(false);
        setLocation(750, 50);
        setDefaultCloseOperation(3);
        setVisible(true);
    }
}

