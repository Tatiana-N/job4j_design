package ru.job4j.solid.tictactoe.game;

import ru.job4j.solid.tictactoe.api.Drawable;

public class Field {
	private final Drawable[][] table = new Drawable[3][3];
	
	public Field() {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table.length; j++) {
				table[i][j] = new Space();
			}
		}
	}
	
	public Drawable[][] getTable() {
		return table;
	}
}
