package ru.job4j.gc;

import com.carrotsearch.sizeof.RamUsageEstimator;

import java.util.ArrayList;
import java.util.List;

public class GCDemo {
	
	private static final long KB = 1000;
	private static final long MB = KB * KB;
	private static final Runtime ENVIRONMENT = Runtime.getRuntime();
	
	public static void info() {
		final long freeMemory = ENVIRONMENT.freeMemory();
		final long totalMemory = ENVIRONMENT.totalMemory();
		final long maxMemory = ENVIRONMENT.maxMemory();
		System.out.println("=== Environment state ===");
		System.out.printf("Free: %d%n", freeMemory / MB);
		System.out.printf("Total: %d%n", totalMemory / MB);
		System.out.printf("Max: %d%n", maxMemory / MB);
	}
	
	public static void main(String[] args) {
		info();
		List<Person> list = new ArrayList<>();
		for (int i = 0; i < 200; i++) {
			Person person = new Person(i, "N" + i);
			person.setAge(20);
			person.setName("Tanya");
			list.add(person);
			System.out.print(RamUsageEstimator.sizeOf(person));
			System.out.print(" ");
			System.out.print(RamUsageEstimator.sizeOf(new Person(i, "N" + i)));
			System.out.print(" ");
			if (i % 20 == 0) {
				System.gc();
			}
		}
		info();
		System.out.println(RamUsageEstimator.sizeOf(list));
		System.gc();
		info();
	}
}



