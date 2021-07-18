package ru.job4j.gc;

public class Test {
	public static void main(String[] args) {
		Integer x = new Integer(5);
		Integer y = new Integer(5);
		check(x, y);
		Integer x1 = 5;
		Integer y1 = 5;
		check(x1, y1);
		String s1 = "e";
		String s2 = "e";
		check(s1, s2);
		x1 = x1 + x;
		check(x1, y1);
		check(x, y1);
	}
	
	private static void check(Object x1, Object y1) {
		System.out.print(" Ссылки на объекты равны ? ");
		System.out.print(x1 == y1);
		System.out.print(" Хешкоды объектов равны ? ");
		System.out.print(x1.hashCode() == y1.hashCode());
		System.out.print(String.format(" Хешкоды объекта 1 = %s , Хешкоды объекта 2 = %s", x1.hashCode(), y1.hashCode()));
		System.out.print(" Объекты равны ? ");
		System.out.println(x1.equals(y1));
	}
}
