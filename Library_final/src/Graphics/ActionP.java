package Graphics;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Main.SQL;
import Model.ABook;
import Model.Book;
import Model.Borrows;
import Model.Reader;

public abstract class ActionP extends JPanel {
	
	
	JTable tableB, tableR, tableAB, tableBR;
	Object[][] valuesB, valuesR, valuesAB, valuesBR;
	DefaultTableModel dmB, dmR, dmAB, dmBR;
	Object[] columnsR = {"ID", "Name", "Birth Date", "PESEL", "Nationality"};
	Object[] columnsB = {"ID", "Title", "Author", "Release Year", "Category", "ISBN"};
	Object[] columnsAB = {"ID Book", "Title", "Author", "Borrow Date", "Return Date", "Actual Return Date","ID Borrow"};
	Object[] columnsBR = {"ID Borrow", "ID Reader", "Reader's Name", "ID Book", "Book's Title", "Borrow Date","Return Date", "Actual Return Date"};
	JTextField tSearchB, tSearchR, tSearchBR;
	JScrollPane scrollB, scrollR, scrollAB, scrollBR;
	JButton bSearchB, bSearchR, bSearchBR;
	JLabel lNameR, lTitleB, lNameTitle;
	int i, j, z;
	SQL sql = new SQL();
	
	public ActionP() {
		
		setLayout(new FlowLayout());
		setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
		setPreferredSize(new Dimension(650,500));
		
		lNameR = new JLabel("Search name:");
		tSearchR = new JTextField();
		bSearchR = new JButton("Search!");
		
		tableR = new JTable();
		scrollR = new JScrollPane(tableR);
		
		tableR.setModel(new MyTableModel());
		dmR = (DefaultTableModel) tableR.getModel();
		dmR.setColumnIdentifiers(columnsR);
		
		tableR.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				i = tableR.rowAtPoint(e.getPoint());				
			}
		});
		
		bSearchR.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SQL sqlConn = new SQL();
				setValuesInTableR(dmR, sqlConn.selectSearchedReaders(tSearchR.getText()));
				sqlConn.closeConnection();
				dmR.fireTableDataChanged();
			
			}
			
		});
		
		lTitleB = new JLabel("Search Book:");
		tSearchB = new JTextField();
		bSearchB = new JButton("Search!");
		
		tableB = new JTable();
		scrollB = new JScrollPane(tableB);
		
		tableB.setModel(new MyTableModel());
		dmB = (DefaultTableModel) tableB.getModel();
		dmB.setColumnIdentifiers(columnsB);
		
		tableB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				j = tableB.rowAtPoint(e.getPoint());				
			}
		});
		
		bSearchB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SQL sqlConn = new SQL();
				setValuesInTableB(dmB, sqlConn.selectSearchedBooksFalse(tSearchB.getText()));
				sqlConn.closeConnection();
				dmB.fireTableDataChanged();			
			}
			
		});
		
		tableAB = new JTable();
		scrollAB = new JScrollPane(tableAB);
		
		tableAB.setModel(new MyTableModel());
		dmAB = (DefaultTableModel) tableAB.getModel();
		dmAB.setColumnIdentifiers(columnsAB);
		
		tableAB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				z = tableAB.rowAtPoint(e.getPoint());				
			}
		});
		
		lNameTitle = new JLabel("Search name/title:");
		tSearchBR = new JTextField();
		bSearchBR = new JButton("Search!");
		
		
		tableBR = new JTable();
		scrollBR = new JScrollPane(tableBR);
		
		tableBR.setModel(new MyTableModel());
		dmBR = (DefaultTableModel) tableBR.getModel();
		dmBR.setColumnIdentifiers(columnsBR);
		
		bSearchBR.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SQL sqlConn = new SQL();
				setValuesInTableBR(dmBR, sqlConn.selectBorrows(tSearchBR.getText()));
				sqlConn.closeConnection();
				dmBR.fireTableDataChanged();
			
			}
			
		});
		sql.closeConnection();
	}
	
	public void setValuesInTableR(DefaultTableModel dtm, List<Reader> l) {
		
		valuesR = new Object[l.size()][5];
		dtm.setRowCount(l.size());
		
		for(int i = 0;i<l.size();i++) {
		valuesR[i][0] = l.get(i).getId_reader();
		valuesR[i][1] = l.get(i).getName();
		valuesR[i][2] = l.get(i).getBirthDate();
		valuesR[i][3] = l.get(i).getPesel();
		valuesR[i][4] = l.get(i).getNationaliaty();
		
		dtm.setValueAt(valuesR[i][0], i, 0);
		dtm.setValueAt(valuesR[i][1], i, 1);
		dtm.setValueAt(valuesR[i][2], i, 2);
		dtm.setValueAt(valuesR[i][3], i, 3);
		dtm.setValueAt(valuesR[i][4], i, 4);
		
		}		
	}
	
