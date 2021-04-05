package ru.job4j.search.src.files;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchFiles<T> extends SimpleFileVisitor<T> {
  private List<Path> paths;
  Predicate predicate;

  public SearchFiles(Predicate<Path> predicate) {
    this.predicate = predicate;
    paths = new ArrayList<>();
  }

  public List<Path> getPaths() {
    return paths;
  }

  @Override
  public FileVisitResult visitFile(T file, BasicFileAttributes attrs) throws IOException {
    if (predicate.test(file)) {
      paths.add(((Path) file).toAbsolutePath());
    }
    return super.visitFile(file, attrs);
  }
}
