package ru.job4j.user;

import java.util.*;

public class User {
  private String name;
  private int children;
  private Calendar birthday;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getChildren() {
    return children;
  }

  public void setChildren(int children) {
    this.children = children;
  }

  public Calendar getBirthday() {
    return birthday;
  }

  public void setBirthday(Calendar birthday) {
    this.birthday = birthday;
  }

  @Override
  public int hashCode() {
    return Integer.parseInt(name.length() + "" + children + "" + birthday.getFirstDayOfWeek());
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof User) {
      User obj1 = (User) obj;
      return this.birthday.equals(obj1.birthday) && this.children == obj1.children && this.name.equals(obj1.name);
    }
    return false;
  }
  public User(String name, int children, GregorianCalendar birthday) {
    this.name = name;
    this.children = children;
    this.birthday = birthday;
  }
  public static void main(String[] args) {
    User us1 = new User("Tom", 3, new GregorianCalendar(1990, Calendar.JANUARY, 1));
    User us2 = new User("Tom", 3, new GregorianCalendar(1990, Calendar.JANUARY, 1));
    Map<User, String> map = new HashMap<>();
    map.put(us1, us1.getName());
    map.put(us2, us2.getName());
    for (User u : map.keySet()) {
      System.out.print(u.hashCode() + " ");
      System.out.println(u.toString());
    }
  }
}