public void setValuesInTableB (DefaultTableModel dtm, List<Book> l) {
		
		valuesB = new Object[l.size()][6];
		dtm.setRowCount(l.size());
		
		for(int i = 0;i<l.size();i++) {
		valuesB[i][0] = l.get(i).getId_book();
		valuesB[i][1] = l.get(i).getTitle();
		valuesB[i][2] = l.get(i).getAuthor();
		valuesB[i][3] = l.get(i).getReleaseYear();
		valuesB[i][4] = l.get(i).getCategory();
		valuesB[i][5] = l.get(i).getIsbn();
		
		dtm.setValueAt(valuesB[i][0], i, 0);
		dtm.setValueAt(valuesB[i][1], i, 1);
		dtm.setValueAt(valuesB[i][2], i, 2);
		dtm.setValueAt(valuesB[i][3], i, 3);
		dtm.setValueAt(valuesB[i][4], i, 4);
		dtm.setValueAt(valuesB[i][5], i, 5);
		}
	}

public void setValuesInTableAB (DefaultTableModel dtm, List<ABook> l) {
	
	valuesAB = new Object[l.size()][7];
	dtm.setRowCount(l.size());
	
	for(int i = 0;i<l.size();i++) {
	valuesAB[i][0] = l.get(i).getId_book();
	valuesAB[i][1] = l.get(i).getTitle();
	valuesAB[i][2] = l.get(i).getAuthor();
	valuesAB[i][3] = l.get(i).getBorrowDate();
	valuesAB[i][4] = l.get(i).getReturnDate();
	valuesAB[i][5] = l.get(i).getActualReturnDate();
	valuesAB[i][6] = l.get(i).getId_borrow();
	
	dtm.setValueAt(valuesAB[i][0], i, 0);
	dtm.setValueAt(valuesAB[i][1], i, 1);
	dtm.setValueAt(valuesAB[i][2], i, 2);
	dtm.setValueAt(valuesAB[i][3], i, 3);
	dtm.setValueAt(valuesAB[i][4], i, 4);
	dtm.setValueAt(valuesAB[i][5], i, 5);
	dtm.setValueAt(valuesAB[i][6], i, 6);
	}
}

public void setValuesInTableBR (DefaultTableModel dtm, List<Borrows> l) {
	
	valuesBR = new Object[l.size()][8];
	dtm.setRowCount(l.size());
	
	for(int i = 0;i<l.size();i++) {
	valuesBR[i][0] = l.get(i).getId_borrow();
	valuesBR[i][1] = l.get(i).getId_reader();
	valuesBR[i][2] = l.get(i).getName();
	valuesBR[i][3] = l.get(i).getId_book();
	valuesBR[i][4] = l.get(i).getTitle();
	valuesBR[i][5] = l.get(i).getBorrowDate();
	valuesBR[i][6] = l.get(i).getReturnDate();
	valuesBR[i][7] = l.get(i).getActualReturnDate();
	
	dtm.setValueAt(valuesBR[i][0], i, 0);
	dtm.setValueAt(valuesBR[i][1], i, 1);
	dtm.setValueAt(valuesBR[i][2], i, 2);
	dtm.setValueAt(valuesBR[i][3], i, 3);
	dtm.setValueAt(valuesBR[i][4], i, 4);
	dtm.setValueAt(valuesBR[i][5], i, 5);
	dtm.setValueAt(valuesBR[i][6], i, 6);
	dtm.setValueAt(valuesBR[i][7], i, 7);
	}
}

}
