package Graphics;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import javafx.scene.input.DataFormat;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Main.SQL;

public class ProRetP extends ActionP{

	JTable tableP;
	JScrollPane scrollP;
	DefaultTableModel dmP;
	int k;
	JButton prolong, returnButton;
	SQL sql = new SQL();
	
	public ProRetP() {
		
		add(lNameR);
		tSearchR .setPreferredSize(new Dimension(100,25));
		add(tSearchR );
		add(bSearchR);
		
		tableP = new JTable();
		scrollP = new JScrollPane(tableP);
		
		tableP.setModel(new MyTableModel());
		dmP = (DefaultTableModel) tableP.getModel();
		dmP.setColumnIdentifiers(columnsR);
		
		
		
		tableP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SQL sqlConn = new SQL();
				k = tableP.rowAtPoint(e.getPoint());
				setValuesInTableAB(dmAB, sqlConn.selectSearchedBooksTrueForReader((int) valuesR[k][0]));
				sqlConn.closeConnection();
				dmAB.fireTableDataChanged();
			}
		});
		
		bSearchR.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SQL sqlConn = new SQL();
				setValuesInTableR(dmP, sqlConn.selectSearchedReaders(tSearchR.getText()));
				sqlConn.closeConnection();
				dmP.fireTableDataChanged();
						
			}
			
		});
		
		setValuesInTableR(dmP, sql.selectReaders());
		
		tableP.setPreferredSize(new Dimension(600, 400));
		scrollP.setPreferredSize(new Dimension(600, 150));
		add(scrollP);
		
		tableAB.setPreferredSize(new Dimension(600, 400));
		scrollAB.setPreferredSize(new Dimension(600, 150));
		add(scrollAB);
		
		prolong = new JButton("Prolong!");
		add(prolong);
		prolong.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (confirmationValue("prolong")==0) {
				SQL sqlConn = new SQL();
				if (ProlongDays()<=90) {
				sqlConn.UpdateBorrow(ProlongDays()+30, (int) valuesAB[z][6]);}
				else {JOptionPane.showConfirmDialog(null, "Prolongate is not available anymore" , "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);}
				setValuesInTableAB(dmAB, sqlConn.selectSearchedBooksTrueForReader((int) valuesR[k][0]));
				sqlConn.closeConnection();
				dmAB.fireTableDataChanged();
				}
			}
		});
		
		returnButton = new JButton("Return");
		add(returnButton);
		returnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SQL sqlConn = new SQL();
				
				if (confirmationValue("return")==0) {
				if(CheckFee()>0) {
				JOptionPane.showConfirmDialog(null, CheckFee()*0.1 +  " z³ fee is requested" , "Fee", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				sqlConn.ReturnBook((int) valuesAB[z][0]);
				sqlConn.UpdateBorrowReturn((int) valuesAB[z][6]);
				setValuesInTableAB(dmAB, sqlConn.selectSearchedBooksTrueForReader((int) valuesR[k][0]));
				dmAB.fireTableDataChanged();
				}
				else {
				sqlConn.ReturnBook((int) valuesAB[z][0]);
				sqlConn.UpdateBorrowReturn((int) valuesAB[z][6]);
				setValuesInTableAB(dmAB, sqlConn.selectSearchedBooksTrueForReader((int) valuesR[k][0]));
				dmAB.fireTableDataChanged();}
				}
				sqlConn.closeConnection();
			}
		});
		sql.closeConnection();
	}
		
	public int ProlongDays() {
		long diff = 0;
		try {
			Date borrowDate = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(valuesAB[z][3]));
			Date returnDate = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(valuesAB[z][4]));
			diff = returnDate.getTime()-borrowDate.getTime();
		} catch (ParseException e) {e.printStackTrace();}
		int numberOfDays = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		return numberOfDays; 
		}
	
	public int CheckFee() {
		long diff = 0;
		try {
			Date returnDate = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(valuesAB[z][4]));
			if (valuesAB[z][5]==null)
				return -1;
			else {
			Date actualReturnDate = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(valuesAB[z][5]));
			diff = actualReturnDate.getTime()- returnDate.getTime();}
		} catch (ParseException e) {e.printStackTrace();}
		int numberOfDays = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		return numberOfDays; 
		}
	
	public int confirmationValue(String what) {
		return JOptionPane.showConfirmDialog(null, "You will " + what + " book "  + valuesAB[z][1], "Please confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
	}
}
