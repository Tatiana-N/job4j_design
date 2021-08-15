package ru.job4j.solid.tictactoe.game;

import ru.job4j.solid.tictactoe.api.Drawable;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ContactWithPlayer {
	private final Field field;
	private final Scanner scanner = new Scanner(System.in);
	private final Validator validator;
	
	public ContactWithPlayer(Field field, Validator validator) {
		this.field = field;
		this.validator = validator;
	}
	
	public int[] getMove(Player player) {
		int[] step = new int[2];
		do {
			System.out.println(player.getName() + " введите ход");
			step[0] = scanner.nextInt();
			step[1] = scanner.nextInt();
		} while (!validator.checkStep(step));
		return step;
	}
	
	public void drawField() {
		Drawable[][] table = field.getTable();
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[0].length; j++) {
				table[i][j].draw();
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public List<Player> getPlayers() {
		System.out.print("Введите имя первого игрока,который будет играть ноликами : ");
		Player player1 = new Player(scanner.nextLine(), new Circle());
		System.out.print("Введите имя второго игрока,который будет играть крестиками : ");
		Player player2 = new Player(scanner.nextLine(), new Crosse());
		return Arrays.asList(player1, player2);
	}
}
