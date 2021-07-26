package ru.job4j.solid.lsp.products.controller;

import ru.job4j.solid.lsp.products.Food;
import ru.job4j.solid.lsp.products.api.ControlQualityInterface;
import ru.job4j.solid.lsp.products.api.Storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControlQuality implements ControlQualityInterface {
	private final List<Storage> storages = new ArrayList<>();
	
	public ControlQuality(Storage... storages) {
		this.storages.addAll(Arrays.asList(storages));
	}
	
	@Override
	public void distributeFood(Food food) {
		for (Storage storage : storages) {
			storage.addFood(food);
		}
	}
	
	@Override
	public void resort() {
		List<Food> foodList = new ArrayList<>();
		for (Storage storage : storages) {
			foodList.addAll(storage.getAllFood());
			storage.devastate();
		}
		for (Food food : foodList) {
			food.setDiscount(0);
			distributeFood(food);
		}
	}
}
