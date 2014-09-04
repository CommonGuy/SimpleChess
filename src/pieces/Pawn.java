package pieces;

import controller.*;

import java.util.Set;
import java.util.HashSet;

public class Pawn extends Piece {
	private boolean hasMoved = false;

	public Pawn(Color team, Point pos) {
		super(team, pos, PieceType.PAWN);
	}

	@Override
	public Set<Point> getValidDestinationSet(Board board) {
		Set<Point> dests = new HashSet<>();		
		Field[][] fields = board.getFields();
		
		int offsetY = getTeam() == Color.WHITE ? 1 : -1;
		Point pos = getPos();
		
		Point oneStepPos = pos.add(0, offsetY);
		if (isEmpty(fields, oneStepPos)) {
			dests.add(oneStepPos);
			if (!hasMoved) {
				Point twoStepPos = oneStepPos.add(0, offsetY);
				if (isEmpty(fields, twoStepPos)) {
					dests.add(twoStepPos);
				}
			}
		}
		Point attackPos = oneStepPos.add(1, 0);
		if (hasEnemy(fields, attackPos)) {
			dests.add(attackPos);
		}
		attackPos = oneStepPos.add(-1, 0);
		if (hasEnemy(fields, attackPos)) {
			dests.add(attackPos);
		}		
		
		return dests;
	}
	
	public boolean hasMoved() {
		return hasMoved;
	}
	
	public void setMoved() {
		hasMoved = true;
	}
	
	private boolean isEmpty(Field[][] fields, Point pos) {
		if (!pos.isOutside()) {
			if (!fields[pos.getX()][pos.getY()].hasPiece()) {
				return true;
			}
		}
		return false;
	}

	private boolean hasEnemy(Field[][] fields, Point pos) {
		if(!pos.isOutside()) {
			Field field = fields[pos.getX()][pos.getY()];
			if (field.hasPiece() && field.getPiece().getTeam() == getTeam().opposite()) {
				return true;
			}
		}
		return false;
	}
	
	public Piece copy() {
		Pawn copy = new Pawn(getTeam(), getPos().copy());
		if (hasMoved) {
			copy.setMoved();
		}
		return copy;
	}
}
