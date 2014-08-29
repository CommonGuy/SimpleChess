package player;

import controller.*;

public class SimplePlayer extends Player {

	@Override
	public Move getMove(Field[][] board) {

		for (Field[] rows : board) {
			for (Field field : rows) {
				if (field.hasPiece() && field.getPiece().getTeam() == getTeam()) {
					Point[] destinations = 
							field.getPiece().getValidDestinations(board);
					if (destinations.length > 0) {
						return new Move(field.getPiece(), destinations[0]);
					}
				}
			}
		}
		//should never happen, because it's a tie then
		return null;
	}

}
