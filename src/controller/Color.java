package controller;

public enum Color {
	WHITE, BLACK;
	
	public Color opposite() {
		return this == Color.WHITE ? Color.BLACK : Color.WHITE;
	}
	
	public String getInitial() {
		return this.toString().charAt(0) + "";
	}
}
