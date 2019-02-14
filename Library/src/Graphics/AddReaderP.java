package Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Main.SQL;
import sun.util.calendar.Gregorian;

public class AddReaderP extends APanel {
	
	JTextField tName, tBirthDate, tPesel;
	JLabel lName, lBirthDate, lPesel, lNationality;
	JButton bAdd; 
	JComboBox cNationality; 
	String[] n = {"Polish", "Other"};


	public AddReaderP() {
		
		lName = new JLabel("Name:");
		tName = new JTextField();
		add(lName);
		add(tName);
		
		lBirthDate = new JLabel("Birth Date (YYYY-MM-DD):");
		tBirthDate = new JTextField();
		add(lBirthDate);
		add(tBirthDate);
		
		lPesel = new JLabel("National ID/PESEL:");
		tPesel = new JTextField();
		add(lPesel);
		add(tPesel);
		
		lName = new JLabel("Nationality:");
		cNationality = new JComboBox<String>(n);
		add(lName);
		add(cNationality);
		

		bAdd = new JButton("Add Reader");
		add(bAdd);
		bAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SQL sqlConn = new SQL();
				if (checkBirthDate() && checkPESEL()) {
					try {	
						int x = showConfirmation();
						if (x==0) {
						sqlConn.insertReader(tName.getText(), tBirthDate.getText(), Long.parseLong(tPesel.getText()), (String) cNationality.getSelectedItem());	
						}
						}
						catch (NumberFormatException ex) {showError();}
						finally {sqlConn.closeConnection();}
				}
				else {showError();}
				}
		});
		
	}
	
	public boolean checkBirthDate() {
		if (tBirthDate.getText().length()==10) {
		if (tBirthDate.getText().substring(4, 5).equals("-") && tBirthDate.getText().substring(7, 8).equals("-")) 
			return true;
		}
			return false;
		}
	
	public boolean checkPESEL() {
		if (cNationality.getSelectedIndex()==0 && tPesel.getText().length()!=11) {
			return false;
		}
			return true;
		}
	
	public void showError() {
		JOptionPane.showMessageDialog(null, "Please ensure your data is correct.", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public int showConfirmation() {
		return JOptionPane.showConfirmDialog(null, "Do you really want to " + "add" + " " + tName.getText() + " " + tBirthDate.getText() + "?", "Please confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		}
}

