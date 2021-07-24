package ru.job4j.solid.lsp.products;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {
	List<Food> list = new ArrayList<>();
	
	@Override
	public void addFood(Food food) {
		list.add(food);
	}
	
	@Override
	public List<Food> getAllFood() {
		return list;
	}
}
