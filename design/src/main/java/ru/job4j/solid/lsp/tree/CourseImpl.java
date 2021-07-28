package ru.job4j.solid.lsp.tree;

import ru.job4j.solid.lsp.tree.api.Course;
import ru.job4j.solid.lsp.tree.api.Paragraph;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class CourseImpl implements Course {
	private final Paragraph paragraph;
	
	public CourseImpl(Paragraph menu) {
		paragraph = menu;
	}
	
	@Override
	public Paragraph findTask(String name) {
		Paragraph paragraphTemp;
		Stack<Paragraph> paragraphSet = new Stack<>();
		paragraphSet.add(paragraph);
		if (paragraph.getName().equals(name)) {
			return paragraph;
		}
		while (!paragraphSet.isEmpty()) {
			paragraphTemp = paragraphSet.pop();
			List<Paragraph> paragraphChildren = paragraphTemp.getChildren();
			paragraphSet.addAll(paragraphChildren);
			List<Paragraph> collect = paragraphChildren.stream().filter(t -> t.getName().equals(name)).collect(Collectors.toList());
			if (!collect.isEmpty()) {
				return collect.get(0);
			}
		}
		return null;
	}
	
	@Override
	public void addTask(Paragraph paragraph, String parent) {
		Paragraph task = findTask(parent);
		task.putChildren(paragraph);
	}
}
