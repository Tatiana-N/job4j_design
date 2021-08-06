package ru.job4j.io.scanner;

import org.junit.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CsvReader implements Reader {
	private FileInputStream fileInputStream;
	private final String delimiter;
	List<String> collect;
	
	@Override
	public String read() {
		Scanner scanner = new Scanner(fileInputStream);
		scanner.useDelimiter(System.lineSeparator());
		StringBuilder sb = new StringBuilder();
		if (scanner.hasNext()) {
			String[] line = scanner.next().split(delimiter);
			List<String> strings = Arrays.asList(line);
			Assert.assertTrue("Указаны не правильные названия колонок", strings.containsAll(collect));
			List<Integer> columnIntegers = collect.stream().map(strings::indexOf).collect(Collectors.toList());
			columnIntegers.forEach(t -> sb.append("\t|\t").append(line[t]));
			sb.append("\t|").append(System.lineSeparator());
			while (scanner.hasNext()) {
				String[] words = scanner.next().split(delimiter);
				columnIntegers.forEach(t -> sb.append("\t|\t").append(words[t]));
				sb.append("\t|").append(System.lineSeparator());
			}
		}
		return sb.toString();
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
