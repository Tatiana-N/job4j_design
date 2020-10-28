package ru.job4j.map;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Iterable<K> {
  Object[][] array;
  int count = 0;
  double loadFactor;

  public SimpleMap(double loadFactor) {
    this.array = new Object[2][3];
    this.loadFactor = loadFactor;
  }

  public SimpleMap(int size, double loadFactor) {
    this.array = new Object[2][size];
    this.loadFactor = loadFactor;
  }

  int hash(int hashCode, Object[][] array) {
    return hashCode % array[1].length;
  }

  public boolean insert(K key, V value) {
    int hash = hash(key.hashCode(), array);
    if (array[0][hash] == null && count < array[1].length * loadFactor) {
      array[0][hash] = key;
      array[1][hash] = value;
      count++;
      return true;
    } else if (count > array[1].length * loadFactor) {
      Object[][] ar = new Object[2][array[1].length * 2];
      for (int i = 0; i < array[1].length; i++) {
        if (array[0][i] != null) {
          int hk = array[0][i].hashCode();
          int hash2 = hash(hk, ar);
          ar[0][hash2] = array[0][i];
          ar[1][hash2] = array[1][i];
        }
      }
      array = ar;
      insert(key, value);
      return true;
    }
//    Objects.checkIndex(hash, array.length);

    return false;
  }

  public V get(K key) {
    if (array[0][hash(key.hashCode(), array)] != null) {
      return (V) array[1][hash(key.hashCode(), array)];
    }
    return null;
  }

  public boolean delete(K key) {
    int hash = hash(key.hashCode(), array);
    if (array[0][hash] != null) {
      array[0][hash] = null;
      array[1][hash] = null;
      count--;
      return true;
    }
    return false;
  }

  @Override
  public Iterator<K> iterator() {
    return new Iterator<>() {
      int point = 0;
      int object = 0;

      @Override
      public boolean hasNext() {
        return object < count;
      }

      @Override
      public K next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        while (array[0][point] == null) {
          point++;
        }
        object++;
        return (K) array[0][point++];
      }
    };
  }
}
