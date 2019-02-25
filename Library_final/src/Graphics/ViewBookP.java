package Graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Main.SQL;
import Model.Reader;

public class ViewBookP extends BPanel{

	
	List<Reader> list;
	JLabel lSelected, lReader;
	SQL sql = new SQL();
	
	
	public ViewBookP() {
		
		setLayout(new FlowLayout());
		
		add(lName);
		tSearch .setPreferredSize(new Dimension(100,25));
		add(tSearch );
		add(bSearch);

		table.setPreferredSize(new Dimension(600, 500));
		scroll.setPreferredSize(new Dimension(600, 300));
		add(scroll);

		setValuesInTable(dm, sql.selectBooks());
	
		lSelected = new JLabel("Total number of books :");
		add(lSelected);
		lReader = new JLabel();
		add(lReader);
		lReader.setText(String.valueOf(table.getRowCount()));
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
			}
		});
		sql.closeConnection();
	}	
}
