package ru.job4j.solid.lsp.tree;

import ru.job4j.solid.lsp.tree.api.ItemImpl;
import ru.job4j.solid.lsp.tree.api.SomeAnotherAction;

public class Task extends ItemImpl implements SomeAnotherAction {
	public Task(String name) {
		super(name);
	}
	
	@Override
	public void make() {
		System.out.println("решаем задачу");
	}
}
