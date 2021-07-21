package ru.job4j.solid.ocp;


import java.awt.*;
import java.util.List;

public class Draw {
	//класс придется переписывать как только появится новая фигура
	void drawAllShapes(List<Shape> list) {
		for (Shape shape : list) {
			switch (shape.toString()) {
				case "square":
					drawSquare((Square) shape);
					break;
				case "circle":
					drawCircle((Circle) shape);
					break;
				default:
					throw new IllegalStateException("Unexpected value: " + shape.toString());
			}
		}
	}
	
	void drawSquare(Square square) {
		//рисуем квадрат
	}
	
	void drawCircle(Circle circle) {
		//рисуем круг
	}
}
