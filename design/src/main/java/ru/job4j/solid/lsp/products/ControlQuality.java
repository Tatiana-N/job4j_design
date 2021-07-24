package ru.job4j.solid.lsp.products;

import java.util.Calendar;
import java.util.List;

public class ControlQuality {
	Storage trash = new Trash();
	Storage shop = new Shop();
	Storage warehouse = new Warehouse();
	
	public void sendFood(Food food) {
		long now = Calendar.getInstance().getTimeInMillis();
		long expiryDateTime = food.expiryDate.getTimeInMillis();
		long createDateTime = food.createDate.getTimeInMillis();
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
	
	List<Food> getTrash() {
		System.out.println("Trash");
		System.out.println(trash.getAllFood());
		return  trash.getAllFood();
	}
	
	List<Food> getWarehouse() {
		System.out.println("Warehouse");
		System.out.println(warehouse.getAllFood());
		return warehouse.getAllFood();
	}
	
	List<Food> getShop() {
		System.out.println("Shop");
		System.out.println(shop.getAllFood());
		return shop.getAllFood();
	}
}
