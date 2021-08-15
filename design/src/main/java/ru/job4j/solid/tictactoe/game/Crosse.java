package ru.job4j.solid.tictactoe.game;

import ru.job4j.solid.tictactoe.api.Drawable;

public class Crosse implements Drawable {
	@Override
	public void draw() {
		System.out.print(" X ");
	}
}
