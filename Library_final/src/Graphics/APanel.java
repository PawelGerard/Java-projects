package Graphics;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Main.SQL;
import Model.Reader;

public abstract class APanel extends JPanel {

	JTable table;
	Object[][] values;
	DefaultTableModel dm;
	Object[] columns = {"ID", "Name", "Birth Date", "PESEL", "Nationality"};
	JTextField tSearch;
	JScrollPane scroll;
	JButton bSearch;
	JLabel lName;
	int i;
	
	
	public APanel() {
		
		setLayout(new GridLayout(0, 1, 10, 10));
		setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
		setPreferredSize(new Dimension(650,500));
		
		lName = new JLabel("Search name:");
		tSearch = new JTextField();
		bSearch = new JButton("Search!");
		
		table = new JTable();
		scroll = new JScrollPane(table);
		
		
		table.setModel(new MyTableModel());
		dm = (DefaultTableModel) table.getModel();
		dm.setColumnIdentifiers(columns);
		
		bSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SQL sqlConn = new SQL();
				setValuesInTable(dm, sqlConn.selectSearchedReaders(tSearch.getText()));
				sqlConn.closeConnection();
				dm.fireTableDataChanged();
			
			}
			
		});
		
	}
	
	public void setValuesInTable(DefaultTableModel dtm, List<Reader> l) {
		
		values = new Object[l.size()][5];
		dtm.setRowCount(l.size());
		
		for(int i = 0;i<l.size();i++) {
		values[i][0] = l.get(i).getId_reader();
		values[i][1] = l.get(i).getName();
		values[i][2] = l.get(i).getBirthDate();
		values[i][3] = l.get(i).getPesel();
		values[i][4] = l.get(i).getNationaliaty();
		
		dtm.setValueAt(values[i][0], i, 0);
		dtm.setValueAt(values[i][1], i, 1);
		dtm.setValueAt(values[i][2], i, 2);
		dtm.setValueAt(values[i][3], i, 3);
		dtm.setValueAt(values[i][4], i, 4);
		}	
	}	
}	
