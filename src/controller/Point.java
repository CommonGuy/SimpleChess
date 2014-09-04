package controller;

public class Point {
	private int x;
	private int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Point add(int offsetX, int offsetY) {
		return new Point(x + offsetX, y + offsetY);
	}
	
	public boolean isOutside() {
		int maxLength = Board.BOARD_LENGTH - 1;
		return x < 0 || y < 0 || x > maxLength || y > maxLength;
	}

	public Point copy() {
		return new Point(x, y);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "x: " + x + ", y: " + y;
	}
	
	
}
