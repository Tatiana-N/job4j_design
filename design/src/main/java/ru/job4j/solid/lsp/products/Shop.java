package ru.job4j.solid.lsp.products;

import ru.job4j.solid.lsp.products.api.Storage;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {
	List<Food> list = new ArrayList<>();
	
	@Override
	public void addFood(Food food) {
		int percent = Util.getPercent(food);
		if (percent > 75 && percent <= 100) {
			food.setDiscount(30);
			list.add(food);
		} else if (percent > 25 && percent <= 75) {
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
