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

public class EditBookP extends BPanel{
	
	
	
	JButton bEdit, bSave;
	AddBookP addP = new AddBookP();
	int i;
	SQL sql = new SQL();
	
	public EditBookP() {
		
		setLayout(new FlowLayout());
		
		add(lName);
		tSearch.setPreferredSize(new Dimension(100,25));
		add(tSearch);
		add(bSearch);
		
		table.setPreferredSize(new Dimension(600, 400));
		scroll.setPreferredSize(new Dimension(600, 60));
		add(scroll);
		
		

		setValuesInTable(dm, sql.selectBooks());
		
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
				addP.tTitle.setText(String.valueOf(values[i][1]));
				addP.cAuthor.setSelectedIndex(addP.getIndexOfList(String.valueOf(values[i][2])));
				addP.tReleaseYear.setText(String.valueOf(values[i][3]));
				addP.cCategory.setSelectedItem(values[i][4]);
				addP.tISBN.setText(String.valueOf(values[i][5]));
			}
		});
		
		
		add(addP);
		addP.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));
		addP.setPreferredSize(new Dimension(650, 250));
		addP.remove(addP.bAdd);
		
		bSave = new JButton("Save");
		add(bSave);
		bSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SQL sqlConn = new SQL();
				if (addP.checkISBN() && addP.checkReleaseYear()) {
						int r = JOptionPane.showConfirmDialog(null, "You will edit book: " + (String) values[i][1] , "Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
						if (r==0) {
						try {
						int id = addP.list.get(addP.cAuthor.getSelectedIndex()).getId_author();
						sqlConn.updateBook((int) values[i][0], addP.tTitle.getText(), id, Integer.parseInt(addP.tReleaseYear.getText()), String.valueOf(addP.cCategory.getSelectedItem()), Long.parseLong(addP.tISBN.getText()));
						setValuesInTable(dm, sqlConn.selectBooks());
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
