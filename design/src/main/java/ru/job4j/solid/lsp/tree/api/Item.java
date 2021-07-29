package ru.job4j.solid.lsp.tree.api;

import java.util.List;

public interface Item extends SomeAnotherAction {
	String getName();
	
	List<Item> getChildren();
	
	void putChildren(Item... item);
}
