package ru.job4j.tree;

import java.util.*;

class Tree<E> implements SimpleTree<E> {
  private final Node<E> root;

  Tree(final E root) {
    this.root = new Node<>(root);
  }

  @Override
  public boolean add(E parent, E child) {
    if (!findBy(child).isEmpty() || findBy(parent).isEmpty()) {
      return false;
    }
    Node par = findBy(parent).get();
    par.children.add(new Node<>(child));
    return true;
  }

  public boolean isBinary() {
    Queue<Node> check = new LinkedList<>();
    check.offer(root);
    while (!check.isEmpty()) {
      Node temp = check.poll();
      if (temp.children.size() > 2) {
        return false;
      }
      check.addAll(temp.children);
    }

    return true;
  }

  @Override
  public Optional<Node<E>> findBy(E value) {
    Optional<Node<E>> rsl = Optional.empty();
    Queue<Node<E>> data = new LinkedList<>();
    data.offer(this.root);
    while (!data.isEmpty()) {
      Node<E> el = data.poll();
      if (el.value.equals(value)) {
        rsl = Optional.of(el);
        break;
      }
      data.addAll(el.children);
    }
    return rsl;
  }
}