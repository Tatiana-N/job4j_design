package ru.job4j.it;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class FlatMap<T> implements Iterator<T> {
  private final Iterator<Iterator<T>> data;
  private Iterator<T> cursor;

  public FlatMap(Iterator<Iterator<T>> data) {
    this.data = data;
  }
    //Stream.of(data).flatMap(x -> Stream.of(x.next())).filter(Iterator::hasNext).forEach(i -> cursor = i);
  @Override
  public boolean hasNext() {
    while (cursor == null && data.hasNext()) {
      cursor = data.next();
    }
    return cursor != null;
  }

  @Override
  public T next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    T n = cursor.next();
    if (!cursor.hasNext()) {
      cursor = null;
    }
    return n;
  }

  public static void main(String[] args) {
    Iterator<Iterator<Integer>> data = List.of(
      List.of(1, 2, 3).iterator(),
      List.of(4, 5, 6).iterator(),
      List.of(7, 8, 9).iterator()
    ).iterator();
    FlatMap<Integer> flat = new FlatMap<>(data);
    while (flat.hasNext()) {
      System.out.println(flat.next());
    }
  }
}