package ru.job4j.it.test;

import org.junit.Test;
import ru.job4j.it.ArrayIt;

import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class ArrayTtTest {
  @Test
  public void whenMultiCallhasNextThenTrue() {
    ArrayIt it = new ArrayIt(
            new int[] {1, 2, 3}
            );
    assertThat(it.hasNext(), is(true));

    assertThat(it.hasNext(), is(true));
  }

  @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
    ArrayIt it = new ArrayIt(
            new int[] {}
            );
    it.next();
  }

  @Test
    public void whenReadSequence() {
    ArrayIt it = new ArrayIt(
                new int[] {1, 2, 3}
        );
    assertThat(it.next(), is(1));
    assertThat(it.next(), is(2));
    assertThat(it.next(), is(3));
  }
}
