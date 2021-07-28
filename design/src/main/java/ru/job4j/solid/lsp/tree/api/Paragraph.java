package ru.job4j.solid.lsp.tree.api;

import java.util.List;

public interface Paragraph {
	String getName();
	
	List<Paragraph> getChildren();
	
	void putChildren(Paragraph... paragraph);
}
