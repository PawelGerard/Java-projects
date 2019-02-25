package Model;

public class Borrows {

	private int id_borrow;
	private int id_reader;
	private String name;
	private int id_book;
	private String title;
	private String borrowDate;
	private String returnDate;
	private String actualReturnDate;
	
	public Borrows(int id_borrow, int id_reader, String name, int id_book,
			String title, String borrowDate, String returnDate, String actualReturnDate) {
		this.id_borrow = id_borrow;
		this.id_reader = id_reader;
		this.name = name;
		this.id_book = id_book;
		this.title = title;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
		this.actualReturnDate = actualReturnDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId_borrow() {
		return id_borrow;
	}

	public void setId_borrow(int id_borrow) {
		this.id_borrow = id_borrow;
	}

	public int getId_reader() {
		return id_reader;
	}

	public void setId_reader(int id_reader) {
		this.id_reader = id_reader;
	}

	public int getId_book() {
		return id_book;
	}

	public void setId_book(int id_book) {
		this.id_book = id_book;
	}

	public String getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getActualReturnDate() {
		return actualReturnDate;
	}

	public void setActualReturnDate(String actualReturnDate) {
		this.actualReturnDate = actualReturnDate;
	}

	
	
}
