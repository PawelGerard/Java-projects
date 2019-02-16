
package Graphics;

import Graphics.BoardPanel;
import Graphics.InformationPanel;
import Main.Frame;
import Main.Main;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class TwoPlayersFrame extends JFrame {
    JPanel panel = new JPanel();
    JLabel p1l = new JLabel("Name of first player:");
    JLabel p2l = new JLabel("Name of second player:");
    JLabel sl = new JLabel("Size of board:");
    JTextField p1t = new JTextField();
    JTextField p2t = new JTextField();
    ButtonGroup bg;
    JRadioButton r1;
    JRadioButton r2;
    JRadioButton r3;
    JButton ok;
    

    public TwoPlayersFrame() {
        super("Enter details");
        panel.setLayout(new GridLayout(0, 1));
        panel.add(p1l);
        panel.add(p1t);
        panel.add(p2l);
        panel.add(p2t);
        panel.add(sl);
        r1 = new JRadioButton("10x10");
        r1.setActionCommand("10");
        r1.setSelected(true);
        r2 = new JRadioButton("12x12");
        r2.setActionCommand("12");
        r3 = new JRadioButton("15x15");
        r3.setActionCommand("15");
        bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);
        panel.add(r1);
        panel.add(r2);
        panel.add(r3);
        ok = new JButton("ok");
        ok.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                BoardPanel.boardSize = Integer.parseInt(bg.getSelection().getActionCommand());
                InformationPanel.player1 = p1t.getText();
                InformationPanel.player2 = p2t.getText();
                dispose();
            }
        });
        panel.add(ok);
        add(panel);
        setDefaultCloseOperation(0);
        setPreferredSize(new Dimension(300, 300));
        pack();
        setLocationRelativeTo(Main.frame);
        setVisible(true);
    }

}

