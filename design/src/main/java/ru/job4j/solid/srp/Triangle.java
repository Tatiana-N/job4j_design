package ru.job4j.solid.srp;

public class Triangle {
	private final double side1;
	private final double side2;
	private final double angle;
	
	public Triangle(double side1, double side2, double angle) {
		this.side1 = side1;
		this.side2 = side2;
		this.angle = Math.toRadians(angle);
	}
	
	public double countAreaOfTriangle() {
		return 0.5 * side1 * side2 * Math.sin(angle);
	}
	
	public void printAreaOfTriangle() {
		System.out.println(countAreaOfTriangle());
	}
}
