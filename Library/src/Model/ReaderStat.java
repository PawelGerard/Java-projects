package Model;

public class ReaderStat {

	private int id_reader;
	private String name;
	private int birthDate;
	private long pesel;
	private String nationaliaty;
	private int borrows;
	
	public ReaderStat(int id_reader, String name, int birthDate, long pesel,
			String nationaliaty, int borrows) {
		this.id_reader = id_reader;
		this.name = name;
		this.birthDate = birthDate;
		this.pesel = pesel;
		this.nationaliaty = nationaliaty;
		this.borrows = borrows;
	}

	public int getBorrows() {
		return borrows;
	}

	public void setBorrows(int borrows) {
		this.borrows = borrows;
	}

	public int getId_reader() {
		return id_reader;
	}

	public void setId_reader(int id_reader) {
		this.id_reader = id_reader;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(int birthDate) {
		this.birthDate = birthDate;
	}

	public long getPesel() {
		return pesel;
	}

	public void setPesel(long pesel) {
		this.pesel = pesel;
	}

	public String getNationaliaty() {
		return nationaliaty;
	}

	public void setNationaliaty(String nationaliaty) {
		this.nationaliaty = nationaliaty;
	}
	
}
