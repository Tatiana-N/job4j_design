package ru.job4j.io;

import org.junit.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CsvReader {
	private FileInputStream fileInputStream;
	private final String delimiter;
	List<String> collect;
	
	public void read() {
		Scanner scanner = new Scanner(fileInputStream);
		scanner.useDelimiter(System.lineSeparator());
		if (scanner.hasNext()) {
			String[] line = scanner.next().split(delimiter);
			List<String> strings = Arrays.asList(line);
			Assert.assertTrue("Указаны не правильные названия колонок", strings.containsAll(collect));
			List<Integer> columnIntegers = collect.stream().map(strings::indexOf).collect(Collectors.toList());
			columnIntegers.forEach(t -> System.out.print("\t|\t" + line[t]));
			System.out.println("\t|" + System.lineSeparator());
			while (scanner.hasNext()) {
				String[] words = scanner.next().split(delimiter);
				columnIntegers.forEach(t -> System.out.print("\t|\t" + words[t]));
				System.out.println("\t|" + System.lineSeparator());
			}
		}
	}
	
	public CsvReader(String path, String delimiter, String... forPredicate) {
		this.delimiter = delimiter;
		prepareReadFile(path);
		createPredicate(forPredicate);
		
	}
	
	private void createPredicate(String[] forPredicate) {
		collect = Arrays.stream(forPredicate).collect(Collectors.toList());
	}
	
	private void prepareReadFile(String path) {
		try {
			this.fileInputStream = new FileInputStream((path));
		} catch (FileNotFoundException e) {
			System.out.println("не смогли найти файл, программа завершена с ошибкой");
			throw new RuntimeException();
		}
	}
}
