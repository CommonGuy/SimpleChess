package controller;

import static controller.Board.BOARD_LENGTH;

import java.util.HashSet;
import java.util.Set;

public abstract class Piece {
	private final Color team;
	private Point pos;
	private final PieceType type;

	public Piece(Color team, Point pos, PieceType type) {
		this.team = team;
		this.pos = pos;
		this.type = type;
	}
	
	public Color getTeam() {
		return team;
	}

	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public PieceType getType() {
		return type;
	}
	
	public abstract Set<Point> getValidDestinationSet(Board board);
	
	public Point[] getValidDestinations(Board board) {
		Set<Point> dests = getValidDestinationSet(board);
		return dests.toArray(new Point[dests.size()]);
	}
	
	// vert = vertical, neg = negative, hor = horizontal
	protected Set<Point> getDests(Field[][] fields, boolean vert, boolean negVert, boolean hor, boolean negHor) {
		Set<Point> dests = new HashSet<>();
		Point pos = getPos();
		Color enemy = getTeam().opposite();
		
		for (int offset = 1; offset < BOARD_LENGTH; offset++) {
			int dx = !hor ? 0 : 
				   negHor ? -offset : offset;
			int dy = !vert ? 0 : 
				   negVert ? -offset : offset;
			Point futurePos = pos.add(dx, dy);
			if (futurePos.isOutside()) {
				break;
			}
			Field field = fields[futurePos.getX()][futurePos.getY()];
			if (!field.hasPiece()) {
				dests.add(futurePos);
			} else if (field.getPiece().getTeam() == enemy) {
				dests.add(futurePos);
				break;
			} else {
				break;
			}				
		}
		return dests;
	}
	
	public abstract Piece copy();
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pos == null) ? 0 : pos.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Piece other = (Piece) obj;
		if (pos == null) {
			if (other.pos != null)
				return false;
		} else if (!pos.equals(other.pos))
			return false;
		if (team != other.team)
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return team + " " + type + " on " + pos.toString();
	}
}
