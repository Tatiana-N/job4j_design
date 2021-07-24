package ru.job4j.solid.lsp.products;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.solid.lsp.products.api.ControlQualityInterface;
import ru.job4j.solid.lsp.products.api.ParserDate;
import ru.job4j.solid.lsp.products.controller.ControlQuality;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ControlQualityTest {
	private final ParserDate<Calendar> parserDate = new ParseDateToCalendar("dd.MM.yyyy");
	
	@Test
	public void testAddFoodInWarehouse() {
		
		Food apple = new Food("apple", parserDate.parseString("01.07.2021"), parserDate.parseString("01.01.2021"), 200.0, 0);
		Food potato = new Food("potato", parserDate.parseString("01.08.2021"), parserDate.parseString("01.01.2021"), 176.0, 0);
		Food pineapple = new Food("pineapple", parserDate.parseString("01.09.2021"), parserDate.parseString("01.01.2021"), 100.0, 0);
		Food cucumber = new Food("cucumber", parserDate.parseString("01.10.2021"), parserDate.parseString("01.01.2021"), 500.0, 0);
		Food carrot = new Food("carrot", parserDate.parseString("01.11.2021"), parserDate.parseString("01.01.2021"), 1600.0, 0);
		Food tomato = new Food("tomato", parserDate.parseString("01.12.2021"), parserDate.parseString("01.01.2021"), 90.0, 0);
		Food pumpkin = new Food("pumpkin", parserDate.parseString("01.12.2021"), parserDate.parseString("23.07.2021"), 90.0, 0);
		List<Food> foodList = new ArrayList<>();
		foodList.add(apple);
		foodList.add(pineapple);
		foodList.add(cucumber);
		foodList.add(carrot);
		foodList.add(tomato);
		foodList.add(pumpkin);
		foodList.add(potato);
		
		ControlQualityInterface controlQuality = new ControlQuality();
		for (Food food : foodList) {
			controlQuality.distributeFood(food);
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
		pumpkin.setExpiryDate(parserDate.parseString("01.07.2021"));
		pineapple.setExpiryDate(parserDate.parseString("01.09.2022"));
		controlQuality.resort();
		trash = controlQuality.getTrash();
		warehouse = controlQuality.getWarehouse();
		shop = controlQuality.getShop();
		Assert.assertTrue(trash.contains(apple));
		Assert.assertTrue(trash.contains(pumpkin));
		Assert.assertFalse(warehouse.contains(pumpkin));
		Assert.assertTrue(shop.contains(potato));
		Assert.assertEquals(0, warehouse.size());
		Assert.assertEquals(30.0, potato.getDiscount(), 0.0);
		Assert.assertEquals(0, pineapple.getDiscount(), 0.0);
		Assert.assertEquals(5, shop.size());
	}
}