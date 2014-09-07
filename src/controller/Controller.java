package controller;

import java.util.HashMap;
import java.util.Map;
import player.*;

public class Controller {
	public static final int WIN_POINTS = 3;
	public static final int DRAW_POINTS = 1;
	public static final int LOSE_POINTS = 0;
	private static final int GAMES_PER_PAIR = 10;
	private final Class[] classes = {SimplePlayer.class, TestPlayer.class};
	private final Map<Class<? extends Player>, Integer> scores = new HashMap<>();

	public static void main(String... args) {
		new Controller().generateResult();
	}
	
	public Controller() {
		for (Class player : classes) {
			scores.put(player, 0);
		}
	}
	
	private void generateResult() {
		
		for (int i = 0; i < classes.length - 1; i++) {
			for (int j = i + 1; j < classes.length; j++) {
				for (int k = 0; k < GAMES_PER_PAIR; k++) {
					runGame(classes[i], classes[j], k>=GAMES_PER_PAIR/2);
				}
			}
		}
		
		printScores();
	}
	
	private void runGame(Class class1, Class class2, boolean switchSides) {
		if (switchSides) { //switch sides
			Class tempClass = class2;
			class2 = class1;
			class1 = tempClass;
		}
		try {
			Player player1 = (Player) class1.newInstance();
			Player player2 = (Player) class2.newInstance();
			int result = new Game(player1, player2).run();

			addResult(class1, result, false);
			addResult(class2, result, true);
		} catch (Exception e) {
			System.out.println("Error in game betwenn " + class1 + " and " + class2);
		}
	}
	
	private void addResult(Class player, int result, boolean reverse) {
		if (reverse) {
			if (result == WIN_POINTS) {
				result = LOSE_POINTS;
			} else if (result == LOSE_POINTS) {
				result = WIN_POINTS;
			}
		}
		int newScore = scores.get(player) + result;
		scores.put(player, newScore);
	}
	
	private void printScores() {
		int bestScore = 0;
		Class currPlayer = null;
		int place = 1;
		
		while (scores.size() > 0) {
			bestScore = 0;
			currPlayer = null;
			for (Class player : scores.keySet()) {
				int playerScore = scores.get(player);
				if (scores.get(player) >= bestScore) {
					bestScore = playerScore;
					currPlayer = player;
				}
			}
			System.out.println(String.format("%02d", place++) + ") " + currPlayer + ": " + bestScore);
			scores.remove(currPlayer);
		}
	}
}
