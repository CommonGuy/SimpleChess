package controller;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
	private Color team;
	private boolean check = false;
	private boolean disqualified = false;
	
	public final List<Piece> getPieces(Board board) {
		List<Piece> pieces = new ArrayList<>();
		
		for (Field[] rows : board.getFields()) {
			for (Field field : rows) {
				if (field.hasPiece()) {
					Piece piece = field.getPiece();
					if (piece.getTeam() == getTeam()) {
						pieces.add(piece);
					}
				}
			}
		}
		return pieces;
	}
		
	final void setTeam(Color team) {
		this.team = team;
	}
	
	public final Color getTeam() {
		return team;
	}
	
	final void setDisqualified() {
		disqualified = true;
	}
	
	public final boolean isDisqualified() {
		return disqualified;
	}
	
	public void setCheck(boolean check) {
		this.check = check;
	}
	
	public final boolean isCheck() {
		return check;
	}
	
	public abstract Move getMove(Board board, Player enemy);
	
	public final String toString() {
		return "Player " + getClass() + " (" + getTeam() + ")";
	}
}
