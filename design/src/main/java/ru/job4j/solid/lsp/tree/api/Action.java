package ru.job4j.solid.lsp.tree.api;

public interface Action {
	Item findTask(String name);
	
	boolean addTask(Item item, String parent);
}
