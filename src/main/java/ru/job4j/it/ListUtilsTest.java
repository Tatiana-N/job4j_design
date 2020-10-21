package ru.job4j.it;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

  @Test
  public void whenAddBefore() {
    List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
    ListUtils.addBefore(input, 0, 2);
    assertThat(Arrays.asList(2, 1, 3), Is.is(input));
  }

  @Test
  public void whenAddAfter() {
    List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
    ListUtils.addAfter(input, 0, 2);
    assertThat(Arrays.asList(1, 2, 3), Is.is(input));
  }

  @Test
  public void whenreplaceIf() {
    List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 4, 5, 7));
    ListUtils.replaceIf(input, Predicate.isEqual(4), 45);
    assertThat(Arrays.asList(1, 3, 45, 5, 7), Is.is(input));
  }

  @Test
  public void whenRemoveAll() {
    List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 4, 5, 6));
    List<Integer> input2 = new ArrayList<>(Arrays.asList(5, 1, 4, 2));
    ListUtils.removeAll(input, input2);
    assertThat(Arrays.asList(3, 6), Is.is(input));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void whenAddBeforeWithInvalidIndex() {
    List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
    ListUtils.addBefore(input, 3, 2);
  }

}