package ru.job4j.io;

import org.junit.Test;
import ru.job4j.io.scanner.CsvReader;

public class CsvReaderTest {
	
	@Test
	public void read() {
		CsvReader csvReader = new CsvReader("src/test/resources/fail.csv", ";", "name", "year");
		csvReader.read();
	}
	
	@Test(expected = AssertionError.class)
	public void errorRead() {
		CsvReader csvReader = new CsvReader("src/test/resources/fail.csv", ";", "name", "years");
		csvReader.read();
	}
}