package ru.job4j.solid.lsp.parking.app;

import ru.job4j.solid.lsp.parking.api.Cars;
import ru.job4j.solid.lsp.parking.api.Parking;

import java.util.Arrays;
import java.util.List;

public class ParkingImpl implements Parking {
	final Cars[] parkedCars;
	
	public ParkingImpl(int countPlace) {
		this.parkedCars = new Cars[countPlace];
	}
	
	@Override
	public boolean park(Cars car) {
		int canPark = 1;
		int length = car.getLength();
		for (int i = 0; i < parkedCars.length; i++) {
			if (parkedCars[i] == null) {
				int temp = 1;
				int copyPlace = i;
				while (temp++ < length && copyPlace < parkedCars.length) {
					if (parkedCars[copyPlace] == null) {
						canPark++;
					}
					copyPlace++;
				}
				if (canPark == length) {
					for (int i1 = 0; i1 < length; i1++) {
						parkedCars[i++] = car;
					}
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public List<Cars> getAllCars() {
		return Arrays.asList(parkedCars);
	}
}
