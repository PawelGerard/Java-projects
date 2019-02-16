
package Graphics;

import Graphics.BoardPanelAI;
import Graphics.InformationPanelAI;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class OnePlayerFrame extends JFrame {
    JPanel panel = new JPanel();
    JLabel p1l = new JLabel("Name of player:");
    JLabel sign = new JLabel("Your sign:");
    JLabel sl = new JLabel("Size of board:");
    JTextField p1t = new JTextField();
    ButtonGroup bg;
    JRadioButton r1;
    JRadioButton r2;
    JRadioButton r3;
    JButton ok;
    JComboBox<String> cb;
    String[] signs = new String[]{"Circle", "Cross"};

    public OnePlayerFrame() {
        super("Enter details");
        panel.setLayout(new GridLayout(0, 1));
        panel.add(p1l);
        panel.add(p1t);
        cb = new JComboBox<String>(signs);
        panel.add(sign);
        panel.add(cb);
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
                BoardPanelAI.boardSize = Integer.parseInt(bg.getSelection().getActionCommand());
                if (cb.getSelectedIndex() == 0) {
                    InformationPanelAI.player1 = p1t.getText();
                    InformationPanelAI.player2 = "AI";
                    BoardPanelAI.firstPlayer = false;
                } else {
                    InformationPanelAI.player1 = "AI";
                    InformationPanelAI.player2 = p1t.getText();
                    BoardPanelAI.firstPlayer = true;
                }
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

