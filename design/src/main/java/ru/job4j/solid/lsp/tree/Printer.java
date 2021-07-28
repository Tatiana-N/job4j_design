package ru.job4j.solid.lsp.tree;

import ru.job4j.solid.lsp.tree.api.Course;
import ru.job4j.solid.lsp.tree.api.Paragraph;
import ru.job4j.solid.lsp.tree.api.PrinterTasks;

import java.util.List;
import java.util.Stack;

public class Printer implements PrinterTasks {
	private final Course course;
	
	public Printer(Course course) {
		this.course = course;
	}
	
	@Override
	public void printAllTask(String paragraphName, String prefix) {
		Paragraph paragraph = course.findTask(paragraphName);
		Stack<Paragraph> paragraphSet = new Stack<>();
		paragraphSet.add(paragraph);
		
		while (!paragraphSet.isEmpty()) {
			Paragraph paragraphTemp = paragraphSet.pop();
			System.out.println(prefix + paragraphTemp.getName());
			List<Paragraph> children = paragraphTemp.getChildren();
			Stack<Paragraph> childrenSet = new Stack<>();
			childrenSet.addAll(children);
			prefix = prefix + prefix;
			while (!childrenSet.isEmpty()) {
				printAllTask(childrenSet.pop().getName(), prefix);
			}
		}
	}
}

