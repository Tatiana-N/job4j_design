package ru.job4j.generics.simpleArray;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable {
  public static void main(String[] args) {
  }

  @Override
  public Iterator<T> iterator() {
    Iterator<T> it = new Iterator<T>() {
      private int point = 0;

      @Override
      public boolean hasNext() {
        return point < size && array[point] != null;
      }

      @Override
      public T next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return array[point++];
      }
    };
    return it;
  }

  private final int size;
  private T[] array;
  int index = 0;

  public SimpleArray(int size) {
    this.array = (T[]) new Object[size];
    this.size = array.length;
  }

  /**
   *
   * @param
   *
   * model
   * added model to SimpleArray
   */

  public void add(T model) {
    Objects.checkIndex(index, array.length);
    array[index] = model;
    index++;
  }

  /**
   insert the element model into the SimpleArray under the index
   * @param
   * index
   * @param
   * model
   */

  public void set(int index, T model) {
    Objects.checkIndex(index, array.length);
    array[index] = model;

  }

  public void remove(int index) {
    System.arraycopy(array, index + 1, array, index, size - index - 1);
    array[size - 1] = null;
  }

  public T get(int index) {
    return array[index];
  }
}
