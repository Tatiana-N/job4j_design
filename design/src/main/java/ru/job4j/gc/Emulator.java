package ru.job4j.gc;

import java.io.IOException;
import java.util.Scanner;

public class Emulator {
	public void init(Scanner scanner) {
		boolean run = true;
		System.out.print("Введите название директории:");
		String cachingDir = scanner.nextLine();
		DirFileCache dirFileCache = new DirFileCache(cachingDir);
		while (run) {
			this.showMenu();
			System.out.print("Выбрать: ");
			String select = scanner.nextLine();
			switch (select) {
				case "1":
					System.out.println("---Загрузить содержимое файла в кэш---");
					System.out.print("Ведите название файла: ");
					String fileName = scanner.nextLine();
					dirFileCache.get(fileName);
					break;
				case "2":
					System.out.println("---Получить содержимое файла по названию---");
					System.out.print("Ведите название файла: ");
					fileName = scanner.nextLine();
					String s = dirFileCache.get(fileName);
					System.out.println(s);
					break;
				case "3":
					run = false;
					break;
				default:
					System.out.println("Попадайте по клавишам");
			}
		}
	}
	
	private void showMenu() {
		System.out.println("Меню: ");
		System.out.println("1 - загрузить содержимое файла в кэш");
		System.out.println("2 - получить содержимое файла из кэша");
		System.out.println("3 - Выход");
	}
	
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		new Emulator().init(scanner);
	}
}
