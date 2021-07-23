package ru.job4j.solid.lsp;

import java.util.ArrayList;
import java.util.List;

public class Application {
	public static void main(String[] args) {
		Shape square = new Square();
		Shape triangle = new Triangle();
		List<Shape> shapeList = new ArrayList<>();
		shapeList.add(square);
		shapeList.add(triangle);
		for (Shape shape : shapeList) {
			//мы ожидаем что все будет нормально, а класс треугольника вместо того чтобы нарисовать треугольник выкинет эксепшен, это нарушение LSP
			shape.draw();
		}
	}
}
