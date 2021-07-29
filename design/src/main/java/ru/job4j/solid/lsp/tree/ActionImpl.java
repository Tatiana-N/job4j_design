package ru.job4j.solid.lsp.tree;

import ru.job4j.solid.lsp.tree.api.Action;
import ru.job4j.solid.lsp.tree.api.Item;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class ActionImpl implements Action {
	private final Item item;
	
	public ActionImpl(Item menu) {
		item = menu;
	}
	
	@Override
	public Item findTask(String name) {
		Item itemTemp;
		Stack<Item> itemSet = new Stack<>();
		itemSet.add(item);
		if (item.getName().equals(name)) {
			return item;
		}
		while (!itemSet.isEmpty()) {
			itemTemp = itemSet.pop();
			List<Item> itemChildren = itemTemp.getChildren();
			itemSet.addAll(itemChildren);
			List<Item> collect = itemChildren.stream().filter(t -> t.getName().equals(name)).collect(Collectors.toList());
			if (!collect.isEmpty()) {
				return collect.get(0);
			}
		}
		return null;
	}
	
	@Override
	public boolean addTask(Item item, String parent) {
		if (item == null) {
			return false;
		}
		Item task = findTask(parent);
		if (task == null) {
			return false;
		}
		task.putChildren(item);
		return true;
	}
}
