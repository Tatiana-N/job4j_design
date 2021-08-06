package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class App {
	public static void main(String[] args) {
		ArgsName argsName = ArgsName.of(args);
		String path = argsName.get("path");
		String filter = argsName.get("filter");
		String delimiter = argsName.get("delimiter");
		String out = argsName.get("out");
		if (path == null || filter == null || delimiter == null || out == null) {
			System.out.println("Запустите программу с корректными аргументами");
			System.out.println("Пример java -jar target/csvReader.jar -path=file.csv -delimiter=;  -out=stdout -filter=name,age");
		} else {
			Reader reader = new CsvReader(path, delimiter, filter.split(","));
			String lines = reader.read();
			OutputStream writer = null;
			if ("stdout".equals(out)) {
				writer = System.out;
			} else {
				try {
					writer = new FileOutputStream(out);
				} catch (FileNotFoundException e) {
					System.out.println("не получилось записать данные в файл");
				}
			}
			try {
				assert writer != null;
				writer.write(lines.getBytes(StandardCharsets.UTF_8));
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
