package ru.job4j.solid.tictactoe.game;

import java.util.List;

public class Game {
	
	public void start() {
		Field field = new Field();
		Validator validator = new Validator(field);
		ContactWithPlayer contactWithPlayer = new ContactWithPlayer(field, validator);
		List<Player> players = contactWithPlayer.getPlayers();
		contactWithPlayer.drawField();
		outer:
		while (true) {
			for (Player player : players) {
				int[] step = contactWithPlayer.getMove(player);
				field.doMove(step, player);
				contactWithPlayer.drawField();
				if (validator.hasWinner()) {
					System.out.println("Игрок по имени " + player.getName() + " - выиграл в этой партии");
					break outer;
				}
				if (!validator.hasStep()) {
					System.out.println("Никто не выиграл в этой партии");
					break outer;
				}
			}
		}
	}
}
