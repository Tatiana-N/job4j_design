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
}
