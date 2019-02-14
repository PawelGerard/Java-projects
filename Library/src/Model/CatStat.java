package Model;

public class CatStat {

	private String category;
	private int borrows;
	
	public CatStat(String category, int borrows) {
		super();
		this.category = category;
		this.borrows = borrows;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getBorrows() {
		return borrows;
	}

	public void setBorrows(int borrows) {
		this.borrows = borrows;
	}
	
	
}
