package ru.job4j.list;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Iterator;

public class SimpleSetTest extends TestCase {
  @Test
  public void testAdd() {
    SimpleSet<String> array = new SimpleSet<>();
    array.add("1");
    array.add("1");
    array.add("4");
    array.add("4");
    array.add("5");
    for (String a : array) {
      System.out.println(a);
    }
  }

  @Test
  public void testIterator() {
    SimpleSet<String> array = new SimpleSet<>();
    array.add("1");
    array.add("4");
    array.add("4");
    array.add("4");
    array.add("5");
    Iterator<String> it = array.iterator();
    while (it.hasNext()) {
      System.out.println(it.next());
    }
  }
}
