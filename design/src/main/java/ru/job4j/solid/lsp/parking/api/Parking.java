package ru.job4j.solid.lsp.parking.api;

import java.util.List;

public interface Parking {
	boolean park(Cars car);
	
	List<Cars> getAllCars();
}
