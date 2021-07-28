package ru.job4j.solid.dip;

import ru.job4j.solid.lsp.parking.api.Cars;
import ru.job4j.solid.lsp.parking.app.Car;
import ru.job4j.solid.lsp.parking.app.Truck;

import java.util.ArrayList;
import java.util.List;

public class ParkingImpl implements Parking {
	//3. даже поля класса не должны быть конкретной реализацией
	//из за этого если мы захотим добавить еще один вид
	// машин нам придется влезть сюда и переписать класс
	
	List<Car> cars = new ArrayList<>();
	List<Truck> trucks = new ArrayList<>();
	
	@Override
	public boolean park(Car car) {
		return false;
	}
	
	@Override
	public ArrayList<Cars> getAllCars() {
		return null;
	}
}
