package controller;

public class Field {
	private final Point pos;
	private final Color color;
	private Piece piece;
	
	public Field(Point pos, Color color) {
		this.pos = pos;
		this.color = color;
	}

	public Piece getPiece() {
		return piece;
	}
	
	public boolean hasPiece() {
		return piece != null;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public Point getPos() {
		return pos;
	}

	public Color getColor() {
		return color;
	}
	
	public Field copy() {
		Field copy = new Field(pos.copy(), color);
		if (hasPiece()) {
			copy.setPiece(piece.copy());
		}
		return copy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((piece == null) ? 0 : piece.hashCode());
		result = prime * result + ((pos == null) ? 0 : pos.hashCode());
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
		Field other = (Field) obj;
		if (color != other.color)
			return false;
		if (piece == null) {
			if (other.piece != null)
				return false;
		} else if (!piece.equals(other.piece))
			return false;
		if (pos == null) {
			if (other.pos != null)
				return false;
		} else if (!pos.equals(other.pos))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return hasPiece() ? piece.getType().getInitial()
				+ piece.getTeam().getInitial() + " " : " . ";
	}
}
