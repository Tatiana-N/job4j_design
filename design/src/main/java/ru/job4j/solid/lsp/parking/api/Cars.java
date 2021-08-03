package ru.job4j.solid.lsp.parking.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Cars {
	protected String name;
	protected int length;
}
