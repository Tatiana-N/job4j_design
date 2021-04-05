package ru.job4j.search.src.s;

import ru.job4j.search.src.files.SearchFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Search {

  public static void main(String[] args) throws IOException {
    if (args.length == 0) {
      throw new IllegalArgumentException("Root folder is null and search argument not defined");
    }
    if (args.length == 1) {
      throw new IllegalArgumentException("Search argument not defined");
    }
    Path start = Paths.get(args[0]);
    search(start, args[1]).forEach(System.out::println);
  }

  public static List<Path> search(Path root, String ext) throws IOException {
    SearchFiles searcher = new SearchFiles(p -> ((Path) p).toFile().getName().endsWith(ext));
    Files.walkFileTree(root, searcher);
    return searcher.getPaths();
  }

}