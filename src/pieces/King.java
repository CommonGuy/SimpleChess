package pieces;

import controller.*;

import java.util.Set;
import java.util.HashSet;

public class King extends Piece {

	public King(Color team, Point pos) {
		super(team, pos, PieceType.KING);
	}

	@Override
	public Set<Point> getValidDestinationSet(Field[][] fields) {
		Set<Point> dests = new HashSet<>();
		Point pos = getPos();
		Color enemy = getTeam().opposite();
		
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				Point futurePos = pos.add(x, y);
				if (!futurePos.isOutside()) {
					Field field = fields[futurePos.getX()][futurePos.getY()];
					if (!field.hasPiece() || field.getPiece().getTeam() == enemy) {
						dests.add(futurePos);
					}
				}
			}
		}
		return dests;
	}

}
