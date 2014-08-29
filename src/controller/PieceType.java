package controller;

public enum PieceType {
	PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING;
	
	public String getInitial() {
		if (this == PieceType.KING) {
			return "^";
		}
		return this.toString().charAt(0) + "";
	}
}
