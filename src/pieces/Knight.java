package pieces;

import controller.*;

import java.util.Set;
import java.util.HashSet;

public class Knight extends Piece {

	public Knight(Color team, Point pos) {
		super(team, pos, PieceType.KNIGHT);
	}

	@Override
	public Set<Point> getValidDestinationSet(Board board) {
		Set<Point> dests = new HashSet<>();		
		Field[][] fields = board.getFields();
		Point pos = getPos();
		Color enemy = getTeam().opposite();
		
		Set<Point> futurePositions = new HashSet<>();
		futurePositions.add(pos.add(2,1));
		futurePositions.add(pos.add(2,-1));
		futurePositions.add(pos.add(-2,1));
		futurePositions.add(pos.add(-2,-1));
		futurePositions.add(pos.add(1,2));
		futurePositions.add(pos.add(-1,2));
		futurePositions.add(pos.add(1,-2));
		futurePositions.add(pos.add(-1,-2));

		for (Point futurePos : futurePositions) {
			if (!futurePos.isOutside()) {
				Field field = fields[futurePos.getX()][futurePos.getY()];
				if (!field.hasPiece() || field.getPiece().getTeam() == enemy) {
					dests.add(futurePos);
				}
			}
		}
		return dests;
	}
	
	public Piece copy() {
		return new Knight(getTeam(), getPos().copy());
	}

}
