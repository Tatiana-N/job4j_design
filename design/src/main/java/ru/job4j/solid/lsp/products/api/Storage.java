package ru.job4j.solid.lsp.products.api;

import ru.job4j.solid.lsp.products.Food;

import java.util.List;

public interface Storage {
	void addFood(Food food);
	
	List<Food> getAllFood();
}
