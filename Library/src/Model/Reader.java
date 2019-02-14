package Model;

public class Reader {

	private int id_reader;
	private String name;
	private String birthDate;
	private long pesel;
	private String nationaliaty;
	
	public Reader(int id_reader, String name, String birthDate, long pesel,
			String nationaliaty) {
		this.id_reader = id_reader;
		this.name = name;
		this.birthDate = birthDate;
		this.pesel = pesel;
		this.nationaliaty = nationaliaty;
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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
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
