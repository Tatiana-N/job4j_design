package ru.job4j.serialization.json;

public class Person {
  private String name;
  private int age;
  private  boolean isMarried;
  private Dog dog;

  public Person(String name, int age, Dog dog) {
    this.name = name;
    this.age = age;
    this.dog = dog;
  }

  public boolean isMarried() {
    return isMarried;
  }

  public void setMarried(boolean married) {
    isMarried = married;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public Dog getDog() {
    return dog;
  }

  @Override
  public String toString() {
      return "{"
        + "name : " + name
        + ", age : " + age
        + ", isMarried : " + isMarried
        + dog
        + "}";
  }
}
