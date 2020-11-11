package ru.job4j.analize;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {
  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

  @Test

  public void unavailable() throws IOException {

    File source = temporaryFolder.newFile("source.txt");
    try (
      PrintWriter out = new PrintWriter(new FileOutputStream(source))) {
      out.println("200 10:56:01");
      out.println("500 10:57:01");
      out.println("400 10:58:01");
      out.println("200 10:59:01");
      out.println("500 11:01:01");
      out.println("200 11:02:01");

    } catch (Exception e) {
      e.printStackTrace();
    }
    Analizy analizy = new Analizy();
    File target = temporaryFolder.newFile("target.txt");
    analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
    StringBuilder rsl = new StringBuilder();
    try (BufferedReader in = new BufferedReader(new FileReader(target))) {
      in.lines().forEach(x -> rsl.append(x + " "));
    }
    assertThat(rsl.toString(), is("10:57:01 10:59:01 11:01:01 11:02:01 "));
  }
}
