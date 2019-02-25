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

public class DeleteReaderP extends APanel{

	
	List<Reader> list;
	JLabel lSelected, lReader;
	JButton bDelete;
	int i;
	SQL sql = new SQL();
	
	public DeleteReaderP() {
		
		setLayout(new FlowLayout());
		
		add(lName);
		tSearch .setPreferredSize(new Dimension(100,25));
		add(tSearch );
		add(bSearch);

		table.setPreferredSize(new Dimension(600, 400));
		scroll.setPreferredSize(new Dimension(600, 300));
		add(scroll);

		setValuesInTable(dm, sql.selectReaders());
	
		lSelected = new JLabel("Selected:");
		add(lSelected);
		lReader = new JLabel();
		lReader.setFont(new Font(Font.SANS_SERIF, Font.BOLD + Font.ITALIC, 14));
		lReader.setForeground(Color.BLUE);
		lReader.setPreferredSize(new Dimension(150, 25));
		add(lReader);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				i = table.rowAtPoint(e.getPoint());	
				lReader.setText((String) values[i][1]);
			
			}
		});
		
		bDelete = new JButton("Delete");
		bDelete.setLayout(new BorderLayout());
		add(bDelete);
		bDelete.setPreferredSize(new Dimension(350, 25));
		bDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int r = JOptionPane.showConfirmDialog(null, "You will delete user: " + (String) values[i][1] , "Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				if (r==0) {
				SQL sqlConn = new SQL();
				sqlConn.deleteReader((int) values[i][0]) ;
				setValuesInTable(dm, sqlConn.selectReaders());
				sqlConn.closeConnection();
				dm.fireTableDataChanged();
				}
			}
		});
		sql.closeConnection();
	}	
}
