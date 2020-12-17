package ru.job4j.serialization.json;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Dog {
  private String name;
  private boolean isHomeless;
  private int age;
  private String[] kids;

  public Dog(String name, int age) {
    this.name = name;
    this.age = age;

  }

  public boolean isHomeless() {
    return isHomeless;
  }

  public void setHomeless(boolean homeless) {
    isHomeless = homeless;
  }

  public String[] getKids() {
    return kids;
  }

  public void setKids(String[] kids) {
    this.kids = kids;
  }

  @Override
  public String toString() {
    return "{"
      + "name : " + name
      + ", age : " + age
      + ", isHomeless : " + isHomeless
      + ", kids : " + Arrays.stream(kids).collect(Collectors.joining("\" , \"", "[\"", "\"]"))
      + "}";
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }
}
