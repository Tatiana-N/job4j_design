package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements Iterable<E> {
  transient Node<E> first;
  transient Node<E> last;
  private int modCount = 0;
  private int size = 0;

  public SimpleLinkedList() {
    first = null;
    last = null;
  }

  public void add(E value) {
    if (first == null) {
      first = new Node<>(value, null, null);
      last = first;
    } else {
      Node<E> n;
      if (first.next == null) {
        n = new Node<>(value, first, null);
        first.next = n;
      } else {
        n = new Node<>(value, last, null);
        last.next = n;
      }
      last = n;
    }
    size++;
    modCount++;
  }

  public int size() {
    return size;
  }

  public E get(int in) {
    Objects.checkIndex(in, size);
    Node<E> a = first;
    for (int i = 0; i < in; i++) {
      a = a.next;
    }
    return a.item;
  }

  public E removeByIndex(int in) {
    Objects.checkIndex(in, size);
    Node<E> a = first;
    if (a != null) {
      if (first.next == null) {
        first.item = null;
        last.item = null;
      }
      for (int i = 0; i < in; i++) {
        a = a.next;
      }
      Node<E> prev = a.prev;
      Node<E> next = a.next;
      if (prev == null) {
        first = a.next;
        first.prev = null;
      } else {
        if (next == null) {
          last = a.prev;
          last.next = null;
        } else {
          prev.next = next;
          next.prev = prev;
        }
      }
      size--;
      modCount++;
    } else {
      throw new NoSuchElementException();
    }
    return a.item;
  }

  @Override
  public Iterator<E> iterator() {

    return new Iterator<>() {
      Node<E> a = first;
      int count = 0;
      final int expectedModCount = modCount;

      @Override
      public boolean hasNext() {
        return count < size;
      }

      @Override
      public E next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        if (expectedModCount != modCount) {
          throw new ConcurrentModificationException();
        }
        Node<E> b = a;
        a = a.next;
        count++;
        return b.item;
      }
    };
  }

  private static class Node<E> {
    E item;
    Node<E> prev;
    Node<E> next;

    public Node(E item, Node<E> prev, Node<E> next) {
      this.item = item;
      this.prev = prev;
      this.next = next;
    }
  }
}
