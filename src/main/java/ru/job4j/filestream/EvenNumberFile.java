package ru.job4j.filestream;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EvenNumberFile {
  public List<Integer> readFileOfNums() {
    List<Integer> list = new ArrayList<>();
    try (
      FileInputStream fis = new FileInputStream("C:/Tanusha/BOOKS/java/task/input.txt");
      BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
    ) {
      while (reader.ready()) {
        list.add(Integer.parseInt(reader.readLine()));
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    return list;
  }

  public void printEvenNumber(List list) {
    list.stream().filter(p -> Double.parseDouble(p.toString()) % 2 == 0).forEach(System.out::println);
  }

  public static void main(String[] args) {
    EvenNumberFile evenNumberFile = new EvenNumberFile();
    List<Integer> list = evenNumberFile.readFileOfNums();
    evenNumberFile.printEvenNumber(list);
  }
}
