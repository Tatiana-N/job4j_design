package ru.job4j.solid.lsp.products.api;

import ru.job4j.solid.lsp.products.Food;

public interface ControlQualityInterface {
	void distributeFood(Food food);
	
	void resort();
}
