package ru.job4j.solid.lsp.parking.app;

import org.apache.maven.surefire.shade.org.apache.commons.lang3.ArrayUtils;
import ru.job4j.solid.lsp.parking.api.Cars;
import ru.job4j.solid.lsp.parking.api.Parking;

import java.util.Arrays;
import java.util.List;

public class ParkingImpl implements Parking {
	final Cars[] parkedCars;
	final Cars[] parkedTrucks;
	private int carPlace = 0;
	private int truckPlace = 0;
	
	public ParkingImpl(int cars, int trucks) {
		this.parkedCars = new Cars[cars];
		this.parkedTrucks = new Cars[trucks];
	}
	
	@Override
	public boolean park(Cars car) {
		if (car.getLength() > 1 && parkedTrucks.length > truckPlace) {
			parkedTrucks[truckPlace++] = car;
			return true;
		} else if (car.getLength() > 1 && parkedCars.length - carPlace >= car.getLength()) {
			for (int i = 0; i < car.getLength(); i++) {
				parkedCars[carPlace++] = car;
			}
			return true;
		} else if (car.getLength() == 1 && parkedCars.length > carPlace) {
			parkedCars[carPlace++] = car;
			return true;
		}
		return false;
	}
	
	@Override
	public List<Cars> getAllCars() {
		Cars[] both = ArrayUtils.addAll(parkedTrucks, parkedCars);
		return Arrays.asList(both);
	}
}
