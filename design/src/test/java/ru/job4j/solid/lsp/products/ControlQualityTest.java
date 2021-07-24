package ru.job4j.solid.lsp.products;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ControlQualityTest {
	@Test
	public void testAddFoodInWarehouse() {
		Food apple = new Food("apple", "01.07.2021", "01.01.2021", 200.0, 0);
		Food potato = new Food("potato", "01.08.2021", "01.01.2021", 176.0, 0);
		Food pineapple = new Food("pineapple", "01.09.2021", "01.01.2021", 100.0, 0);
		Food cucumber = new Food("cucumber", "01.10.2021", "01.01.2021", 500.0, 0);
		Food carrot = new Food("carrot", "01.11.2021", "01.01.2021", 1600.0, 0);
		Food tomato = new Food("tomato", "01.12.2021", "01.01.2021", 90.0, 0);
		Food pumpkin = new Food("pumpkin", "01.12.2021", "23.07.2021", 90.0, 0);
		List<Food> foodList = new ArrayList<>();
		foodList.add(apple);
		foodList.add(pineapple);
		foodList.add(cucumber);
		foodList.add(carrot);
		foodList.add(tomato);
		foodList.add(pumpkin);
		foodList.add(potato);
		
		ControlQuality controlQuality = new ControlQuality();
		for (Food food : foodList) {
			controlQuality.sendFood(food);
		}
		List<Food> trash = controlQuality.getTrash();
		List<Food> warehouse = controlQuality.getWarehouse();
		List<Food> shop = controlQuality.getShop();
		Assert.assertTrue(trash.contains(apple));
		Assert.assertTrue(warehouse.contains(pumpkin));
		Assert.assertTrue(shop.contains(potato));
		Assert.assertEquals(30.0, potato.getDiscount(), 0.0);
		Assert.assertEquals(30.0, pineapple.getDiscount(), 0.0);
		Assert.assertEquals(5, shop.size());
	}
}