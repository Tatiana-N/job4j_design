package ru.job4j.gc;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class DirFileCacheTest {
	
	@Test(expected = RuntimeException.class)
	public void load() throws IOException {
		DirFileCache dirFileCache = new DirFileCache("src/main/resources");
		String load = dirFileCache.load("app.properties");
		String get = dirFileCache.get("app.properties");
		Assert.assertEquals(get, load);
		String collect = Files.lines(Paths.get("src/main/resources/app.properties")).collect(Collectors.joining());
		Assert.assertEquals(collect, dirFileCache.get("app.properties"));
		dirFileCache.load("wrongApp.properties");
	}
}