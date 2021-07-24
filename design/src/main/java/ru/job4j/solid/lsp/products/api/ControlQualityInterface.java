package ru.job4j.solid.lsp.products.api;

import ru.job4j.solid.lsp.products.Food;

import java.util.List;

public interface ControlQualityInterface {
	void distributeFood(Food food);
	
	List<Food> getTrash();
	
	List<Food> getWarehouse();
	
	List<Food> getShop();
}
