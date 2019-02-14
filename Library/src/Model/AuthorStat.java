package Model;

public class AuthorStat {

		
	private int id_author;
	private String name;
	private int yearOfBirth;
	private int borrows;
	
	public AuthorStat(int id_author, String name, int yearOfBirth, int borrows) {
		
		this.id_author = id_author;
		this.name = name;
		this.yearOfBirth = yearOfBirth;
		this.borrows=borrows;
	}

	public int getBorrows() {
		return borrows;
	}

	public void setBorrows(int borrows) {
		this.borrows = borrows;
	}

	public int getId_author() {
		return id_author;
	}

	public void setId_author(int id_author) {
		this.id_author = id_author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	
	public String toString() {
		return name + " " + yearOfBirth;
	}
	
}
