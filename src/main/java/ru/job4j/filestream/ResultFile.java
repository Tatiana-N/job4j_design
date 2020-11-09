package ru.job4j.filestream;

import java.io.File;
import java.io.FileOutputStream;

public class ResultFile {
  private String fileName;

  public static void main(String[] args) {
    ResultFile resultFile = new ResultFile("result.txt");
    resultFile.writeInFile(resultFile.multiplicationTableInString());
  }

  public String multiplicationTableInString() {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < 10; i++) {
      if (i != 0) {
        result.append("\t").append(i);
      } else {
        result.append("\t");
      }
      for (int j = 1; j < 10; j++) {

        if (i == 0) {
          result.append("\t").append(j);
        } else {
          result.append(" \t").append(i * j);
        }
      }
      result.append("\n\n");
    }
    return result.toString();
  }

  public ResultFile(String fileName) {
    this.fileName = fileName;

  }

  public void writeInFile(String s) {
    File file = new File(fileName);
    try (FileOutputStream out = new FileOutputStream(file, true)) {
      out.write(s.getBytes());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}