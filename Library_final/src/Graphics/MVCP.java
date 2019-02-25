package Graphics;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Main.SQL;
import Model.BookStat;
import Model.CatStat;

public class MVCP extends JPanel{

	JTable tableB;
	Object[][] valuesB;
	DefaultTableModel dmB;
	Object[] columnsB = {"Category", "Number of Borrows"};
	JScrollPane scrollB;
	JLabel lTitleB;
	SQL sql = new SQL();
	
	public MVCP() {
		
		tableB = new JTable();
		scrollB = new JScrollPane(tableB);
		
		dmB = (DefaultTableModel) tableB.getModel();
		dmB.setColumnIdentifiers(columnsB);
		tableB.setEnabled(false);
		
		tableB.setPreferredSize(new Dimension(600, 400));
		scrollB.setPreferredSize(new Dimension(600, 300));
		add(scrollB);
		setValuesInTableB(dmB, sql.selectCatStat());
		sql.closeConnection();
		
	}
	
	
	public void setValuesInTableB (DefaultTableModel dtm, List<CatStat> l) {
		
		valuesB = new Object[l.size()][2];
		dtm.setRowCount(l.size());
		
		for(int i = 0;i<l.size();i++) {
		valuesB[i][0] = l.get(i).getCategory();
		valuesB[i][1] = l.get(i).getBorrows();
	
		
		dtm.setValueAt(valuesB[i][0], i, 0);
		dtm.setValueAt(valuesB[i][1], i, 1);
		}
	}
}
