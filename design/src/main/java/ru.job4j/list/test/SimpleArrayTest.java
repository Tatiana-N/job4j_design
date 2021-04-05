package ru.job4j.list.test;

import org.junit.Test;
import ru.job4j.list.SimpleArray;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayTest {

  @Test
  public void whenAddThenGet() {
    SimpleArray<String> array = new SimpleArray<>();
    array.add("first");
    array.add("first2");
    array.add("first3");
    array.add("first4");
    array.add("first5");
array.removeByIndex(2);
array.get(0);
    String rsl = array.get(0);
    array.add("12345");
    for (int i = 0; i < array.size(); i++) {
      System.out.println(array.get(i));
    }
    assertThat(rsl, is("first"));
  }

  @Test
  public void whenAddThenIt() {
    SimpleArray<String> array = new SimpleArray<>();
    array.add("first");
    String rsl = array.iterator().next();
    assertThat(rsl, is("first"));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void whenGetEmpty() {
    SimpleArray<String> array = new SimpleArray<>();
    array.get(0);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void whenGetOutBound() {
    SimpleArray<String> array = new SimpleArray<>();
    array.add("first");
    array.get(1);
  }

  @Test(expected = NoSuchElementException.class)
  public void whenGetEmptyFromIt() {
    SimpleArray<String> array = new SimpleArray<>();
    array.iterator().next();
  }

  @Test(expected = ConcurrentModificationException.class)
  public void whenCorruptedIt() {
    SimpleArray<String> array = new SimpleArray<>();
    array.add("first");
    Iterator<String> it = array.iterator();
    array.add("second");
    it.next();
  }
}
