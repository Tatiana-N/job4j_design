package ru.job4j.solid.lsp.products.api;

import java.text.DateFormat;

public interface ParserDate<T> {
	T parseString(String date);
	
	DateFormat getFormatter();
}
