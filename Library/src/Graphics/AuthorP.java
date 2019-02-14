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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Main.SQL;
import Model.Author;
import Model.Reader;

public class AuthorP extends JPanel {

	JTable table;
	Object[][] values;
	DefaultTableModel dm;
	Object[] columns = {"ID", "Name", "Year of Birth"};
	JTextField tSearch, tName;
	JScrollPane scroll;
	JButton bSearch, bAdd, bEdit, bDelete, bSave;
	JLabel lSearch, lName, lYear;
	JComboBox cYear;
	int i;
	SQL sql = new SQL();
	
	public AuthorP() {
		
		setLayout(new FlowLayout());
		setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));
		setPreferredSize(new Dimension(650,500));
		
		lName = new JLabel("Search name:");
		add(lName);
		tSearch = new JTextField();
		tSearch .setPreferredSize(new Dimension(100,25));
		add(tSearch);
		bSearch = new JButton("Search!");
		add(bSearch);
		
		table = new JTable();
		scroll = new JScrollPane(table);
		table.setModel(new MyTableModel());
		dm = (DefaultTableModel) table.getModel();
		dm.setColumnIdentifiers(columns);
		setValuesInTable(dm, sql.selectAuthors());
		
		table.setPreferredSize(new Dimension(500, 400));
		scroll.setPreferredSize(new Dimension(500, 150));
		add(scroll);
		
		bSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SQL sqlConn = new SQL();
				setValuesInTable(dm, sqlConn.selectSearchedAuthors(tSearch.getText()));
				sqlConn.closeConnection();
				dm.fireTableDataChanged();
			
			}
			
		});
		
		JLabel lName = new JLabel("Name:");
		add(lName);
		
		tName = new JTextField();
		add(tName);
		tName.setPreferredSize(new Dimension(430, 25));
		
		JLabel lYear = new JLabel("Year of Birth:");
		add(lYear);
		
		cYear = new JComboBox(createListOfYears());
		add(cYear);
		cYear.setPreferredSize(new Dimension (400, 25));
		
		bAdd = new JButton("Add!");
		add(bAdd);
		bAdd.setPreferredSize(new Dimension (100, 30));
		bAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SQL sqlConn = new SQL();
				int x = showConfirmation("add");
				if (x==0) {
				try {
				sqlConn.insertAuthor(tName.getText(), Integer.parseInt((String) cYear.getSelectedItem()));
				setValuesInTable(dm, sqlConn.selectAuthors());
				dm.fireTableDataChanged();
				}
				catch (NumberFormatException ex) {ex.printStackTrace();}
				finally {sqlConn.closeConnection();}
				}
			}
		});
		
		bEdit = new JButton("Edit");
		add(bEdit);
		bEdit.setPreferredSize(new Dimension (100, 30));
		bEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			SQL sql = new SQL();
				
				try {
					tName.setText((String) values[i][1]);
					cYear.setSelectedItem(String.valueOf(values[i][2]));
				}
				catch (Exception ex) {ex.printStackTrace();}
				finally {sql.closeConnection();}
				
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				i = table.rowAtPoint(e.getPoint());				
			}
		});
		
		bSave = new JButton("Save");
		add(bSave);
		bSave.setPreferredSize(new Dimension (100, 30));
		bSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			SQL sql = new SQL();
				int x = showConfirmation("save");
				if (x==0) {
				try {
					sql.updateAuthor((int) values[i][0], tName.getText(), Integer.parseInt((String) cYear.getSelectedItem()));
					setValuesInTable(dm, sql.selectAuthors());
					dm.fireTableDataChanged();
				}
				catch (Exception ex) {ex.printStackTrace();}
				finally {sql.closeConnection();}
				}
			}
		});
		
		
		bDelete = new JButton("Delete");
		add(bDelete);
		bDelete.setPreferredSize(new Dimension (100, 30));
		bDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			SQL sql = new SQL();
				int x = showConfirmation("delete");
				if (x==0) {
				try {
					sql.deleteAuthor((int) values[i][0]);
					setValuesInTable(dm, sql.selectAuthors());
					dm.fireTableDataChanged();
				}
				catch (Exception ex) {ex.printStackTrace();}
				finally {sql.closeConnection();}
				}
			}
		});
	
	}
	
	
	
public void setValuesInTable(DefaultTableModel dtm, List<Author> l) {
		
		values = new Object[l.size()][3];
		dtm.setRowCount(l.size());
		
		for(int i = 0;i<l.size();i++) {
		values[i][0] = l.get(i).getId_author();
		values[i][1] = l.get(i).getName();
		values[i][2] = l.get(i).getYearOfBirth();
		
		dtm.setValueAt(values[i][0], i, 0);
		dtm.setValueAt(values[i][1], i, 1);
		dtm.setValueAt(values[i][2], i, 2);
		}	
	}	

	public String[] createListOfYears() {
		String[] s = new String[500];
		
		for (int i = 1; i<500; i++) {
			s[i] = String.valueOf(1500 + i);
		}
		
		return s;
		
	}
	
	public int showConfirmation(String action) {
	return JOptionPane.showConfirmDialog(null, "Do you really want to " + action + " " + tName.getText() + " " + cYear.getSelectedItem() + "?", "Please confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
	}
	
}
