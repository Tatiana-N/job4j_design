package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
  private final String path;
  private final Map<String, String> values = new HashMap<>();

  public Config(final String path) {
    this.path = path;
  }

  public void load() {
    StringJoiner out = new StringJoiner(System.lineSeparator());
    try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
      read.lines().filter(line -> !line.isEmpty() && line.strip().charAt(0) != '#')
        .map(x -> x.split("=")).filter(x -> x.length == 2).forEach(x -> values.put(x[0], x[1]));

    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(out);
  }

  public String value(String key) {
    if (values.containsKey(key)) {
      return values.get(key);
    }
    return null;
    //throw new UnsupportedOperationException("Don't impl this method yet!");
  }

  @Override
  public String toString() {
    StringJoiner out = new StringJoiner(System.lineSeparator());
    try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
      read.lines().forEach(out::add);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return out.toString();
  }

  public static void main(String[] args) {
    String path = "C:/Tanusha/BOOKS/java/task/";
    System.out.println(new Config(path + "app.properties"));
  }
}