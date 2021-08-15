package ru.job4j.solid.tictactoe.game;

import ru.job4j.solid.tictactoe.api.Drawable;

import java.util.Arrays;

public class Validator {
	private final Field field;
	
	public Validator(Field field) {
		this.field = field;
	}
	
	public boolean hasWinner() {
		Drawable[][] table = field.getTable();
		return table[0][0].equals(table[0][1]) && table[0][1].equals(table[0][2]) || table[1][0].equals(table[1][1]) && table[1][1].equals(table[1][2]) || table[2][0].equals(table[2][1]) && table[2][1].equals(table[2][2]) || table[0][0].equals(table[1][1]) && table[1][1].equals(table[2][2]) || table[0][2].equals(table[1][1]) && table[1][1].equals(table[2][0]) || table[0][0].equals(table[1][0]) && table[1][0].equals(table[2][0]) || table[0][1].equals(table[1][1]) && table[1][1].equals(table[2][1]) || table[0][2].equals(table[1][2]) && table[1][2].equals(table[2][2]);
	}
	
	public boolean hasStep() {
		Drawable[][] table = field.getTable();
		return Arrays.stream(table).anyMatch(t -> Arrays.stream(t).anyMatch(r -> r instanceof Space));
	}
	
	public boolean checkStep(int[] step) {
		Drawable[][] table = field.getTable();
		if (step[0] < 1 || step[1] < 1 || step[0] > table.length || step[1] > table[0].length || table[step[0] - 1][step[1] - 1] instanceof Circle || table[step[0] - 1][step[1] - 1] instanceof Crosse) {
			System.out.println("неверно задан ход, попробуйте еще раз");
			return false;
		}
		return true;
	}
}
