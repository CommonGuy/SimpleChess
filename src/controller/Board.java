package controller;

import java.util.Arrays;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;

public class Board {
	private Field[][] fields;
	public static final int BOARD_LENGTH = 8;
	
	public Board() {
		fields = new Field[BOARD_LENGTH][BOARD_LENGTH];
	}
	
	public Field[][] getFields() {
		return fields;
	}
	
	//returns whether a piece got captured
	public boolean movePiece(Move move) {
		boolean capture = false;
		Piece piece = move.getPiece();
		Point dest = move.getDestination();
		if (!dest.isOutside()) {
			capture = fields[dest.getX()][dest.getY()].hasPiece();
			// upgrade pawn
			if (piece.getType() == PieceType.PAWN && (dest.getY() == 0 || dest.getY() == BOARD_LENGTH - 1)) {
				fields[dest.getX()][dest.getY()].setPiece(new Queen(piece.getTeam(), dest));
			} else {
				fields[dest.getX()][dest.getY()].setPiece(piece);
			}
			if (piece.getType() == PieceType.PAWN) {
				((Pawn)piece).setMoved();
			}
			//remove piece on old field
			fields[piece.getPos().getX()][piece.getPos().getY()].setPiece(null);
			//update position
			piece.setPos(dest);
		}
		return capture;
	}
	
	public boolean isCheck(Player player, Player enemy) {
		Piece king = getKing(player);
		Point pos = king.getPos();
		
		for (Piece piece : enemy.getPieces(this)) {
			if (piece.getValidDestinationSet(this).contains(pos)) {
				return true;
			}
		}
		return false;
	}
	
	public Piece getKing(Player player) {
		for (Piece piece : player.getPieces(this)) {
			if (piece.getType() == PieceType.KING) {
				return piece;
			}
		}

		return null; //player lost the game
	}
	
	public Board copy() {
		Board copy = new Board();
		for (int i = 0; i < BOARD_LENGTH; i++) {
			for (int j = 0; j < BOARD_LENGTH; j++) {
				copy.fields[i][j] = fields[i][j].copy();
			}
		}
		return copy;
	}
	
	void initialize() {
		for (int i = 0; i < BOARD_LENGTH; i++) {
			for (int j = 0; j < BOARD_LENGTH; j++) {
				Color color = (i + j) % 2 == 0 ? Color.BLACK : Color.WHITE;
				fields[i][j] = new Field(new Point(i, j), color);
			}
		}
		
		fields[0][0].setPiece(new Rook(Color.WHITE, new Point(0,0)));
		fields[1][0].setPiece(new Knight(Color.WHITE, new Point(1,0)));
		fields[2][0].setPiece(new Bishop(Color.WHITE, new Point(2,0)));
		fields[3][0].setPiece(new Queen(Color.WHITE, new Point(3,0)));
		fields[4][0].setPiece(new King(Color.WHITE, new Point(4,0)));
		fields[5][0].setPiece(new Bishop(Color.WHITE, new Point(5,0)));
		fields[6][0].setPiece(new Knight(Color.WHITE, new Point(6,0)));
		fields[7][0].setPiece(new Rook(Color.WHITE, new Point(7,0)));
		
		fields[0][7].setPiece(new Rook(Color.BLACK, new Point(0,7)));
		fields[1][7].setPiece(new Knight(Color.BLACK, new Point(1,7)));
		fields[2][7].setPiece(new Bishop(Color.BLACK, new Point(2,7)));
		fields[3][7].setPiece(new Queen(Color.BLACK, new Point(3,7)));
		fields[4][7].setPiece(new King(Color.BLACK, new Point(4,7)));
		fields[5][7].setPiece(new Bishop(Color.BLACK, new Point(5,7)));
		fields[6][7].setPiece(new Knight(Color.BLACK, new Point(6,7)));
		fields[7][7].setPiece(new Rook(Color.BLACK, new Point(7,7)));
		
		//pawns
		for (int i = 0; i < BOARD_LENGTH; i++) {
			fields[i][1].setPiece(new Pawn(Color.WHITE, new Point(i, 1)));
			fields[i][6].setPiece(new Pawn(Color.BLACK, new Point(i, 6)));
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for (int j = BOARD_LENGTH - 1; j >= 0; j--) {
			for (int i = 0; i < BOARD_LENGTH; i++) {
				builder.append(fields[i][j]);
			}
			builder.append("\n");
		}
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(fields);
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
		Board other = (Board) obj;
		if (!Arrays.deepEquals(fields, other.fields))
			return false;
		return true;
	}	
}
