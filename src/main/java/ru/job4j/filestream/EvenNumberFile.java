package ru.job4j.filestream;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EvenNumberFile {
  public List<Integer> readFileOfNums() {
    List<Integer> list = new ArrayList<>();
    try (
      FileInputStream fis = new FileInputStream("C:/Tanusha/BOOKS/java/task/input.txt")
    ) {
      while (fis.available() > 0) {
        String data = new String(fis.readAllBytes());
        Arrays.stream(data.split("\r\n")).map(Integer::parseInt).forEach(list::add);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    return list;
  }

  public void evenNumber(List<Integer> list) {
    Arrays.stream(list.toArray()).map(d -> Double.parseDouble(d.toString())).forEach(d -> System.out.println(d % 2 == 0));
  }

  public static void main(String[] args) {
    EvenNumberFile evenNumberFile = new EvenNumberFile();
    List<Integer> list = evenNumberFile.readFileOfNums();
    evenNumberFile.evenNumber(list);
  }
}
