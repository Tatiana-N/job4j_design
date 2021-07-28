package ru.job4j.solid.lsp.tree;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.solid.lsp.tree.api.Course;
import ru.job4j.solid.lsp.tree.api.Paragraph;
import ru.job4j.solid.lsp.tree.api.PrinterTasks;

public class CourseImplTest {
	Paragraph menu = new ParagraphImpl("меню");
	Paragraph topic1 = new ParagraphImpl("тема 1");
	Paragraph paragraph1 = new ParagraphImpl("Задача 1.1.1");
	Paragraph paragraph2 = new ParagraphImpl("Задача 1.1.2");
	Paragraph paragraph3 = new ParagraphImpl("Задача 1.1.3");
	Paragraph module1 = new ParagraphImpl("модуль 1");
	Paragraph topic3 = new ParagraphImpl("тема 3");
	Paragraph topic4 = new ParagraphImpl("тема 4");
	Paragraph module2 = new ParagraphImpl("модуль 2");
	Paragraph module3 = new ParagraphImpl("модуль 3");
	Paragraph module4 = new ParagraphImpl("модуль 4");
	Paragraph topic2 = new ParagraphImpl("тема 2");
	Paragraph paragraph9 = new ParagraphImpl("Задача 2.1.1");
	Paragraph paragraph10 = new ParagraphImpl("Задача 2.1.2");
	Paragraph paragraph14 = new ParagraphImpl("Задача 2.1.3");
	Paragraph paragraph15 = new ParagraphImpl("Задача 3.1.1");
	Paragraph paragraph16 = new ParagraphImpl("Задача 3.1.2");
	Paragraph paragraph17 = new ParagraphImpl("Задача 3.1.3");
	Course course = new CourseImpl(menu);
	
	{
		topic3.putChildren(paragraph15, paragraph16, paragraph17);
		topic2.putChildren(paragraph9, paragraph10, paragraph14);
		topic1.putChildren(paragraph1, paragraph2, paragraph3);
		module1.putChildren(topic1);
		module2.putChildren(topic2, topic4);
		module3.putChildren(topic3);
		menu.putChildren(module1, module2, module3, module4);
	}
	
	@Test
	public void findTask() {
		Paragraph task = course.findTask("тема 2");
		Assert.assertEquals(task, topic2);
	}
	
	@Test
	public void addTask() {
		Paragraph newTask = new ParagraphImpl("Задача 3.1.3");
		course.addTask(newTask, "тема 1");
		Paragraph task = course.findTask(newTask.getName());
		Assert.assertEquals(task, newTask);
	}
	
	@Test
	public void print() {
		PrinterTasks printerTasks = new Printer(course);
		printerTasks.printAllTask("меню", "-");
	}
}