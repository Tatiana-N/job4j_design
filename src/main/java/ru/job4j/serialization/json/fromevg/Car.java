package ru.job4j.serialization.json.fromevg;

import org.json.JSONObject;
import ru.job4j.serialization.json.Person;

import java.util.Arrays;

public class Car {
    private boolean res;
    private int number;
    private String name;
    private Person person;
    private int[] years;

    @Override
    public String toString() {
        return "Car{"
                + "res=" + res
                + ", number=" + number
                + ", name='" + name + '\''
                + ", person=" + person
                + ", years=" + Arrays.toString(years)
                + '}';
    }

    public Car(boolean res, int number, String name, Person person, int[] years) {
        this.res = res;
        this.number = number;
        this.name = name;
        this.person = person;
        this.years = years;
    }

    public boolean isRes() {
        return res;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public Person getPerson() {
        return person;
    }

    public int[] getYears() {
        return years;
    }

//        private static class Person {
//            private String id;
//            private int number;
//            public Person(String id, int number) {
//                this.id = id;
//                this.number = number;
//            }
//
//            public String getId() {
//                return id;
//            }
//
//            public int getNumber() {
//                return number;
//            }
//        }

    public static void main(String[] args) {
        /* JSONObject из json-строки строки */
        JSONObject jsonPerson = new JSONObject("{\"id\":\"1245\", \"number\":\"654\"}");
        /* JSONObject напрямую методом put */
        final Car car = new Car(true, 254, "BMW", new Person("1245", 654, null), new int[]{1980, 1982, 1952});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("res", car.isRes());
        jsonObject.put("number", car.getNumber());
        jsonObject.put("name", car.getName());
        jsonObject.put("person", jsonPerson);
        jsonObject.put("years", car.getYears());
        System.out.println(jsonObject.toString());
        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(car).toString());
    }




    /*
    {
        "res": false,
        "number": 254,
        "name": "BMW",
        "person" {
            "id": "1245"
        },
        "years": ["1980", "1982", "1952"]
    }
     */
}

