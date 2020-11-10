package ru.job4j.analize;

import java.io.*;

public class Analizy {
  public void unavailable(String source, String target) {
    String[] str;
    try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
      BufferedWriter writer = new BufferedWriter(new FileWriter(target, true));
      while ((reader.ready())) {
        str = reader.readLine().split(" ");
        if (Integer.parseInt(str[0]) > 399) {
          writer.write(str[1] + " ");
          while (Integer.parseInt(str[0]) > 399) {
            str = reader.readLine().split(" ");
          }
          writer.write(str[1] + "\n");
        }
      }
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(new FileOutputStream("C:/Tanusha/BOOKS/java/task/source.txt"))) {
      out.println("200 10:56:01");
      out.println("500 10:57:01");
      out.println("400 10:58:01");
      out.println("200 10:59:01");
      out.println("500 11:01:01");
      out.println("200 10:02:01");

    } catch (Exception e) {
      e.printStackTrace();
    }
    Analizy analizy = new Analizy();
    analizy.unavailable("C:/Tanusha/BOOKS/java/task/source.txt", "C:/Tanusha/BOOKS/java/task/target.txt");
  }
}
