package Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Main.SQL;
import Model.Author;
import sun.util.calendar.Gregorian;

public class AddBookP extends APanel {
	
	
	JTextField tTitle, tReleaseYear, tISBN;
	JLabel lTitle, lAuthor, lReleaseYear, lISBN, lCategory;
	JButton bAdd; 
	JComboBox cAuthor, cCategory; 
	String[] cat = {"Drama", "Comedy", "Historical", "Romance", "Documental"};
	List<Author> list;
	SQL sql = new SQL();

	public AddBookP() {
		
		list = sql.selectAuthors();
		
		lTitle = new JLabel("Title:");
		tTitle = new JTextField();
		add(lTitle);
		add(tTitle);
		
		lAuthor = new JLabel("Author:");
		cAuthor = new JComboBox<Object>(list.toArray());
		add(lAuthor);
		add(cAuthor);
		
		lReleaseYear = new JLabel("Release Year:");
		tReleaseYear = new JTextField();
		add(lReleaseYear);
		add(tReleaseYear);
		
		lISBN = new JLabel("ISBN (13 Digits):");
		tISBN = new JTextField();
		add(lISBN);
		add(tISBN);
		
		lCategory = new JLabel("Category:");
		cCategory = new JComboBox<String>(cat);
		add(lCategory);
		add(cCategory);
		

		bAdd = new JButton("Add Book");
		add(bAdd);
		bAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SQL sqlConn = new SQL();
				if (checkReleaseYear() && checkISBN()) {
						try {
						int x = showConfirmation();
						if (x==0) {
						int id = list.get(cAuthor.getSelectedIndex()).getId_author();
						sqlConn.insertBook(tTitle.getText(),id , Integer.parseInt(tReleaseYear.getText()), String.valueOf(cCategory.getSelectedItem()), Long.parseLong(tISBN.getText()), false);
						}
						}
						catch (NumberFormatException ex) {showError();}
						finally {sqlConn.closeConnection();}
				}
				else {showError();}
			}
		});
		sql.closeConnection();		
	}
	
	public boolean checkReleaseYear() {
		try {
		if (tReleaseYear.getText().length()==4 && Integer.parseInt(tReleaseYear.getText())<2018 ) {
			return true;
		}
		else {return false;}
		}
		catch (NumberFormatException e) {return false;}
	}
	
	public boolean checkISBN() {
		try {
		if (tISBN.getText().length()==13) {
			return true;
		}
		else {return false;}
		}
		catch (NumberFormatException e) {return false;}		
		}
	
	public void showError() {
		JOptionPane.showMessageDialog(null, "Please ensure your data is correct.", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public int getIndexOfList(String author) {
		int x = -1;
		for (int i = 0; i<list.size();i++) {
			String authorData = list.get(i).getName() + " " + list.get(i).getYearOfBirth();
			if (authorData.equals(author)) {
				x=i;
			}
		}
		return x;
	}
	
	public int showConfirmation() {
		return JOptionPane.showConfirmDialog(null, "Do you really want to " + "add" + " " + tTitle.getText() + " " + tReleaseYear.getText() + "?", "Please confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		}
}

