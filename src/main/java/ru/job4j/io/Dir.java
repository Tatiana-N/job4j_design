package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Dir {
  public static void main(String[] args) throws IOException {
    File file = new File("c:\\projects");
    if (!file.exists()) {
      throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
    }
    if (!file.isDirectory()) {
      throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
    }
    System.out.println(String.format("size : %s", file.getTotalSpace()));
    for (File subfile : file.listFiles()) {
      System.out.println(subfile.getName() + " size = " + Files.walk(subfile.toPath()).filter(x -> x.toFile().isFile())
        .mapToLong(x -> x.toFile().length()).sum() / 1024 + "kb");
    }
  }
}