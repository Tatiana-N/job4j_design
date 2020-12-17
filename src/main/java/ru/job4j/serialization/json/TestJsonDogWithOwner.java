package ru.job4j.serialization.json;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

    //Преобразование JSON в POJO. JsonObject

    /* JSONObject из json-строки строки */
    JSONObject jsonObject1 = new JSONObject(jsonObject);
    /* JSONArray из ArrayList */
    List<String> list = new ArrayList<>();
    list.add("Student");
    list.add("Free");
    JSONArray jsonStatuses = new JSONArray(list);
  //  System.out.println(jsonStatuses);

    /* JSONObject напрямую методом put */
    JSONObject jsonObject2 = new JSONObject();
    jsonObject2.put("name", person1.getName());
    jsonObject2.put("age", person1.getAge());
    jsonObject2.put("dogName", person1.getDog().getName());
    jsonObject2.put("isHomeless", person1.getDog().isHomeless());
    jsonObject2.put("isMarried", person1.isMarried());
    jsonObject2.put("dogKids", person1.getDog().getKids());

    /* Выведем результат в консоль */
    System.out.println(jsonObject2.toString());

    /* Преобразуем объект person в json-строку */
    System.out.println(new JSONObject(person1).toString());
  }
}

