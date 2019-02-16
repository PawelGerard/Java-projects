
package Graphics;

public class AIPossibilities {
    private int firstX;
    private int firstY;
    private int xDirection;
    private int yDirection;
    private int circleAmount;
    private int crossAmount;
    private int availableFields;
    private int FirstSumLast;

    public AIPossibilities(int firstX, int firstY, int xDirection, int yDirection, int circleAmount, int crossAmount, int FirstSumLast) {
        this.firstX = firstX;
        this.firstY = firstY;
        this.xDirection = xDirection;
        this.yDirection = yDirection;
        this.circleAmount = circleAmount;
        this.crossAmount = crossAmount;
        this.availableFields = 5 - circleAmount - crossAmount;
        this.FirstSumLast = FirstSumLast;
    }

	public int getFirstX() {
		return firstX;
	}

	public void setFirstX(int firstX) {
		this.firstX = firstX;
	}

	public int getFirstY() {
		return firstY;
	}

	public void setFirstY(int firstY) {
		this.firstY = firstY;
	}

	public int getxDirection() {
		return xDirection;
	}

	public void setxDirection(int xDirection) {
		this.xDirection = xDirection;
	}

	public int getyDirection() {
		return yDirection;
	}

	public void setyDirection(int yDirection) {
		this.yDirection = yDirection;
	}

	public int getCircleAmount() {
		return circleAmount;
	}

	public void setCircleAmount(int circleAmount) {
		this.circleAmount = circleAmount;
	}

	public int getCrossAmount() {
		return crossAmount;
	}

	public void setCrossAmount(int crossAmount) {
		this.crossAmount = crossAmount;
	}

	public int getAvailableFields() {
		return availableFields;
	}

	public void setAvailableFields(int availableFields) {
		this.availableFields = availableFields;
	}

	public int getFirstSumLast() {
		return FirstSumLast;
	}

	public void setFirstSumLast(int firstSumLast) {
		FirstSumLast = firstSumLast;
	}

}  
