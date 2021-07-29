package ru.job4j.solid.lsp.tree;

import ru.job4j.solid.lsp.tree.api.*;

import java.io.IOException;
import java.util.Scanner;

public class Application {
	Action action;
	
	public void loadCourse() {
		Item menu = new Paragraph("меню");
		Item topic1 = new Topic("тема 1");
		Item item1 = new Task("Задача 1.1.1");
		Item item2 = new Task("Задача 1.1.2");
		Item item3 = new Task("Задача 1.1.3");
		Item module1 = new Paragraph("модуль 1");
		Item topic3 = new Topic("тема 3");
		Item topic4 = new Topic("тема 4");
		Item module2 = new Paragraph("модуль 2");
		Item module3 = new Paragraph("модуль 3");
		Item module4 = new Paragraph("модуль 4");
		Item topic2 = new Topic("тема 2");
		Item item9 = new Task("Задача 2.1.1");
		Item item10 = new Task("Задача 2.1.2");
		Item item14 = new Task("Задача 2.1.3");
		Item item15 = new Task("Задача 3.1.1");
		Item item16 = new Task("Задача 3.1.2");
		Item item17 = new Task("Задача 3.1.3");
		topic3.putChildren(item15, item16, item17);
		topic2.putChildren(item9, item10, item14);
		topic1.putChildren(item1, item2, item3);
		module1.putChildren(topic1);
		module2.putChildren(topic2, topic4);
		module3.putChildren(topic3);
		menu.putChildren(module1, module2, module3, module4);
		action = new ActionImpl(menu);
		init();
	}
	
	Scanner scanner = new Scanner(System.in);
	
	private void init() {
		Printer printer = new PrinterItems(action);
		boolean run = true;
		Item tempItem = action.findTask("меню");
		while (run) {
			this.showMenu();
			System.out.print("Выбрать: ");
			String select = scanner.nextLine();
			switch (select) {
				case "0":
					System.out.println("---Загрузка всего модуля---");
					printer.printAllTask("меню", "-");
					break;
				case "1":
					System.out.println("---Загрузка содержимого модуля---");
					printer.printAllTask(tempItem.getName(), "-");
					break;
				case "2":
					System.out.println("---Что-то делаем с модулем---");
					tempItem.make();
					break;
				case "3":
					System.out.println("---Найти модуль---");
					System.out.print("Введите название модуля: ");
					Item temp = action.findTask(scanner.nextLine());
					if (temp != null) {
						System.out.print("--Модуль найден--");
						tempItem = temp;
					} else {
						System.out.print("--Модуль не найден--");
					}
					break;
				case "4":
					Item topic = newItem("topic");
					tempItem = topic != null ? topic : tempItem;
					break;
				case "5":
					Item paragraph = newItem("paragraph");
					tempItem = paragraph != null ? paragraph : tempItem;
					break;
				case "6":
					Item task = newItem("task");
					tempItem = task != null ? task : tempItem;
					break;
				case "7":
					run = false;
					break;
				default:
					System.out.println("Попадайте по клавишам");
			}
		}
	}
	
	private Item newItem(String type) {
		System.out.println("---Создаем новый модуль---");
		System.out.println("Введите название модуля в который вставляем модуль ");
		String parentName = scanner.nextLine();
		System.out.println("Введите название модуля ");
		String name = scanner.nextLine();
		Item item = null;
		switch (type) {
			case "topic":
				item = new Topic(name);
				break;
			case "paragraph":
				item = new Paragraph(name);
				break;
			case "task":
				item = new Task(name);
				break;
			default:
				break;
		}
		if (action.addTask(item, parentName)) {
			System.out.printf("новый модуль %s добавлен в модуль %s%n", item, parentName);
		} else {
			System.out.printf("Что-то пошло не так модуль %s не добавлен в модуль %s%n", item, parentName);
			return null;
		}
		return item;
	}
	
	private void showMenu() {
		System.out.println("0 - Меню: ");
		System.out.println("1 - показать содержимое");
		System.out.println("2 - действие с модулем");
		System.out.println("3 - найти модуль");
		System.out.println("4 - добавить модуль");
		System.out.println("5 - добавить параграф");
		System.out.println("6 - добавить задание");
		System.out.println("7 - выйти");
	}
	
	public static void main(String[] args) throws IOException {
		new Application().loadCourse();
	}
}
