package pieces;

import controller.*;

import java.util.Set;
import java.util.HashSet;

public class Rook extends Piece {

	public Rook(Color team, Point pos) {
		super(team, pos, PieceType.ROOK);
	}

	@Override
	public Set<Point> getValidDestinationSet(Board board) {
		Set<Point> dests = new HashSet<>();		
		Field[][] fields = board.getFields();
		
		//horizontal fields
		dests.addAll(super.getDests(fields, true, false, false, false));
		dests.addAll(super.getDests(fields, true, true, false, false));
		
		//vertical fields
		dests.addAll(super.getDests(fields, false, false, true, false));
		dests.addAll(super.getDests(fields, false, false, true, true));
		
		return dests;
	}
	
	public Piece copy() {
		return new Rook(getTeam(), getPos().copy());
	}
}
