package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
  private final int[][] data;
  private int row = 0;
  private int column = 0;

  public MatrixIt(int[][] data) {
    this.data = data;
  }

  @Override
  public boolean hasNext() {
    for (int i = row; i < data.length; i++) {
      if (data[i].length != 0) {
        row = i;
        return true;
      }
    }
    return false;
  }

  @Override
  public Integer next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    if (column < data[row].length) {
      return data[row][column++];
    } else {
      column = 0;
    }
    if (row < data.length) {
      row++;
      hasNext();
      return data[row][column++];
    } else {
      row = 0;
    }
    return null;
  }
}
