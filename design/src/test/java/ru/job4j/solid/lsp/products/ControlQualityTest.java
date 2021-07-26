package ru.job4j.solid.lsp.products;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.solid.lsp.products.api.ControlQualityInterface;
import ru.job4j.solid.lsp.products.api.Storage;
import ru.job4j.solid.lsp.products.controller.ControlQuality;

import java.util.Calendar;
import java.util.List;

public class ControlQualityTest {
	
	@Test
	public void testAddFoodInWarehouse() {
		//Если срок годности вышел. Отправить продукт в мусорку.
		Food apple = new Food("apple", createDate(-1), createDate(-6), 200.0, 0);
		//Если срок годности израсходован меньше чем на 25% направить в Warehouse;
		Food pumpkin = new Food("pumpkin", createDate(7), createDate(-1), 90.0, 0);
		//Если срок годности от 25% до 75% направить в Shop;
		Food carrot = new Food("carrot", createDate(4), createDate(-7), 1600.0, 0);
		Food tomato = new Food("tomato", createDate(5), createDate(-7), 90.0, 0);
		Food cucumber = new Food("cucumber", createDate(3), createDate(-7), 500.0, 0);
		//Если срок годности больше 75% то выставить скидку на продукт и отправить в Shop;
		Food potato = new Food("potato", createDate(1), createDate(-6), 176.0, 0);
		Food pineapple = new Food("pineapple", createDate(2), createDate(-7), 100.0, 0);
		
		List<Food> foodList = List.of(apple, potato, pineapple, cucumber, carrot, tomato, pumpkin);
		Storage trash = new Trash();
		Storage shop = new Shop();
		Storage warehouse = new Warehouse();
		ControlQualityInterface controlQuality = new ControlQuality(trash, shop, warehouse);
		for (Food food : foodList) {
			controlQuality.distributeFood(food);
		}
		List<Food> trashAllFood = trash.getAllFood();
		List<Food> warehouseAllFood = warehouse.getAllFood();
		List<Food> shopAllFood = shop.getAllFood();
		Assert.assertTrue(trashAllFood.contains(apple));
		Assert.assertTrue(warehouseAllFood.contains(pumpkin));
		Assert.assertTrue(shopAllFood.contains(potato));
		Assert.assertEquals(30.0, potato.getDiscount(), 0.0);
		Assert.assertEquals(30.0, pineapple.getDiscount(), 0.0);
		Assert.assertEquals(5, shopAllFood.size());
		pumpkin.setExpiryDate(createDate(-1));
		pineapple.setExpiryDate(createDate(12));
		controlQuality.resort();
		trashAllFood = trash.getAllFood();
		warehouseAllFood = warehouse.getAllFood();
		shopAllFood = shop.getAllFood();
		Assert.assertTrue(trashAllFood.contains(apple));
		Assert.assertTrue(trashAllFood.contains(pumpkin));
		Assert.assertFalse(warehouseAllFood.contains(pumpkin));
		Assert.assertTrue(shopAllFood.contains(potato));
		Assert.assertEquals(0, warehouseAllFood.size());
		Assert.assertEquals(30.0, potato.getDiscount(), 0.0);
		Assert.assertEquals(0, pineapple.getDiscount(), 0.0);
		Assert.assertEquals(5, shopAllFood.size());
	}
	
	private Calendar createDate(int i) {
		Calendar create = Calendar.getInstance();
		create.add(Calendar.MONTH, i);
		return create;
	}
}