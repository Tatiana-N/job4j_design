package ru.job4j.gc;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class DirFileCache extends AbstractCache<String, String> {
	
	private final String cachingDir;
	
	public DirFileCache(String cachingDir) {
		this.cachingDir = cachingDir;
	}
	
	@Override
	protected String load(String key) {
		try {
			return Files.lines(Paths.get(cachingDir + File.separator + key), StandardCharsets.UTF_8).collect(Collectors.joining());
		} catch (IOException e) {
			System.out.println("файл не найден");
			throw new RuntimeException(e.getMessage());
		}
	}
}
