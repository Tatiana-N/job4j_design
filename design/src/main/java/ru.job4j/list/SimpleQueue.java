package ru.job4j.list;

public class SimpleQueue<T> {
  SimpleStack<T> in = new SimpleStack<>();
  SimpleStack<T> out = new SimpleStack<>();
  private int sizeIn = 0;
  private int sizeOut = 0;

  public T poll() {
    if (sizeOut == 0) {
      while (sizeIn != 0) {
        out.push(in.pop());
        sizeIn--;
        sizeOut++;
      }
    }
    sizeOut--;
    return out.pop();

  }

  public void push(T value) {
    in.push(value);
    sizeIn++;
  }
}
