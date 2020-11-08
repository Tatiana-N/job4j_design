package ru.job4j.analize;

import java.util.*;

public class Analize {
  public Info diff(List<User> previous, List<User> current) {
    Map<Integer, String> changes = new HashMap<>();
    Info info = new Info();
    for (User user : previous) {
      changes.put(user.id, user.name);
    }
    for (User user : current) {
      String name = changes.remove(user.id);
      if (name != null) {
        if (!name.equals(user.name)) {
          info.changed++;
        }
      } else {
        info.added++;
      }
    }
    info.deleted = changes.size();

    return info;
  }

  public static class User {
    private int id;
    private String name;

    public User(int id, String name) {
      this.id = id;
      this.name = name;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof User)) {
        return false;
      }
      User user = (User) o;
      return id == user.id;
    }

    @Override
    public int hashCode() {
      return Objects.hash(id);
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
    curent.add(new User(2, "had")); //
    curent.add(new User(4, "Fad")); //added
    curent.add(new User(5, "Fad")); //changed
    curent.add(new User(8, "Fad")); //added

    Analize an = new Analize();
    Info info = an.diff(previous, curent);
    System.out.println(info.added + " " + info.deleted + " " + info.changed);
  }
}