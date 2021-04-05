package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class DispatchPatternForServer {
  private static final String HELLO = "Hello";
  private static final String EXIT = "Exit";
  private static final String ANY = "What did you say?";

  private final Map<String, Function<String, String>> dispatch = new HashMap<>();
  private Boolean flag = true;

  public Boolean getFlag() {
    return flag;
  }

  public Function<String, String> exit() {
    return msg -> {
      flag = false;
      return EXIT;
    };
  }

  public Function<String, String> sayHello() {
    return msg -> {
      return HELLO;
    };
  }

  public Function<String, String> anyMsg() {
    return msg -> {
      return ANY;
    };
  }

  public DispatchPatternForServer init() {
    this.load(ANY, this.anyMsg());
    this.load(HELLO, this.sayHello());
    this.load(EXIT, this.exit());
    return this;
  }

  public void load(String str, Function<String, String> handle) {
    this.dispatch.put(str, handle);
  }

  public String check(final String msg) {
    if (dispatch.containsKey(msg)) {
      return this.dispatch.get(
        msg
      ).apply(msg);
    } else {
      return ANY;
    }
  }
}