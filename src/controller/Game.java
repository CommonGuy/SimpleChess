package controller;

public class Game {
	private static final int MAX_TURNS_WITHOUT_CAPTURES = 100; //=50, counts for both teams
	private static final int MAX_SECONDS = 2;
	private Board board;
	private Player[] players = new Player[2];
	private int turnsWithoutCaptures = 0;
	private boolean draw = false;
	
	public Game(Player player1, Player player2) {
		board = new Board();
		board.initializePieces();
		players[0] = player1;
		players[0].setTeam(Color.WHITE);
		players[1] = player2;
		players[1].setTeam(Color.BLACK);
	}
	
	int run() {
		int i = 0;
		while (!gameOver()) {
			if (!turnAvailable(players[i])) {
				draw = true;
			} else {
				long start = System.currentTimeMillis();
				makeTurn(players[i]);
				if ((System.currentTimeMillis() - start) / 1000 > MAX_SECONDS) {
					players[i].setDisqualified();
				}
				i = (i + 1) % 2;
			}
		}
		if (wins(players[0]) && !wins(players[1])) {
			return Controller.WIN_POINTS;
		} else if (wins(players[1]) && !wins(players[0])) {
			return Controller.LOSE_POINTS;
		} else {
			return Controller.DRAW_POINTS;
		}
	}
	
	private boolean wins(Player player) {
		if (player.isDisqualified() || board.getKing(player) == null) {
			return true;
		}
		return false;
	}
	
	// player can make a turn
	private boolean turnAvailable(Player player) {
		for (Piece piece : player.getPieces(board.getOriginalFields())) {
			if (piece.getValidDestinationSet(board.getOriginalFields()).size() > 0) {
				return true;
			}
		}
		return false;
	}
	
	private void makeTurn(Player player) {
		player.setCheck(isCheck(player));
		try {
			Move move = player.getMove(board.getFields());
			if (move.isValid(board.getOriginalFields())) {
				if (board.movePiece(move) || move.getPiece().getType() == PieceType.PAWN) {
					turnsWithoutCaptures = 0;
				} else {
					turnsWithoutCaptures++;
				}
			}
		} catch (Exception e) {
			player.setDisqualified();
		}
		
	}
	
	public boolean isCheck(Player player) {
		Piece king = board.getKing(player);
		Point pos = king.getPos();
		Color enemy = king.getTeam().opposite();
		Field[][] fields = board.getFields();
		
		for (Field[] rows : board.getOriginalFields()) {
			for (Field field : rows) {
				if (field.hasPiece()) {
					Piece piece = field.getPiece();
					if (piece.getTeam() == enemy && 
							piece.getValidDestinationSet(fields).contains(pos)){
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean gameOver() {
		for (Player player : players) {
			if (player.isDisqualified() || board.getKing(player) == null
					|| turnsWithoutCaptures >= MAX_TURNS_WITHOUT_CAPTURES || draw) {
				return true;
			}
		}
		return false;
	}
}
