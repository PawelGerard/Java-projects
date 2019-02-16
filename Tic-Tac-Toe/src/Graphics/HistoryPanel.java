
package Graphics;

import Graphics.MainPanel;
import Main.Frame;
import Main.SQL;
import Model.Game;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class HistoryPanel extends JPanel {
    JTable table;
    DefaultTableModel dtm;
    String[] columnNames = new String[]{"ID", "Type", "Size of Board", "First player", "Second Player", "Game Result", "Winner", "Time"};
    JScrollPane scroll;
    SQL sql;
    JButton button;

    public HistoryPanel() {
        setLayout(new BorderLayout());
        table = new JTable();
        table.setPreferredSize(new Dimension(480, 1500));
        scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(480, 400));
        add((Component)scroll, "North");
        sql = new SQL();
        dtm = (DefaultTableModel)table.getModel();
        dtm.setColumnIdentifiers(columnNames);
        setValuesInTable(dtm, sql.selectGames());
        sql.closeConnection();
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
        add((Component)button, "South");
    }

    public void setValuesInTable(DefaultTableModel dtm, List<Game> l) {
        dtm.setRowCount(l.size());
        int i = 0;
        while (i < l.size()) {
            dtm.setValueAt(l.get(i).getId(), i, 0);
            dtm.setValueAt(l.get(i).getType(), i, 1);
            dtm.setValueAt(l.get(i).getBoardSize(), i, 2);
            dtm.setValueAt(l.get(i).getPlayer1(), i, 3);
            dtm.setValueAt(l.get(i).getPlayer2(), i, 4);
            dtm.setValueAt(l.get(i).getGameResult(), i, 5);
            dtm.setValueAt(l.get(i).getWinner(), i, 6);
            dtm.setValueAt(Float.valueOf(l.get(i).getTime()), i, 7);
            ++i;
        }
    }

}

