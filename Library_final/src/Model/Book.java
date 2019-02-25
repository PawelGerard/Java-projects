package Model;

public class Book {

	private int id_book;
	private String title;
	private String author;
	private int releaseYear;
	private String category;
	private long isbn;
	private boolean status;
	
	public Book(int id_book, String title, String author, int releaseYear,
			String category, long isbn, boolean status) {
		this.id_book = id_book;
		this.title = title;
		this.author = author;
		this.releaseYear = releaseYear;
		this.category = category;
		this.isbn = isbn;
		this.status = status;
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

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
}
