package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

  private T[] array;
  private int size = 0;
  private int index = 0;
  private int modCount = 0;

  public SimpleArray() {
    this.array = (T[]) new Object[size];
  }

  @Override
  public Iterator<T> iterator() {

    return new Iterator<>() {
      int count = 0;
      final int expectedModCount = modCount;

      @Override
      public boolean hasNext() {
        return count < index;
      }

      @Override
      public T next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        if (expectedModCount != modCount) {
          throw new ConcurrentModificationException();
        }
        return array[count++];
      }
    };
  }

  public T get(int in) {
    Objects.checkIndex(in, index);
    return array[in];
  }

  public void add(T model) {
    if (index == size) {
      T[] array1 = (T[]) new Object[size + 2];
      System.arraycopy(array, 0, array1, 0, array.length);
      array = array1;
      size = array1.length;
    }
    array[index++] = model;
    modCount++;

  }

  public int size() {
    return size;
  }

  public void removeByIndex(int in) {
    Objects.checkIndex(in, index);
    T[] array1 = (T[]) new Object[index - 1];
    System.arraycopy(array, 0, array1, 0, in);
    System.arraycopy(array, in + 1, array1, in, array1.length - in);
    size = array1.length;
    array = array1;
    index--;

    modCount++;
  }
}
