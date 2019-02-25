package Model;

public class ABook {

	private int id_book;
	private String title;
	private String author;
	private boolean status;
	private int id_borrow;
	String borrowDate, returnDate, actualReturnDate;
	
	public ABook(int id_book, String title, String author, boolean status, int id_borrow, String borrowDate, String returnDate, String actualReturnDate) {
		this.id_book = id_book;
		this.title = title;
		this.author = author;
		this.status = status;
		this.id_borrow = id_borrow;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
		this.actualReturnDate = actualReturnDate;
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

	public int getId_borrow() {
		return id_borrow;
	}

	public void setId_borrow(int id_borrow) {
		this.id_borrow = id_borrow;
	}

	public int getId_book() {
		return id_book;
	}

	public void setId_book(int id_book) {
		this.id_book = id_book;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
}
