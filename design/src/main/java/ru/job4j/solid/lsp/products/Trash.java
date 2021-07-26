package ru.job4j.solid.lsp.products;

import ru.job4j.solid.lsp.products.api.Storage;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Storage {
	List<Food> list = new ArrayList<>();
	
	@Override
	public void addFood(Food food) {
		int percent = Util.getPercent(food);
		if (percent > 100) {
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
