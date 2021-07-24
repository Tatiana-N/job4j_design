package ru.job4j.solid.lsp.products.controller;

import ru.job4j.solid.lsp.products.Shop;
import ru.job4j.solid.lsp.products.Trash;
import ru.job4j.solid.lsp.products.Warehouse;
import ru.job4j.solid.lsp.products.api.ControlQualityInterface;
import ru.job4j.solid.lsp.products.api.Storage;
import ru.job4j.solid.lsp.products.Food;

import java.util.Calendar;
import java.util.List;

public class ControlQuality implements ControlQualityInterface {
	Storage trash = new Trash();
	Storage shop = new Shop();
	Storage warehouse = new Warehouse();
	
	@Override
	public void distributeFood(Food food) {
		long now = Calendar.getInstance().getTimeInMillis();
		long expiryDateTime = food.getExpiryDate().getTimeInMillis();
		long createDateTime = food.getCreateDate().getTimeInMillis();
		long milliSecondsGone = now - createDateTime;
		long milliSecondsToExpiry = expiryDateTime - createDateTime;
		int percent = (int) ((double) milliSecondsGone / (double) milliSecondsToExpiry * 100);
		if (now > expiryDateTime) {
			trash.addFood(food);
		} else if (percent < 25) {
			warehouse.addFood(food);
		} else if (percent > 75) {
			food.setDiscount(30);
			shop.addFood(food);
		} else {
			shop.addFood(food);
		}
	}
	
	@Override
	public List<Food> getTrash() {
		System.out.println("Trash");
		System.out.println(trash.getAllFood());
		return  trash.getAllFood();
	}
	
	@Override
	public List<Food> getWarehouse() {
		System.out.println("Warehouse");
		System.out.println(warehouse.getAllFood());
		return warehouse.getAllFood();
	}
	
	@Override
	public List<Food> getShop() {
		System.out.println("Shop");
		System.out.println(shop.getAllFood());
		return shop.getAllFood();
	}
}
