package ru.job4j.solid.srp;

import java.text.Format;

public class Printer {
	//класс печатает
	public static void printInConsole(String text) {
		System.out.println(text);
	}
	
	//класс может форматировать строки
	public static String getFormat(String string, Format format) {
		return format.format(string);
	}
}
