package controller;

public class Move {
	private Piece piece;
	private Point destination;
	
	public Move(Piece piece, Point destination) {
		this.piece = piece;
		this.destination = destination;
	}

	public boolean isValid(Board board, Player player) {
		if (piece == null || destination == null || player.getTeam() != piece.getTeam()) {
			return false;
		}
		
		if (!pieceExists(board, piece)) {
			return false;
		}
		
		if (piece.getValidDestinationSet(board).contains(destination)) {
			return true;
		}
		return false;
	}
	
	private boolean pieceExists(Board board, Piece piece) {
		Point pos = piece.getPos();
		if (pos != null && !piece.getPos().isOutside()) {
			Field field = board.getFields()[pos.getX()][pos.getY()];
			if (field.hasPiece() && field.getPiece().equals(piece)) {
				return true;
			}
		}
		return false;
	}
	
	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public Point getDestination() {
		return destination;
	}

	public void setDestination(Point destination) {
		this.destination = destination;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((piece == null) ? 0 : piece.hashCode());
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
		Move other = (Move) obj;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (piece == null) {
			if (other.piece != null)
				return false;
		} else if (!piece.equals(other.piece))
			return false;
		return true;
	}		

	@Override
	public String toString() {
		return piece.toString() + " moves to " + destination.toString();
	}
}
