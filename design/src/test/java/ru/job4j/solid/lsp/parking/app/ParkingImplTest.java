package ru.job4j.solid.lsp.parking.app;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.solid.lsp.parking.api.Cars;
import ru.job4j.solid.lsp.parking.api.Parking;

public class ParkingImplTest {
	Cars car1 = new Car("audi");
	Cars car2 = new Car("ferrari");
	Cars car3 = new Car("bmw");
	Cars car4 = new Car("vesta");
	Cars car5 = new Truck("название грузовика", 3);
	Cars car6 = new Truck("название грузовика", 2);
	Cars car7 = new Truck("название грузовика", 3);
	Cars car8 = new Truck("название грузовика", 5);
	
	@Test
	public void correctPark() {
		Parking parking = new ParkingImpl(15);
		Assert.assertTrue(parking.park(car1)); //1
		Assert.assertTrue(parking.park(car2)); //2
		Assert.assertTrue(parking.park(car3)); //3
		Assert.assertTrue(parking.park(car4)); //4
		Assert.assertTrue(parking.park(car5)); //7
		Assert.assertTrue(parking.park(car6)); //9
		Assert.assertTrue(parking.park(car7)); //12
		Assert.assertFalse(parking.park(car8)); //12!!
		Assert.assertTrue(parking.park(car1)); //13
		Assert.assertTrue(parking.park(car6)); //15
		Assert.assertFalse(parking.park(car2)); //16!!
	}
	
	@Test(expected = RuntimeException.class)
	public void incorrectCreatingTrucks() {
		new Truck("название грузовика", 1);
	}
	
	@Test
	public void checkNotParking() {
		Parking parking = new ParkingImpl(3);
		Assert.assertTrue(parking.park(car6)); //2
		Assert.assertFalse(parking.park(car7)); //2!!!
		Assert.assertTrue(parking.park(car2)); //3
		Assert.assertFalse(parking.park(car3)); //3!!!
		Assert.assertFalse(parking.park(car4)); //3!!!
		Assert.assertFalse(parking.park(car5));
		Assert.assertFalse(parking.getAllCars().contains(car5));
		Assert.assertFalse(parking.getAllCars().contains(car4));
		Assert.assertFalse(parking.getAllCars().contains(car3));
		Assert.assertFalse(parking.getAllCars().contains(car7));
		Assert.assertTrue(parking.getAllCars().contains(car6));
		Assert.assertTrue(parking.getAllCars().contains(car2));
	}
	
}