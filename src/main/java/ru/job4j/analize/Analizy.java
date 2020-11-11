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
  }
}