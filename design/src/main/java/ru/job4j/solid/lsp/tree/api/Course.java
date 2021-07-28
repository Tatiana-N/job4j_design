package ru.job4j.solid.lsp.tree.api;

public interface Course {
	Paragraph findTask(String name);
	
	void addTask(Paragraph paragraph, String parent);
}
