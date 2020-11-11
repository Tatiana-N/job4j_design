package ru.job4j.filestream;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
  public static List<String> filter(String file) {
    List<String> list = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      while (reader.ready()) {
        String[] line = reader.readLine().split(" ");
        if (line[line.length - 2].equals("404")) {
          list.add(Arrays.stream(line).collect(Collectors.joining(" ")));
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return list;
  }

  public static void save(List<String> list, String fileName) {
    try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(fileName)))) {
      list.stream().forEach(x -> writer.write(x + "\n"));
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    List<String> log = filter("input2.txt");
    save(log, "result2.txt");

  }
}