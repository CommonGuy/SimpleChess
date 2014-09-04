package pieces;

import java.util.HashSet;
import java.util.Set;

import controller.Board;
import controller.Color;
import controller.Field;
import controller.Piece;
import controller.PieceType;
import controller.Point;

public class Queen extends Piece {

	public Queen(Color team, Point pos) {
		super(team, pos, PieceType.QUEEN);
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
		
		//diagonal fields
		dests.addAll(super.getDests(fields, true, false, true, false));
		dests.addAll(super.getDests(fields, true, true, true, true));
		dests.addAll(super.getDests(fields, true, true, true, false));
		dests.addAll(super.getDests(fields, true, false, true, true));
		
		return dests;
	}
	
	public Piece copy() {
		return new Queen(getTeam(), getPos().copy());
	}

}
