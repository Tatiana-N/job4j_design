package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator<Integer> {
  private final int[] data;
  private int point = 0;

  public EvenIt(int[] data) {
    this.data = data;
  }

  private boolean hasEven(int[] num) {
    for (int i = point; i < num.length; i++) {
      if (num[i] % 2 == 0) {
        point = i;
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean hasNext() {
    return point < data.length && hasEven(data);
  }

  @Override
  public Integer next() {
    if (!hasEven(data)) {
      throw new NoSuchElementException();
    }
    return data[point++];
  }
}
