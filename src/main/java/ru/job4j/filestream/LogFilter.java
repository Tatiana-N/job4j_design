package ru.job4j.filestream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
  public static List<String> filter(String file) {
    List<String> list = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      while (reader.ready()) {
        list.add(reader.readLine());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    list = list.stream().filter(x -> x.split(" ")[x.split(" ").length - 2].equals("404")).peek((e) -> System.out.print("," + e)).
      collect(Collectors.toList());
    return list;
  }

  public static void main(String[] args) {
    List<String> log = filter("C:/Tanusha/BOOKS/java/task/input2.txt");
    System.out.println(log);
  }
}