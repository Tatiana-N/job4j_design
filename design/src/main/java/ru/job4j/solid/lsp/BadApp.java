package ru.job4j.solid.lsp;

import java.util.List;

public class BadApp {
	List<Shape> shapeList;
	
	public BadApp(List<Shape> shapeList) {
		this.shapeList = shapeList;
	}
	
	//такой метод нарушает принцип OCP в силу того что нарушен принцип LSP
	public void drawAll() {
		for (Shape shape : shapeList) {
			if (shape instanceof Triangle) {
				try {
					((Triangle)shape).draw();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (shape instanceof Square) {
				((Square)shape).draw();
			}
		}
	}
}
