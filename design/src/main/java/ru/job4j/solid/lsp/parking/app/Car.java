package ru.job4j.solid.lsp.parking.app;

import lombok.Getter;
import lombok.Setter;
import ru.job4j.solid.lsp.parking.api.Cars;
@Getter
@Setter
public class Car extends Cars {
	private String name;
	private final int length;
	
	public Car(String name, int length) {
		this.name = name;
		if (length == 1) {
			this.length = length;
		} else {
			throw new RuntimeException("неправильная машина");
		}
	}
}
