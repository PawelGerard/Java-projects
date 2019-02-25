package Graphics;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class Menu extends JPanel {

	public static JMenuBar menuBar;
	JMenu readers, books, actions, statistics;
	JMenuItem rCreate, rDelete, rEdit, rView, bAuthor, bCreate, baAdd, baDelete, bDelete, bEdit, bView, aBorrows, aReturns, aProlong, aView, sMostViewedBooks, sMostActiveReaders, sMostViewedCategories, sMostViewedAuthors; 
	
	public Menu() {
		
		setPreferredSize(new Dimension(650,500));
		
		menuBar = new JMenuBar();
		add(menuBar);
		
		readers = new JMenu("Readers");
		menuBar.add(readers);
		
		addMenuItem(readers, rCreate, "Add Reader", new Listener("Graphics.AddReaderP"));
		addMenuItem(readers, rDelete, "Delete Reader", new Listener("Graphics.DeleteReaderP"));
		addMenuItem(readers, rEdit, "Edit Reader", new Listener("Graphics.EditReaderP"));
		addMenuItem(readers, rView, "View All Readers", new Listener("Graphics.ViewReaderP"));
		
		books = new JMenu("Books");
		menuBar.add(books);
		
		addMenuItem(books, bAuthor, "Author Management", new Listener("Graphics.AuthorP"));
		addMenuItem(books, bCreate, "Add Book", new Listener("Graphics.AddBookP"));
		addMenuItem(books, bDelete, "Delete Book", new Listener("Graphics.DeleteBookP"));
		addMenuItem(books, bEdit, "Edit Book", new Listener("Graphics.EditBookP"));
		addMenuItem(books, bView, "View All Books", new Listener("Graphics.ViewBookP"));
		
		actions = new JMenu("Actions");
		menuBar.add(actions);
		
		addMenuItem(actions, aBorrows, "Borrow", new Listener("Graphics.BorrowP"));
		addMenuItem(actions, aProlong, "Return/Prolong", new Listener("Graphics.ProRetP"));
		addMenuItem(actions, aView, "View All", new Listener("Graphics.ViewP"));
		
		statistics = new JMenu("Statistics");
		menuBar.add(statistics);
		
		addMenuItem(statistics, sMostViewedBooks, "Most Viewed Books", new Listener("Graphics.MVBP"));
		addMenuItem(statistics, sMostViewedCategories, "Most Viewed Categories", new Listener("Graphics.MVCP"));
		addMenuItem(statistics, sMostViewedAuthors, "Most Viewed Authors", new Listener("Graphics.MVAP"));
		addMenuItem(statistics, sMostActiveReaders, "Most Active Readers", new Listener("Graphics.MARP"));
	}
	
	
	public void addMenuItem(JMenu m, JMenuItem mi, String text, ActionListener l) {
		mi = new JMenuItem(text);
		m.add(mi);
		mi.addActionListener(l);
	}
	
	public class Listener implements ActionListener {
		
		String panelName ;
		JPanel panel;
		
		public Listener(String panelName) {
		this.panelName = panelName;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				panel = (JPanel) Class.forName(panelName).newInstance();
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException ex) {ex.printStackTrace();}
			removeAll();
			add(panel);
			repaint();
			revalidate();
			
		}
	}
}