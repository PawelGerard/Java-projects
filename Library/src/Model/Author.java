package Model;

public class Author {

		
	private int id_author;
	private String name;
	private int yearOfBirth;
	
	public Author(int id_author, String name, int yearOfBirth) {
		
		this.id_author = id_author;
		this.name = name;
		this.yearOfBirth = yearOfBirth;
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
