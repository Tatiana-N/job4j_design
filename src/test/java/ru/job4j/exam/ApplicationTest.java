package ru.job4j.exam;

import org.junit.Test;

import java.io.ByteArrayInputStream;

public class ApplicationTest {
  @Test
  public void testStart() {
    ByteArrayInputStream in = new ByteArrayInputStream(("user1\n" +
      "xxx@ya.ru,foo@gmail.com,lol@mail.ru\n" +
      "user2 \n" +
      "foo@gmail.com,ups@pisem.net\n" +
      "user3\n" +
      "xyz@pisem.net,vasya@pupkin.com\n" +
      "user4\n" +
      "ups@pisem.net,aaa@bbb.ru\n" +
      "user5 \n" +
      "xyz@pisem.net\n" +
      "выход\n").getBytes());
    System.setIn(in);
    Application ap = new Application();
    ap.start();
  }
}