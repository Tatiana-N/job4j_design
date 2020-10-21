package ru.job4j.tasks;

import java.util.HashMap;
import java.util.Map;

public class Task {
  public static void main(String[] args) {
    System.out.println(eq("erty", "yter"));
  }

  public static boolean eq(String left, String right) {
    Map<String, Integer> map1 = new HashMap<>();
    // Map<Character, Integer> map1 = new HashMap<>();
    for (String a : left.split("\"\\s*(\\s|,|!|\\.)\\s*\"")) {
      if (!map1.containsKey(a)) {
        map1.put(a, 1);
      } else {
        map1.put(a, map1.get(a) + 1);
      }
    }
    for (String a : right.split(" ")) {
      if (!map1.containsKey(a)) {
        return false;
      } else {
        map1.put(a, map1.get(a) - 1);
      }
    }
    return true;

  }
}

