package Graphics;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Main.SQL;

public class BorrowP extends ActionP {
	
	JButton borrow;
	
	SQL sql = new SQL();

	public BorrowP() {
		add(lNameR);
		tSearchR .setPreferredSize(new Dimension(100,25));
		add(tSearchR );
		add(bSearchR);
		tableR.setPreferredSize(new Dimension(600, 400));
		scrollR.setPreferredSize(new Dimension(600, 150));
		add(scrollR);
		setValuesInTableR(dmR, sql.selectReaders());
		
		add(lTitleB);
		tSearchB .setPreferredSize(new Dimension(100,25));
		add(tSearchB );
		add(bSearchB);
		tableB.setPreferredSize(new Dimension(600, 400));
		scrollB.setPreferredSize(new Dimension(600, 150));
		add(scrollB);
		setValuesInTableB(dmB, sql.selectBooksFalse());
		
		borrow = new JButton("Borrow!");
		add(borrow);
		borrow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (confirmationValue()==0) {
				SQL sqlConn = new SQL();
				sqlConn.BorrowBook((int) valuesB[j][0]); 
				sqlConn.insertBorrow((int) valuesR[i][0], (int) valuesB[j][0]);
				setValuesInTableB(dmB, sqlConn.selectBooksFalse());
				sqlConn.closeConnection();
				dmB.fireTableDataChanged();
				}
			}
		});
		sql.closeConnection();
	}
	
	public int confirmationValue() {
		return JOptionPane.showConfirmDialog(null, "Reader " + valuesR[i][1] + " will borrow book " + valuesB[j][1], "Please confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
	}
	
}
