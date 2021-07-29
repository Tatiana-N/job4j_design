package ru.job4j.solid.lsp.parking.app;

import lombok.Getter;
import lombok.Setter;
import ru.job4j.solid.lsp.parking.api.Cars;

@Setter
@Getter
public class Truck extends Cars {
	private final String name;
	private final int length;
	
	public Truck(String name, int length) {
		this.name = name;
		if (length > 1) {
			this.length = length;
		} else {
			throw new RuntimeException("неправильный грузовик");
		}
	}
}
