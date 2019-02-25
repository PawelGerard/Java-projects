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
import Model.ReaderStat;

public class MARP extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable tableB;
	Object[][] valuesB;
	DefaultTableModel dmB;
	Object[] columnsB = {"ID Reader", "Name", "Birth Date", "PESEL", "Nationality", "Number of Borrows"};
	JScrollPane scrollB;
	JLabel lTitleB;
	SQL sql = new SQL();
	public MARP() {
		
		tableB = new JTable();
		scrollB = new JScrollPane(tableB);
		
		dmB = (DefaultTableModel) tableB.getModel();
		dmB.setColumnIdentifiers(columnsB);
		tableB.setEnabled(false);
		
		tableB.setPreferredSize(new Dimension(600, 400));
		scrollB.setPreferredSize(new Dimension(600, 300));
		add(scrollB);
		setValuesInTableB(dmB, sql.selectReaderStat());
		sql.closeConnection();
		
	}
	
	
	public void setValuesInTableB (DefaultTableModel dtm, List<ReaderStat> l) {
		
		valuesB = new Object[l.size()][6];
		dtm.setRowCount(l.size());
		
		for(int i = 0;i<l.size();i++) {
		valuesB[i][0] = l.get(i).getId_reader();
		valuesB[i][1] = l.get(i).getName();
		valuesB[i][2] = l.get(i).getBirthDate();
		valuesB[i][3] = l.get(i).getPesel();
		valuesB[i][4] = l.get(i).getNationaliaty();
		valuesB[i][5] = l.get(i).getBorrows();
		
		dtm.setValueAt(valuesB[i][0], i, 0);
		dtm.setValueAt(valuesB[i][1], i, 1);
		dtm.setValueAt(valuesB[i][2], i, 2);
		dtm.setValueAt(valuesB[i][3], i, 3);
		dtm.setValueAt(valuesB[i][4], i, 4);
		dtm.setValueAt(valuesB[i][5], i, 5);
		}
	}
}
