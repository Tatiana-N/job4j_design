package ru.job4j.solid.tictactoe.game;

import lombok.Getter;
import lombok.Setter;
import ru.job4j.solid.tictactoe.api.Drawable;

@Getter
@Setter
public class Player {
	private final String name;
	private final Drawable drawable;
	
	public Player(String name, Drawable drawable) {
		this.name = name;
		this.drawable = drawable;
	}
	
	public Drawable getDrawable() {
		return drawable;
	}
	
	public void doMove(int[] step, Drawable[][] table) {
		table[step[0] - 1][step[1] - 1] = this.getDrawable();
	}
}
