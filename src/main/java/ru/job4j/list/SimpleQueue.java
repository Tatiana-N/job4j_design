package ru.job4j.list;

public class SimpleQueue<T> {
  SimpleStack<T> in = new SimpleStack<>();
  SimpleStack<T> out = new SimpleStack<>();
  private int sizeIn = 0;
  private int sizeOut = 0;

  public T poll() {
    sizeOut = sizeIn;
    while (sizeIn != 0) {
      out.push(in.pop());
      sizeIn--;
    }
    sizeOut--;
    return out.pop();

  }

  public void push(T value) {
    sizeIn = sizeOut != 0 ? sizeOut : sizeIn;
    while (sizeOut != 0) {
      in.push(out.pop());
      sizeOut--;
    }
    in.push(value);
    sizeIn++;
  }
}
