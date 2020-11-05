package ru.job4j.analize;

public class Analize {


  public Info diff(List<User> previous, List<User> current) {
    return null;

  }

  public static class User {
    int id;
    String name;
  }

  public static class Info {

    int added;
    int changed;

    int deleted;

  }

}