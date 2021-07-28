package ru.job4j.solid.dip;

import ru.job4j.solid.lsp.parking.api.Cars;
import ru.job4j.solid.lsp.parking.app.Car;

import java.util.ArrayList;

public interface Parking {
	//1. не должно быть зависимостей на конкретный класс
	boolean park(Car car);
	
	//2. не должны возвращаться объекты конкретного класса
	ArrayList<Cars> getAllCars();
}
