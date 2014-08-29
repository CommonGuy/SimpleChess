package player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import controller.Field;
import controller.Move;
import controller.Player;
import controller.Point;

public class TestPlayer extends Player {

	@Override
	public Move getMove(Field[][] board) {
		List<Move> possibleMoves = new ArrayList<>();
		
		for (Field[] rows : board) {
			for (Field field : rows) {
				if (field.hasPiece() && field.getPiece().getTeam() == getTeam()) {
					for (Point p : field.getPiece().getValidDestinations(board)) {
						Move move = new Move(field.getPiece(), p);
						possibleMoves.add(move);
					}
				}
			}
		}
		
		int rnd = new Random().nextInt(possibleMoves.size());
		return possibleMoves.get(rnd);
	}

}
