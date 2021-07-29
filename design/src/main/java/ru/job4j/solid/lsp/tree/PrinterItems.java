package ru.job4j.solid.lsp.tree;

import ru.job4j.solid.lsp.tree.api.Action;
import ru.job4j.solid.lsp.tree.api.Item;

import java.util.List;
import java.util.Stack;

public class PrinterItems implements ru.job4j.solid.lsp.tree.api.Printer {
	private final Action action;
	
	public PrinterItems(Action action) {
		this.action = action;
	}
	
	@Override
	public void printAllTask(String paragraphName, String prefix) {
		Item item = action.findTask(paragraphName);
		Stack<Item> itemSet = new Stack<>();
		itemSet.add(item);
		
		while (!itemSet.isEmpty()) {
			Item itemTemp = itemSet.pop();
			System.out.println(prefix + itemTemp.getName());
			List<Item> children = itemTemp.getChildren();
			Stack<Item> childrenSet = new Stack<>();
			childrenSet.addAll(children);
			prefix = prefix + prefix;
			while (!childrenSet.isEmpty()) {
				printAllTask(childrenSet.pop().getName(), prefix);
			}
		}
	}
}

