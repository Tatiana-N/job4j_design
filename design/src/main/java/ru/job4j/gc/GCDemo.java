package ru.job4j.gc;

import com.carrotsearch.sizeof.RamUsageEstimator;
import org.junit.Assert;

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
	
	public static void main(String[] args) throws InterruptedException {
		Thread.sleep(25000);
		/**
		 * Минимальный размер объекта  складывается из
		 * заголовка
		 * 8 байт
		 * 4 байт
		 * Всего 12 и дополняется до кратности 8
		 * т.е. размер пустого обекта = 16 байт
		 */
		Assert.assertEquals(RamUsageEstimator.sizeOf(new Object()), 16);
		/**
		 * Размер пустого объекта  Person состоит из:
		 * заголовка
		 * 8 байт
		 * 4 байта
		 * private int age = 4 байта
		 * private String name = 8 байтов
		 */
		//	Assert.assertEquals(RamUsageEstimator.sizeOf(new Person()), 24);
		/**
		 * Размер объекта  Person состоит из:
		 * заголовка
		 * 8 байт
		 * 4 байта
		 * private int age = 4 байта
		 * private String name = 8 байтов
		 */
		//	Assert.assertEquals(RamUsageEstimator.sizeOf(new Person(10)), 24);
		/**
		 * Размер полного объекта  Person состоит из:
		 * заголовка
		 * 8 байт
		 * 4 байта
		 * private int age = 4 байта
		 * private String name = 8 байтов
		 * итого 24 байта +
		 *    обект строка :
		 *    заголовок 4 + 8
		 *    @param  value 8 байт (ссылка на массив)
		 *    private final char value[]
		 *    Array that is the source of characters
		 *    @param  offset int  4 байта
		 *    The initial offset
		 *    @param  count int  4 байта
		 *    итого 28 байта +
		 *        new char[]
		 *        заголовок 4 + 8
		 *        char 2*0
		 *        итого  12 байт
		 *  Всего: 64
		 */
		//	Assert.assertEquals(RamUsageEstimator.sizeOf(new Person(10, "")), 64);
		
		/**
		 * Размер полного объекта  Person состоит из:
		 * заголовка
		 * 8 байт
		 * 4 байта
		 * private int age = 4 байта
		 * private String name = 8 байтов
		 * итого 24 байта +
		 *    обект строка :
		 *    заголовок 4 + 8
		 *    @param  value 8 байт (ссылка на массив)
		 *    private final char value[]
		 *    Array that is the source of characters
		 *    @param  offset int  4 байта
		 *    The initial offset
		 *    @param  count int  4 байта
		 *    итого 28 байта +
		 *        new char[4]
		 *        заголовок 4 + 8
		 *        char 2 * 4
		 *        итого  20 байт
		 *  Всего: 72
		 */
		//	Assert.assertEquals(RamUsageEstimator.sizeOf(new Person(10, "Таня")), 72);
		/**
		 * Размер полного объекта  Person состоит из:
		 * заголовка
		 * 8 байт
		 * 4 байта
		 * private int age = 4 байта
		 * private String name = 8 байтов
		 * итого 24 байта +
		 *    обект строка :
		 *    заголовок 4 + 8
		 *    @param  value 8 байт (ссылка на массив)
		 *    private final char value[]
		 *    Array that is the source of characters
		 *    @param  offset int  4 байта
		 *    The initial offset
		 *    @param  count int  4 байта
		 *    итого 28 байта +
		 *        new char[4]
		 *        заголовок 4 + 8
		 *        char 2 * 5
		 *        итого  22 байт
		 *  Всего 74 и дополняется до кратности 8
		 *  Всего: 80
		 */
		//	Assert.assertEquals(RamUsageEstimator.sizeOf(new Person(10, "ТаняН")), 80);
		/**
		 * Размер полного объекта  Person состоит из:
		 * заголовка
		 * 8 байт
		 * 4 байта
		 * private int age = 4 байта
		 * private String name = 8 байтов
		 * итого 24 байта +
		 *    обект строка :
		 *    заголовок 4 + 8
		 *    @param  value 8 байт (ссылка на массив)
		 *    private final char value[]
		 *    Array that is the source of characters
		 *    @param  offset int  4 байта
		 *    The initial offset
		 *    @param  count int  4 байта
		 *    итого 28 байта +
		 *        new char[4]
		 *        заголовок 4 + 8
		 *        char 2 * 6
		 *        итого  24 байт
		 *
		 *                  40 пустая
		 *  Всего 76 и дополняется до кратности 8
		 *  Всего: 80
		 */
		//	Assert.assertEquals(RamUsageEstimator.sizeOf(new Person(10, "Tanyaa")), 80);
		System.out.println(RamUsageEstimator.sizeOf("FFFFF"));
		System.out.println(RamUsageEstimator.sizeOf("ППППП"));
		
		List<Person> list = new ArrayList<>();
		for (int i = 0; i < 2_000_000; i++) {
			Person person = new Person(i, "Танюша");
			Person n = new Person(i, "Для удаления");
			list.add(person);
			if (list.size() % 1000 == 0) {
				System.out.println(list.size());
			}
		}
		info();
	}
}



