package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgZip {
  private final String[] args;
  Map<String, String> values = new HashMap<>();

  public ArgZip(String[] args) {
    this.args = args;
    if (args.length == 0) {
      throw new IllegalArgumentException("no args");
    }
    int i = 0;
    while (i < args.length) {
      String name = args[i].substring(args[i].indexOf("-") + 1, args[i].indexOf("="));
      String setUp = args[i].substring(args[i].indexOf("=") + 1);
      values.put(name, setUp);
      i++;
    }
  }

  public boolean valid() {
    if (values.containsKey("d") && values.containsKey("e") && values.containsKey("o")) {
      return true;
    }
    throw new IllegalArgumentException("not enought args");
  }

  public String directory() {
    return values.get("d");
  }

  public String exclude() {
    return values.get("e");
  }

  public String output() {
    return values.get("o");
  }
}
