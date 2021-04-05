package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class DispatchPattern {
  private static final String OUT = "закончить";
  private static final String STOP = "стоп";
  private static final String CONTINUE = "продолжить";

  private final Map<String, Function<String, Boolean>> dispatch = new HashMap<>();
  private Boolean flag = true;

  public Boolean getFlag() {
    return flag;
  }

  public Function<String, Boolean> toStop() {
    return msg -> {
      flag = false;
      return true;
    };
  }

  public Function<String, Boolean> toContinue() {
    return msg -> {
      flag = true;
      return true;
    };
  }

  public Function<String, Boolean> toExit() {
    return msg -> {
      return false;
    };
  }

  public DispatchPattern init() {
    this.load(OUT, this.toExit());
    this.load(STOP, this.toStop());
    this.load(CONTINUE, this.toContinue());
    return this;
  }

  public void load(String str, Function<String, Boolean> handle) {
    this.dispatch.put(str, handle);
  }

  public boolean check(final String msg) {
    if (dispatch.containsKey(msg)) {
      return this.dispatch.get(
        msg
      ).apply(msg);
    } else {
      return true;
    }
  }
}