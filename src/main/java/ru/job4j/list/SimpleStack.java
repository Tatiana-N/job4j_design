package ru.job4j.list;

public class SimpleStack<T> {
  private ForwardLinked<T> linked = new ForwardLinked<>();

  public T pop() {
    ForwardLinked.Node<T> removed;
    if (linked.getHead() == null) {
      return null;
    }
    removed = getTail(linked.getHead());
    T saved = removed.value;
    removed.value = null;
    removed = getTail(linked.getHead());
    removed.next = null;
    return saved;
  }

  private ForwardLinked.Node<T> getTail(ForwardLinked.Node<T> e) {
    while (e.next != null) {
      ForwardLinked.Node<T> temp = e.next;
      if (temp.value == null) {
        return e;
      }
      e = e.next;
    }
    return e;
  }

  public void push(T value) {
    ForwardLinked.Node<T> t;
    if (linked.getHead() != null) {
      t = getTail(linked.getHead());
      t.next = new ForwardLinked.Node<>(value, null);
    } else {
      linked.add(value);
    }
  }
}