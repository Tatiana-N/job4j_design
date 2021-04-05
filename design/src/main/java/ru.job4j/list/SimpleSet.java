package ru.job4j.list;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<E> implements Iterable<E> {
  private SimpleArray<E> array;

  public SimpleSet() {
    this.array = new SimpleArray<>();
  }

  public boolean canAdd(E e) {
    for (E h : array) {
      if (Objects.equals(e, h)) {
        return false;
      }
    }
    return true;
  }

  public void add(E e) {
    if (canAdd(e)) {
      array.add(e);
    }
  }

  @Override
  public Iterator<E> iterator() {
    return array.iterator();
  }
}
