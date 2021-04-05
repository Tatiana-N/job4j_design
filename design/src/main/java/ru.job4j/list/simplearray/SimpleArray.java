package ru.job4j.list.simplearray;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
  public static void main(String[] args) {
  }

  private final int size;
  private T[] array;
  private int index = 0;

  public SimpleArray(int size) {
    this.array = (T[]) new Object[size];
    this.size = array.length;
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<>() {
      private int point = 0;

      @Override
      public boolean hasNext() {
        return point < size;
      }

      @Override
      public T next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return array[point++];
      }
    };
  }

  public void add(T model) {
    if (canAdd()) {
      array[index] = model;
      index++;
    }
  }

  public boolean canAdd() {
    return index < array.length;
  }

  public void set(int in, T model) {
    Objects.checkIndex(in, index);
    array[in] = model;
  }

  public void remove(int in) {
    Objects.checkIndex(in, index);
    System.arraycopy(array, in + 1, array, in, size - in - 1);
    array[size - 1] = null;
    index--;
  }

  public T get(int index) {
    return array[index];
  }
}
