package ru.job4j.solid.lsp.tree;

import ru.job4j.solid.lsp.tree.api.ItemImpl;
import ru.job4j.solid.lsp.tree.api.SomeAnotherAction;

public class Paragraph extends ItemImpl implements SomeAnotherAction {
	
	public Paragraph(String name) {
		super(name);
	}
	
	@Override
	public void make() {
		System.out.println("читаем тему");
	}
}
