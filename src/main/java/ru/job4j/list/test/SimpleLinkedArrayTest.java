package ru.job4j.list.test;

import org.junit.Test;
import ru.job4j.list.SimpleLinkedList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SimpleLinkedArrayTest {

  @Test
  public void whenAddThenGet() {
    SimpleLinkedList<String> array = new SimpleLinkedList<>();
    array.add("first");
    array.add("first2");
    array.add("first3");
    array.add("first4");
    array.add("first5");
array.removeByIndex(2);
array.get(0);
    String rsl = array.get(0);
    assertThat(rsl, is("first"));
    rsl = array.get(1);

    assertThat(rsl, is("first2"));
    rsl = array.get(2);
    assertThat(rsl, is("first4")); rsl = array.get(3);
    assertThat(rsl, is("first5"));

  }

  @Test
  public void whenAddThenIt() {
    SimpleLinkedList<String> array = new SimpleLinkedList<>();
    array.add("first");
    String rsl = (String) array.iterator().next();
    assertThat(rsl, is("first"));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void whenGetEmpty() {
    SimpleLinkedList<String> array = new SimpleLinkedList<>();
    array.get(0);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void whenGetOutBound() {
    SimpleLinkedList<String> array = new SimpleLinkedList<>();
    array.add("first");
    array.get(1);
  }

  @Test(expected = NoSuchElementException.class)
  public void whenGetEmptyFromIt() {
    SimpleLinkedList<String> array = new SimpleLinkedList<>();
    array.iterator().next();
  }

  @Test(expected = ConcurrentModificationException.class)
  public void whenCorruptedIt() {
    SimpleLinkedList<String> array = new SimpleLinkedList<>();
    array.add("first");
    Iterator<String> it = array.iterator();
    array.add("second");
    it.next();
  }
}
