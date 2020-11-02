package ru.job4j.map;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Iterable<K> {
  private Node<K, V>[] array;
  private int count = 0;
  private double loadFactor;

  public SimpleMap(double loadFactor) {
    this.array = new Node[16];
    this.loadFactor = loadFactor;
  }

  public SimpleMap(int size, double loadFactor) {
    this.array = new Node[size];
    this.loadFactor = loadFactor;
  }

  int hash(int hashCode, Object[] array) {
    return hashCode % array.length;
  }

  public boolean insert(K key, V value) {
    int hash = hash(key.hashCode(), array);
    if (array[hash] == null && count < array.length * loadFactor) {
      array[hash] = new Node<>(key, value);
      count++;
      return true;
    } else if (count > array.length * loadFactor) {
      Node<K, V>[] ar = new Node[array.length * 2];
      for (Node<K, V> node : array) {
        if (node != null) {
          int hk = node.key.hashCode();
          int hash2 = hash(hk, ar);
          ar[hash2] = node;
        }
      }
      array = ar;
      insert(key, value);
    }
    return false;
  }

  public V get(K key) {
    int hk = key.hashCode();
    int hash = hash(hk, array);
    if (array[hash] != null) {
      return array[hash].value;
    }
    return null;
  }

  public boolean delete(K key) {
    int hk = key.hashCode();
    int hash = hash(hk, array);
    if (array[hash] != null) {
      array[hash] = null;
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
        while (array[point] == null) {
          point++;
        }
        object++;
        return array[point++].key;
      }
    };
  }

  class Node<K, V> {
    V value;
    K key;

    public Node(K key, V value) {
      this.value = value;
      this.key = key;
    }
  }
}
