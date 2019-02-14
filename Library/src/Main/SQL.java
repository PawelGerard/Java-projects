package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.ABook;
import Model.Author;
import Model.AuthorStat;
import Model.Book;
import Model.BookStat;
import Model.Borrows;
import Model.CatStat;
import Model.Reader;
import Model.ReaderStat;

public class SQL {

	
		
		public static final String DRIVER = "org.sqlite.JDBC";
		public static final String URL = "jdbc:sqlite::resource:library.db";
		private Statement statement;
		private Connection connection;
		
		public SQL() {
			
			try {
				connection = DriverManager.getConnection(URL);
				statement = connection.createStatement();
				} 
			catch (SQLException e) {
				e.printStackTrace();
				}
			createTables();
			
		}
	
		public void createTables() {
			try {
				statement.execute("CREATE TABLE IF NOT EXISTS Readers (id_reader INTEGER PRIMARY KEY AUTOINCREMENT, Name varchar(255), BirthDate Date, PESEL bigint, Nationality varchar(255));");
				statement.execute("CREATE TABLE IF NOT EXISTS Books (id_book INTEGER PRIMARY KEY AUTOINCREMENT, Title varchar(255), id_author int, ReleaseYear int, Category varchar(255), ISBN bigint, Status boolean);");
				statement.execute("CREATE TABLE IF NOT EXISTS Authors (id_author INTEGER PRIMARY KEY AUTOINCREMENT, Name varchar(255), YearOfBirth int);");
				statement.execute("CREATE TABLE IF NOT EXISTS Borrows (id_borrow INTEGER PRIMARY KEY AUTOINCREMENT, Id_reader int, Id_book int, BorrowDate Date, ReturnDate Date, ActualReturnDate Date);");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public void closeConnection() {
			try {connection.close();}
			catch (SQLException e) {e.printStackTrace();}
		}
		
		public List<Reader> selectReaders() {
			List<Reader> list = new ArrayList<>();
			int id_reader;
			long pesel;
			String name, birthDate, nationality;
			try {
				ResultSet result = statement.executeQuery("SELECT * FROM Readers;");
				while (result.next()) {
					id_reader = result.getInt("id_reader");
					name = result.getString("Name");
					birthDate = result.getString("BirthDate");
					pesel = result.getLong("PESEL");
					nationality = result.getString("Nationality");
					list.add(new Reader(id_reader, name, birthDate, pesel, nationality));
				}
			} catch (SQLException e) {e.printStackTrace();}
			return list;			
		}
		
		public List<Author> selectAuthors() {
			List<Author> list = new ArrayList<>();
			int id_author;
			int yearOfBirth;
			String name;
			try {
				ResultSet result = statement.executeQuery("SELECT * FROM Authors;");
				while (result.next()) {
					id_author = result.getInt("id_author");
					name = result.getString("Name");
					yearOfBirth = result.getInt("YearOfBirth");
					list.add(new Author(id_author, name, yearOfBirth));
				}
			} catch (SQLException e) {e.printStackTrace();}
			return list;			
		}
		
		public List<Author> selectSearchedAuthors(String searchFor) {
			List<Author> list = new ArrayList<>();
			int id_author;
			int yearOfBirth;
			String name;
			try {
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM Authors WHERE Name LIKE ? OR Name LIKE ? OR Name LIKE ? ;");
				ps.setString(1, searchFor + "%");
				ps.setString(2, "%" + searchFor);
				ps.setString(3, "%" + searchFor + "%");
				ResultSet result = ps.executeQuery();
				while (result.next()) {
					id_author = result.getInt("id_author");
					name = result.getString("Name");
					yearOfBirth = result.getInt("YearOfBirth");
					list.add(new Author(id_author, name, yearOfBirth));
				}
			} catch (SQLException e) {e.printStackTrace();}
			return list;			
		}
		
		public List<Reader> selectSearchedReaders(String searchFor) {
			List<Reader> list = new ArrayList<>();
			int id_reader;
			long pesel;
			String name, birthDate, nationality;
			try {
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM Readers WHERE Name LIKE ? OR Name LIKE ? OR Name LIKE ? ;");
				ps.setString(1, searchFor + "%");
				ps.setString(2, "%" + searchFor);
				ps.setString(3, "%" + searchFor + "%");
				ResultSet result = ps.executeQuery();
				while (result.next()) {
					id_reader = result.getInt("id_reader");
					name = result.getString("Name");
					birthDate = result.getString("BirthDate");
					pesel = result.getLong("PESEL");
					nationality = result.getString("Nationality");
					list.add(new Reader(id_reader, name, birthDate, pesel, nationality));
				}
			} catch (SQLException e) {e.printStackTrace();}
			return list;			
		}
		
		public void insertReader(String name, String BirthDate, long pesel, String nationality) {
			try {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO Readers values (null, ?, ?, ?, ?);");
				ps.setString(1, name);
				ps.setString(2, BirthDate);
				ps.setLong(3, pesel);
				ps.setString(4, nationality);
				ps.execute();
			} catch (SQLException e) {e.printStackTrace();}	
		}
		
		public void updateReader(int id, String name, String BirthDate, long pesel, String nationality) {
			try {
				PreparedStatement ps = connection.prepareStatement("UPDATE Readers SET Name = ?, BirthDate = ?, PESEL = ?, Nationality = ? WHERE id_reader = ?;");
				ps.setString(1, name);
				ps.setString(2, BirthDate);
				ps.setLong(3, pesel);
				ps.setString(4, nationality);
				ps.setInt(5, id);
				ps.execute();
			} catch (SQLException e) {e.printStackTrace();}	
		}
		
		public void insertAuthor(String name, int yearOfBirth) {
			try {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO Authors values (null, ?, ?);");
				ps.setString(1, name);
				ps.setInt(2, yearOfBirth);
				ps.execute();
			} catch (SQLException e) {e.printStackTrace();}	
		}
		
		public void updateAuthor(int id, String name, int yearOfBirth) {
			try {
				PreparedStatement ps = connection.prepareStatement("UPDATE Authors SET Name = ?, YearOfBirth = ? WHERE id_author = ?;");
				ps.setString(1, name);
				ps.setInt(2, yearOfBirth);
				ps.setInt(3, id);
				ps.execute();
			} catch (SQLException e) {e.printStackTrace();}	
		}
		
		public void deleteReader(int id_reader) {
			try {
				PreparedStatement ps = connection.prepareStatement("DELETE FROM Readers WHERE id_reader = ?;");
				ps.setInt(1, id_reader);
				ps.execute();
			} catch (SQLException e) {e.printStackTrace();}	
		}
		
		public void deleteAuthor(int id_author) {
			try {
				PreparedStatement ps = connection.prepareStatement("DELETE FROM Authors WHERE id_author = ?;");
				ps.setInt(1, id_author);
				ps.execute();
			} catch (SQLException e) {e.printStackTrace();}	
		}
		
		public void insertBook(String title, int id_author, int releaseYear, String category, long ISBN, boolean status) {
			try {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO Books values (null, ?, ?, ?, ?, ?, ?);");
				ps.setString(1, title);
				ps.setInt(2, id_author);
				ps.setInt(3, releaseYear);
				ps.setString(4, category);
				ps.setLong(5, ISBN);
				ps.setBoolean(6, status);
				ps.execute();
			} catch (SQLException e) {e.printStackTrace();}	
		}
		public List<Book> selectBooks() {
			List<Book> list = new ArrayList<>();
			int id_book;
			long ISBN;
			int releaseYear;
			String title, category, author;
			boolean status;
			try {
				ResultSet result = statement.executeQuery("SELECT b.id_book, b.Title, a.Name || ' ' || a.YearOfBirth AS AuthorData, b.ReleaseYear, b.Category, b.ISBN, b.Status FROM Books b INNER JOIN Authors a ON b.id_author = a.id_author;");
				while (result.next()) {
					id_book = result.getInt("id_book");
					title = result.getString("Title");
					author = result.getString("AuthorData");
					releaseYear = result.getInt("ReleaseYear");
					category = result.getString("Category");
					ISBN = result.getLong("ISBN");
					status = result.getBoolean("Status");
					list.add(new Book(id_book, title, author, releaseYear, category, ISBN, status));
				}
			} catch (SQLException e) {e.printStackTrace();}
			return list;			
		}
		
		public List<Book> selectSearchedBooks(String searchFor) {
			List<Book> list = new ArrayList<>();
			int id_book;
			long ISBN;
			int releaseYear;
			String title, category, author;
			boolean status;
			try {
				PreparedStatement ps = connection.prepareStatement("SELECT b.id_book, b.Title, a.Name || ' ' || a.YearOfBirth AS AuthorData, b.ReleaseYear, b.Category, b.ISBN, b.Status FROM Books b INNER JOIN Authors a ON b.id_author = a.id_author WHERE b.Title LIKE ? OR b.Title LIKE ? OR b.Title LIKE ? ;");
				ps.setString(1, searchFor + "%");
				ps.setString(2, "%" + searchFor);
				ps.setString(3, "%" + searchFor + "%");
				ResultSet result = ps.executeQuery();
				while (result.next()) {
					id_book = result.getInt("id_book");
					title = result.getString("Title");
					author = result.getString("AuthorData");
					releaseYear = result.getInt("ReleaseYear");
					category = result.getString("Category");
					ISBN = result.getLong("ISBN");
					status = result.getBoolean("Status");
					list.add(new Book(id_book, title, author, releaseYear, category, ISBN, status));
				}
			} catch (SQLException e) {e.printStackTrace();}
			return list;			
		}
		
		public void deleteBook(int id_book) {
			try {
				PreparedStatement ps = connection.prepareStatement("DELETE FROM Books WHERE id_book = ?;");
				ps.setInt(1, id_book);
				ps.execute();
			} catch (SQLException e) {e.printStackTrace();}	
		}
		
		public void updateBook(int id, String title, int id_author, int releaseYear, String category, long ISBN) {
			try {
				PreparedStatement ps = connection.prepareStatement("UPDATE Books SET Title = ?, id_author = ?, ReleaseYear = ?, Category = ?, ISBN = ? WHERE id_book = ?;");
				ps.setString(1, title);
				ps.setInt(2, id_author);
				ps.setInt(3, releaseYear);
				ps.setString(4, category);
				ps.setLong(5, ISBN);
				ps.setInt(6, id);
				ps.execute();
			} catch (SQLException e) {e.printStackTrace();}	
		}
		
		public List<Book> selectBooksFalse () {
			List<Book> list = new ArrayList<>();
			int id_book;
			long ISBN;
			int releaseYear;
			String title, category, author;
			boolean status;
			try {
				ResultSet result = statement.executeQuery("SELECT b.id_book, b.Title, a.Name || ' ' || a.YearOfBirth AS AuthorData, b.ReleaseYear, b.Category, b.ISBN, b.Status FROM Books b INNER JOIN Authors a ON b.id_author = a.id_author WHERE b.Status = false;");
				while (result.next()) {
					id_book = result.getInt("id_book");
					title = result.getString("Title");
					author = result.getString("AuthorData");
					releaseYear = result.getInt("ReleaseYear");
					category = result.getString("Category");
					ISBN = result.getLong("ISBN");
					status = result.getBoolean("Status");
					list.add(new Book(id_book, title, author, releaseYear, category, ISBN, status));
				}
			} catch (SQLException e) {e.printStackTrace();}
			return list;			
		}
		
		public List<Book> selectSearchedBooksFalse (String searchFor) {
			List<Book> list = new ArrayList<>();
			int id_book;
			long ISBN;
			int releaseYear;
			String title, category, author;
			boolean status;
			try {
				PreparedStatement ps = connection.prepareStatement("SELECT b.id_book, b.Title, a.Name || ' ' || a.YearOfBirth AS AuthorData, b.ReleaseYear, b.Category, b.ISBN, b.Status FROM Books b INNER JOIN Authors a ON b.id_author = a.id_author WHERE b.Status = false AND (b.Title LIKE ? OR b.Title LIKE ? OR b.Title LIKE ?);");
				ps.setString(1, searchFor + "%");
				ps.setString(2, "%" + searchFor);
				ps.setString(3, "%" + searchFor + "%");
				ResultSet result = ps.executeQuery();
				while (result.next()) {
					id_book = result.getInt("id_book");
					title = result.getString("Title");
					author = result.getString("AuthorData");
					releaseYear = result.getInt("ReleaseYear");
					category = result.getString("Category");
					ISBN = result.getLong("ISBN");
					status = result.getBoolean("Status");
					list.add(new Book(id_book, title, author, releaseYear, category, ISBN, status));
				}
			} catch (SQLException e) {e.printStackTrace();}
			return list;			
		}
		
		public void BorrowBook(int id_book) {
			try {
				PreparedStatement ps = connection.prepareStatement("UPDATE Books SET Status = true WHERE id_book = ?;");
				ps.setInt(1, id_book);
				ps.execute();
			} catch (SQLException e) {e.printStackTrace();}	
		}
		
		public void ReturnBook(int id_book) {
			try {
				PreparedStatement ps = connection.prepareStatement("UPDATE Books SET Status = false WHERE id_book = ?;");
				ps.setInt(1, id_book);
				ps.execute();
			} catch (SQLException e) {e.printStackTrace();}	
		}
		
		public void insertBorrow(int id_reader, int id_book) {
			try {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO Borrows values (null, ?, ?, DATE('NOW'), DATE('NOW','30 days'), null);");
				ps.setInt(1, id_reader);
				ps.setInt(2, id_book);
				ps.execute();
			} catch (SQLException e) {e.printStackTrace();}	
		}
		
		public void UpdateBorrowReturn(int id_borrow) {
			try {
				PreparedStatement ps = connection.prepareStatement("UPDATE Borrows SET ActualReturnDate = DATE('NOW') WHERE id_borrow = ?;");
				ps.setInt(1, id_borrow);
				ps.execute();
			} catch (SQLException e) {e.printStackTrace();}	
		}
		
		public List<Borrows> selectBorrows(String searchFor) {
			List<Borrows> list = new ArrayList<>();
			int id_borrow, id_reader, id_book;
			String borrowDate, returnDate, actualReturnDate, name, title;
			try {
				PreparedStatement ps = connection.prepareStatement("SELECT br.id_borrow, br.Id_reader, r.Name, br.Id_book, b.Title, br.BorrowDate, br.ReturnDate, br.ActualReturnDate FROM Borrows br INNER JOIN Readers r ON br.Id_reader = r.id_reader INNER JOIN Books b ON br.Id_book = b.id_book WHERE b.Title LIKE ? OR b.Title LIKE ? OR b.Title LIKE ? OR r.Name LIKE ? OR r.Name LIKE ? OR r.Name LIKE ?;");
				ps.setString(1, searchFor + "%");
				ps.setString(2, "%" + searchFor);
				ps.setString(3, "%" + searchFor + "%");
				ps.setString(4, searchFor + "%");
				ps.setString(5, "%" + searchFor);
				ps.setString(6, "%" + searchFor + "%");
				ResultSet result = ps.executeQuery();
				while (result.next()) {
					id_borrow = result.getInt("id_borrow");
					id_reader = result.getInt("Id_reader");
					name = result.getString("Name");
					id_book = result.getInt("Id_book");
					title = result.getString("Title");
					borrowDate = result.getString("BorrowDate");
					returnDate = result.getString("ReturnDate");
					actualReturnDate = result.getString("ActualReturnDate");
		
					list.add(new Borrows(id_borrow, id_reader, name, id_book, title, borrowDate, returnDate, actualReturnDate));
				}
			} catch (SQLException e) {e.printStackTrace();}
			return list;			
		}
		
		public List<ABook> selectSearchedBooksTrueForReader(int id_reader) {
			List<ABook> list = new ArrayList<>();
			int id_book;
			int id_borrow;
			String title, author, borrowDate, returnDate, actualReturnDate;
			boolean status;
			try {
				PreparedStatement ps = connection.prepareStatement("SELECT b.id_book, b.Title, a.Name || ' ' || a.YearOfBirth AS AuthorData, br.BorrowDate, br.ReturnDate, br.ActualReturnDate, b.Status, br.id_borrow FROM Books b INNER JOIN Authors a ON b.id_author = a.id_author INNER JOIN Borrows br ON b.id_book = br.Id_book WHERE b.Status = true AND br.Id_reader = ? AND br.ActualReturnDate IS NULL;");
				ps.setInt(1, id_reader);
				ResultSet result = ps.executeQuery();
				while (result.next()) {
					id_book = result.getInt("id_book");
					title = result.getString("Title");
					author = result.getString("AuthorData");
					borrowDate = result.getString("BorrowDate");
					returnDate = result.getString("ReturnDate");
					actualReturnDate = result.getString("ActualReturnDate");
					status = result.getBoolean("Status");
					id_borrow = result.getInt("id_borrow");
					list.add(new ABook(id_book, title, author, status, id_borrow, borrowDate, returnDate, actualReturnDate));
				}
			} catch (SQLException e) {e.printStackTrace();}
			return list;			
		}
		
		
		public void UpdateBorrow(int days, int id_borrow) {
			try {
				PreparedStatement ps = connection.prepareStatement("UPDATE Borrows SET ReturnDate = Date(BorrowDate, ?) WHERE id_borrow = ?;");
				ps.setString(1, days + " days");
				ps.setInt(2, id_borrow);
				ps.execute();
			} catch (SQLException e) {e.printStackTrace();}	
		}
		
		public String getBorrowDate(int id_borrow) {
			String borrowDate ="";
			try {
				PreparedStatement ps = connection.prepareStatement("SELECT BorrowDate FROM Borrows WHERE id_borrow = ?;");
				ps.setInt(1, id_borrow);
				ResultSet result = ps.executeQuery();
				borrowDate = result.getString("BorrowDate");
			} catch (SQLException e) {e.printStackTrace();}
			return borrowDate;			
		}
		
		public List<BookStat> selectBooksStat() {
			List<BookStat> list = new ArrayList<>();
			int id_book;
			long ISBN;
			int releaseYear;
			String title, category, author;
			int borrows;
			try {
				ResultSet result = statement.executeQuery("SELECT b.id_book, b.Title, a.Name || ' ' || a.YearOfBirth AS AuthorData, b.ReleaseYear, b.Category, b.ISBN, COUNT('br.id_borrow') AS NumberOfBorrows FROM Books b INNER JOIN Authors a ON b.id_author = a.id_author INNER JOIN Borrows br ON b.id_book = br.Id_book GROUP BY b.id_book ORDER BY NumberOfBorrows DESC;");
				while (result.next()) {
					id_book = result.getInt("id_book");
					title = result.getString("Title");
					author = result.getString("AuthorData");
					releaseYear = result.getInt("ReleaseYear");
					category = result.getString("Category");
					ISBN = result.getLong("ISBN");
					borrows = result.getInt("NumberOfBorrows");
					list.add(new BookStat(id_book, title, author, releaseYear, category, ISBN, borrows));
				}
			} catch (SQLException e) {e.printStackTrace();}
			return list;			
		}
	
		public List<CatStat> selectCatStat() {
			List<CatStat> list = new ArrayList<>();
			String category;
			int borrows;
			try {
				ResultSet result = statement.executeQuery("SELECT b.Category, COUNT('br.id_borrow') AS NumberOfBorrows FROM Books b INNER JOIN Authors a ON b.id_author = a.id_author INNER JOIN Borrows br ON b.id_book = br.Id_book GROUP BY b.Category ORDER BY NumberOfBorrows DESC;");
				while (result.next()) {
					category = result.getString("Category");
					borrows = result.getInt("NumberOfBorrows");
					list.add(new CatStat(category, borrows));
				}
			} catch (SQLException e) {e.printStackTrace();}
			return list;			
		}
		
		public List<ReaderStat> selectReaderStat() {
			List<ReaderStat> list = new ArrayList<>();
			int id_reader;
			long pesel;
			int birthDate;
			String name, nationality;
			int borrows;
			try {
				ResultSet result = statement.executeQuery("SELECT r.id_reader, r.Name, r.BirthDate, r.PESEL, r.Nationality, COUNT('r.id_reader') AS NumberOfBorrows FROM Readers r INNER JOIN Borrows br ON r.id_reader = br.Id_reader GROUP BY r.id_reader ORDER BY NumberOfBorrows DESC;");
				while (result.next()) {
					id_reader = result.getInt("id_reader");
					name = result.getString("Name");
					birthDate = result.getInt("BirthDate");
					pesel = result.getLong("PESEL");
					nationality = result.getString("Nationality");
					borrows = result.getInt("NumberOfBorrows");
					list.add(new ReaderStat(id_reader, name, birthDate, pesel, nationality, borrows));
				}
			} catch (SQLException e) {e.printStackTrace();}
			return list;			
		}
		
		public List<AuthorStat> selectAuthorStat() {
			List<AuthorStat> list = new ArrayList<>();
			String name;
			int yearOfBirth;
			int id_author, borrows;
			try {
				ResultSet result = statement.executeQuery("SELECT a.id_author, a.Name, a.YearOfBirth, COUNT('br.id_borrow') AS NumberOfBorrows FROM Authors a INNER JOIN Books b ON a.id_author = b.id_author INNER JOIN Borrows br ON b.id_book = br.Id_book GROUP BY a.id_author ORDER BY NumberOfBorrows DESC;");
				while (result.next()) {
					id_author = result.getInt("id_author");
					name = result.getString("Name");
					yearOfBirth = result.getInt("YearOfBirth");
					borrows = result.getInt("NumberOfBorrows");
					list.add(new AuthorStat(id_author, name, yearOfBirth, borrows));
				}
			} catch (SQLException e) {e.printStackTrace();}
			return list;			
		}
		
}
