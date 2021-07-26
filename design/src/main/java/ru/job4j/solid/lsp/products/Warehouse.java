package ru.job4j.solid.lsp.products;

import ru.job4j.solid.lsp.products.api.Storage;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage {
	List<Food> list = new ArrayList<>();
	
	@Override
	public void addFood(Food food) {
		int percent = Util.getPercent(food);
		if (percent >= 0 && percent <= 25) {
			list.add(food);
		}
	}
	
	@Override
	public List<Food> getAllFood() {
		return list;
	}
	
	@Override
	public void devastate() {
		list = new ArrayList<>();
	}
}
