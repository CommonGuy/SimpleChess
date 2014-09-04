package player;

import java.util.List;
import controller.Board;
import controller.Move;
import controller.Piece;
import controller.Player;
import controller.Point;

public class SimplePlayer extends Player {

	@Override
	public Move getMove(Board board, Player enemy) {
		//get all pieces of this player
		List<Piece> pieces = this.getPieces(board);
		for (Piece piece : pieces) {
			Point[] destinations = piece.getValidDestinations(board);
			if (destinations.length > 0) {
				return new Move(piece, destinations[0]);
			}
		}

		//should never happen, because the game is over then
		return null;
	}

}
