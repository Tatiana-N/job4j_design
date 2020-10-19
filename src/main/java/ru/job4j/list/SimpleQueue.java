package ru.job4j.list;

public class SimpleQueue<T> {
  SimpleStack<T> in = new SimpleStack<>();
  SimpleStack<T> out = new SimpleStack<>();
  private int sizeIn = 0;

  public T poll() {
    while (sizeIn != 0) {
      out.push(in.pop());
      sizeIn--;
    }
    return out.pop();
  }

  public void push(T value) {
    in.push(value);
    sizeIn++;
  }
}
