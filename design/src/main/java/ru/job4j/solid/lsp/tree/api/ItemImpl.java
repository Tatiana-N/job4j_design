package ru.job4j.solid.lsp.tree.api;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
public abstract class ItemImpl implements Item {
	String name;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ItemImpl)) {
			return false;
		}
		ItemImpl paragraph = (ItemImpl) o;
		return getName().equals(paragraph.getName()) && getItemList().equals(paragraph.getItemList());
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getName(), getItemList());
	}
	
	List<Item> itemList = new ArrayList<>();
	
	public ItemImpl(String name) {
		this.name = name;
	}
	
	@Override
	public List<Item> getChildren() {
		return itemList;
	}
	
	@Override
	public void putChildren(Item... item) {
		itemList.addAll(Arrays.asList(item));
	}
}
