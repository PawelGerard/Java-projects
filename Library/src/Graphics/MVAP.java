package Graphics;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Main.SQL;
import Model.AuthorStat;
import Model.CatStat;

public class MVAP extends JPanel {

	
	JTable tableB;
	Object[][] valuesB;
	DefaultTableModel dmB;
	Object[] columnsB = {"ID Author", "Author's Name", "Author's Year of Birth", "Number of Borrows"};
	JScrollPane scrollB;
	JLabel lTitleB;
	SQL sql = new SQL();
	
	public MVAP() {
		
		tableB = new JTable();
		scrollB = new JScrollPane(tableB);
		
		dmB = (DefaultTableModel) tableB.getModel();
		dmB.setColumnIdentifiers(columnsB);
		tableB.setEnabled(false);
		
		tableB.setPreferredSize(new Dimension(600, 400));
		scrollB.setPreferredSize(new Dimension(600, 300));
		add(scrollB);
		setValuesInTableB(dmB, sql.selectAuthorStat());
		sql.closeConnection();
	}
	
	
	public void setValuesInTableB (DefaultTableModel dtm, List<AuthorStat> l) {
		
		valuesB = new Object[l.size()][4];
		dtm.setRowCount(l.size());
		
		for(int i = 0;i<l.size();i++) {
		valuesB[i][0] = l.get(i).getId_author();
		valuesB[i][1] = l.get(i).getName();
		valuesB[i][2] = l.get(i).getYearOfBirth();
		valuesB[i][3] = l.get(i).getBorrows();
		
		dtm.setValueAt(valuesB[i][0], i, 0);
		dtm.setValueAt(valuesB[i][1], i, 1);
		dtm.setValueAt(valuesB[i][2], i, 2);
		dtm.setValueAt(valuesB[i][3], i, 3);
		}
	}
}
