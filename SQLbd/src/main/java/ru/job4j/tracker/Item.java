package ru.job4j.tracker;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Item implements Comparable<Item> {
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Item(String name) {
		this.name = name;
	}
	
	@Override
	protected void finalize() throws Throwable {
		System.out.printf("Removed %s%n", name);
	}
	
	@Override
	public int compareTo(Item item) {
		return this.name.compareTo(item.getName());
	}
}
