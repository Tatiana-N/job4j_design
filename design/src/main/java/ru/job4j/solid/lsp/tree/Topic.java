package ru.job4j.solid.lsp.tree;

import ru.job4j.solid.lsp.tree.api.ItemImpl;
import ru.job4j.solid.lsp.tree.api.SomeAnotherAction;

public class Topic extends ItemImpl implements SomeAnotherAction {
	public Topic(String name) {
		super(name);
	}
	
	@Override
	public void make() {
		System.out.println("что-то делаем, уже не знаю что");
	}
}
