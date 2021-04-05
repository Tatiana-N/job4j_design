package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
  private Node<T> head;

  public void add(T value) {
    Node<T> node = new Node<>(value, null);
    if (head == null) {
      head = node;
      return;
    }
    Node<T> tail = head;
    while (tail.next != null) {
      tail = tail.next;
    }
    tail.next = node;
  }

  public void revert() {
    Node<T> tail = head;
    head = null;
    while (tail != null) {
      addFirst(tail.value);
      tail = tail.next;
    }
  }

  public T deleteFirst() {
    Node<T> n = head;
    if (head != null) {
      head = head.next;
      return n.value;
    }
    throw new NoSuchElementException();
  }

  public void addFirst(T value) {
    this.head = new Node<>(value, head);
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<>() {
      Node<T> node = head;

      @Override
      public boolean hasNext() {
        return node != null;
      }

      @Override
      public T next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        T value = node.value;
        node = node.next;
        return value;
      }
    };
  }

  static class Node<T> {
    T value;
    Node<T> next;

    public Node(T value, Node<T> next) {
      this.value = value;
      this.next = next;
    }
  }
}