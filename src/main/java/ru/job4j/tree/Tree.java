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
    Optional<Node<E>> optionalNode = findBy(parent);
    if (!findBy(child).isEmpty() || optionalNode.isEmpty()) {
      return false;
    }
    Node<E> nodeParent = optionalNode.get();
    nodeParent.children.add(new Node<>(child));
    return true;
  }

  public boolean isBinary() {
    return findByPredicate(eNode -> eNode.children.size() > 2).isEmpty();
  }

  @Override
  public Optional<Node<E>> findBy(E value) {
    return findByPredicate(eNode -> value.equals(eNode.value));

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