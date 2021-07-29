package ru.job4j.solid.lsp.parking.app;

import lombok.Getter;
import lombok.Setter;
import ru.job4j.solid.lsp.parking.api.Cars;
@Getter
@Setter
public class Car extends Cars {
	private String name;
	private final int length = 1;
	
	public Car(String name) {
		this.name = name;
	}
}
