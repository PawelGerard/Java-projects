package Snake;

//Class to define with what label we dealing with
//Standard model class with two variables, constructor, getters and setters methods

public class Coordinates {

	private int xCord;
	private int yCord;
	
	public Coordinates(int xCord, int yCord) {
		super();
		this.xCord = xCord;
		this.yCord = yCord;
	}
	
	public int getxCord() {
		return xCord;
	}
	public void setxCord(int xCord) {
		this.xCord = xCord;
	}
	public int getyCord() {
		return yCord;
	}
	public void setyCord(int yCord) {
		this.yCord = yCord;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + xCord;
		result = prime * result + yCord;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (xCord != other.xCord)
			return false;
		if (yCord != other.yCord)
			return false;
		return true;
	}
	
	
	
	
}
