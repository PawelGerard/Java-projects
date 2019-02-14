package Model;

public class BookStat {

	private int id_book;
	private String title;
	private String author;
	private int releaseYear;
	private String category;
	private long isbn;
	private int borrows;
	
	public BookStat(int id_book, String title, String author, int releaseYear,
			String category, long isbn, int borrows) {
		this.id_book = id_book;
		this.title = title;
		this.author = author;
		this.releaseYear = releaseYear;
		this.category = category;
		this.isbn = isbn;
		this.borrows = borrows;
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

	public int getBorrows() {
		return borrows;
	}

	public void setBorrows(int borrows) {
		this.borrows = borrows;
	}
	
	
}
