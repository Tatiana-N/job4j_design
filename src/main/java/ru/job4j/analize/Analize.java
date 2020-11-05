package ru.job4j.analize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analize {
  public Info diff(List<User> previous, List<User> current) {
    Map<Integer, String> prev = new HashMap<>();
    Map<Integer, String> cur = new HashMap<>();

    for (User user : current) {
      cur.put(user.id, user.name);
    }
    Info info = new Info();
    for (User user : previous) {
      prev.put(user.id, user.name);
    }

    for (Integer id : prev.keySet()) {
      if (cur.containsKey(id)) {
        if (!cur.get(id).equals(prev.get(id))) {
          info.changed++;
        }
      }
    }
    Map<Integer, String> prev2 = new HashMap<>(prev);
    prev.keySet().removeAll(cur.keySet());
    info.deleted = prev.size();
    cur.keySet().removeAll(prev2.keySet());
    info.added = cur.size();
    return info;

  }

  public static class User {
    private int id;
    private String name;

    public User(int id, String name) {
      this.id = id;
      this.name = name;
    }
  }

  public static class Info {

    int added;
    int changed;
    int deleted;

    public Info() {
      this.added = 0;
      this.changed = 0;
      this.deleted = 0;
    }
  }

  public static void main(String[] args) {
    List<User> previous = new ArrayList<>();
    List<User> curent = new ArrayList<>();
    previous.add(new User(1, "Fad")); //
    previous.add(new User(2, "vad")); //deleted
    previous.add(new User(3, "had")); //
    previous.add(new User(4, "yad")); //deleted
    previous.add(new User(5, "kad")); //
    curent.add(new User(1, "Fan")); //changed
    curent.add(new User(3, "had")); //
    curent.add(new User(6, "Fad")); //added
    curent.add(new User(5, "Fad")); //changed
    curent.add(new User(8, "Fad")); //added

    Analize an = new Analize();
    Info info = an.diff(previous, curent);
    System.out.println(info.added + " " + info.deleted + " " + info.changed);
  }
}