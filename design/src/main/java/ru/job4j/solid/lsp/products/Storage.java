package ru.job4j.solid.lsp.products;

import java.util.List;

public interface Storage {
	void addFood(Food food);
	
	List<Food> getAllFood();
}
