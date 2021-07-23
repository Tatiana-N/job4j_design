package ru.job4j.solid.lsp;

public class Triangle implements Shape {
	@Override
	public void draw() {
		throw new RuntimeException();
	}
}
