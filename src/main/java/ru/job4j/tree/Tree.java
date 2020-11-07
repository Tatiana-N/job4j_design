package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

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
    Predicate predicate = (Predicate<Node<E>>) node -> {
      if (node.children.size() > 2) {
        return true;
      } else {
        return false;
      }
    };
    return findByPredicate(predicate).isEmpty();
  }

  @Override
  public Optional<Node<E>> findBy(E value) {
    Predicate predicate = (Predicate<Node<E>>) node -> {
      if (value.equals(node.value)) {
        return true;
      }
      return false;
    };
    return findByPredicate(predicate);

  }

  public Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
    Optional<Node<E>> rsl = Optional.empty();
    Queue<Node<E>> data = new LinkedList<>();
    data.offer(this.root);
    while (!data.isEmpty()) {
      Node<E> el = data.poll();
      if (condition.test(el)) {
        rsl = Optional.of(el);
        break;
      }
      data.addAll(el.children);
    }
    return rsl;
  }
}