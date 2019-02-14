package Graphics;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Main.SQL;
import Model.ABook;
import Model.BookStat;

public class MVBP extends JPanel {

	JTable tableB;
	Object[][] valuesB;
	DefaultTableModel dmB;
	Object[] columnsB = {"ID", "Title", "Author", "Release Year", "Category", "ISBN", "Number of Borrows"};
	JScrollPane scrollB;
	JLabel lTitleB;
	SQL sql = new SQL();
	
	public MVBP() {
		
		tableB = new JTable();
		scrollB = new JScrollPane(tableB);
		
		dmB = (DefaultTableModel) tableB.getModel();
		dmB.setColumnIdentifiers(columnsB);
		tableB.setEnabled(false);
		
		tableB.setPreferredSize(new Dimension(600, 400));
		scrollB.setPreferredSize(new Dimension(600, 300));
		add(scrollB);
		setValuesInTableB(dmB, sql.selectBooksStat());
		sql.closeConnection();
		
	}
	
	
	public void setValuesInTableB (DefaultTableModel dtm, List<BookStat> l) {
		
		valuesB = new Object[l.size()][7];
		dtm.setRowCount(l.size());
		
		for(int i = 0;i<l.size();i++) {
		valuesB[i][0] = l.get(i).getId_book();
		valuesB[i][1] = l.get(i).getTitle();
		valuesB[i][2] = l.get(i).getAuthor();
		valuesB[i][3] = l.get(i).getReleaseYear();
		valuesB[i][4] = l.get(i).getCategory();
		valuesB[i][5] = l.get(i).getIsbn();
		valuesB[i][6] = l.get(i).getBorrows();
		
		dtm.setValueAt(valuesB[i][0], i, 0);
		dtm.setValueAt(valuesB[i][1], i, 1);
		dtm.setValueAt(valuesB[i][2], i, 2);
		dtm.setValueAt(valuesB[i][3], i, 3);
		dtm.setValueAt(valuesB[i][4], i, 4);
		dtm.setValueAt(valuesB[i][5], i, 5);
		dtm.setValueAt(valuesB[i][6], i, 6);
		}
	}
}
