package ru.job4j.solid.lsp.tree;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.solid.lsp.tree.api.Action;
import ru.job4j.solid.lsp.tree.api.Item;
import ru.job4j.solid.lsp.tree.api.Printer;

public class ActionImplTest {
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
	Action action = new ActionImpl(menu);
	
	{
		topic3.putChildren(item15, item16, item17);
		topic2.putChildren(item9, item10, item14);
		topic1.putChildren(item1, item2, item3);
		module1.putChildren(topic1);
		module2.putChildren(topic2, topic4);
		module3.putChildren(topic3);
		menu.putChildren(module1, module2, module3, module4);
		
	}
	
	@Test
	public void findTask() {
		Item task = action.findTask("тема 2");
		task.make();
		Assert.assertEquals(task, topic2);
		
	}
	
	@Test
	public void addTask() {
		Item newTask = new Task("Задача 3.1.3");
		action.addTask(newTask, "тема 1");
		Item task = action.findTask(newTask.getName());
		Assert.assertEquals(task, newTask);
	}
	
	@Test
	public void print() {
		Printer printerTasks = new PrinterItems(action);
		printerTasks.printAllTask("меню", "-");
	}
}