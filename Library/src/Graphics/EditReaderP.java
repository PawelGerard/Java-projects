package Graphics;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Main.SQL;
import Model.Reader;

public class EditReaderP extends APanel{
	
	
	
	JButton bEdit, bSave;
	AddReaderP addP = new AddReaderP();
	int i;
	SQL sql = new SQL();
	
	public EditReaderP() {
		
		setLayout(new FlowLayout());
		
		add(lName);
		tSearch.setPreferredSize(new Dimension(100,25));
		add(tSearch);
		add(bSearch);
		
		table.setPreferredSize(new Dimension(600, 400));
		scroll.setPreferredSize(new Dimension(600, 100));
		add(scroll);

		setValuesInTable(dm, sql.selectReaders());
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				i = table.rowAtPoint(e.getPoint());				
			}
		});

		
		bEdit = new JButton("Edit");
		add(bEdit);
		bEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addP.tName.setText((String) values[i][1]);
				addP.tBirthDate.setText((String) values[i][2]);
				addP.tPesel.setText(String.valueOf(values[i][3]));
				addP.cNationality.setSelectedItem(values[i][4]);
			}
		});
		
		
		add(addP);
		addP.setBorder(BorderFactory.createEmptyBorder(5, 40, 5, 40));
		addP.setPreferredSize(new Dimension(600, 250));
		addP.remove(addP.bAdd);
		
		bSave = new JButton("Save");
		add(bSave);
		bSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SQL sqlConn = new SQL();
				if (addP.checkBirthDate() && addP.checkPESEL()) {
						int r = JOptionPane.showConfirmDialog(null, "You will edit user: " + (String) values[i][1] , "Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
						if (r==0) {
						try {
						sqlConn.updateReader(Integer.parseInt(String.valueOf(values[i][0])), addP.tName.getText(), addP.tBirthDate.getText(), Long.parseLong(addP.tPesel.getText()), (String) addP.cNationality.getSelectedItem());	
						setValuesInTable(dm, sqlConn.selectReaders());
						dm.fireTableDataChanged();
						}
						catch (NumberFormatException ex) {addP.showError();}
						finally {sqlConn.closeConnection();}
						
						}
				}
				else {addP.showError();}
			}
		});
		sql.closeConnection();
	}
	
}
