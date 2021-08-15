package ru.job4j.solid.tictactoe;

import ru.job4j.solid.tictactoe.game.Game;

public class Application {
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
}
