package ru.job4j.serialization.json;

import com.google.gson.Gson;

public class TestJsonDogWithOwner {

  public static void main(String[] args) {
    Dog dog = new Dog("Бобик", 3);
    dog.setKids(new String[]{"Клык", "Жужа ", "Морда"});
    dog.setHomeless(false);
    Person person1 = new Person("Анжелика", 31, dog);
    person1.setMarried(true);
    Gson gson = new Gson();
    System.out.println(gson.toJson(person1));
    String jsonObject = "{"
      + "\"name\" : \"Дядя Ваня\","
      + "\"age\" : 39,"
      + "\"isMarried\": false,"
      + "\"dog\": {"
      + "\"name\": \"Шарик\","
      + "\"age\": 3,"
      + "\"isHomeless\": false,"
      + "\"kids\": ["
      + "\"Pop\", \"Pip\", \"Don\", \"Sara\"]"
      + "}}";

    Person person2 = gson.fromJson(jsonObject, Person.class);
    System.out.println(person2);
  }
}

