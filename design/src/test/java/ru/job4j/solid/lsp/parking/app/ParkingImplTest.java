package ru.job4j.solid.lsp.parking.app;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.solid.lsp.parking.api.Cars;
import ru.job4j.solid.lsp.parking.api.Parking;

import java.util.List;
import java.util.Objects;

public class ParkingImplTest {
	Cars car1 = new Car("audi", 1);
	Cars car2 = new Car("ferrari", 1);
	Cars car3 = new Car("bmw", 1);
	Cars car4 = new Car("vesta", 1);
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
		Assert.assertEquals(getCountPlase(parking.getAllCars(), car6), car6.getLength());
		Assert.assertTrue(parking.park(car7)); //12
		Assert.assertFalse(parking.park(car8)); //12!!
		Assert.assertTrue(parking.park(car1)); //13
		Assert.assertTrue(parking.park(car6)); //15
		Assert.assertFalse(parking.park(car2)); //16!!
		Assert.assertEquals(getCountPlase(parking.getAllCars(), car1), 2 * car1.getLength());
		Assert.assertEquals(getCountPlase(parking.getAllCars(), car7), car7.getLength());
		Assert.assertEquals(getCountPlase(parking.getAllCars(), car5), car5.getLength());
		Assert.assertEquals(getCountPlase(parking.getAllCars(), car4), car4.getLength());
		Assert.assertEquals(getCountPlase(parking.getAllCars(), car3), car3.getLength());
		Assert.assertEquals(getCountPlase(parking.getAllCars(), car2), car2.getLength());
		Assert.assertEquals(getCountPlase(parking.getAllCars(), car6), 2 * car6.getLength());
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
		Assert.assertEquals(getCountPlase(parking.getAllCars(), car6), car6.getLength());
		Assert.assertEquals(getCountPlase(parking.getAllCars(), car2), car2.getLength());
	}
	
	private int getCountPlase(List<Cars> list, Cars car) {
		return (int) list.stream().filter(Objects::nonNull).filter(t -> t.equals(car)).count();
	}
}