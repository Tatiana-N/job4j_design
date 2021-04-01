package ru.job4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Car {
    String name;
    int number;
    String color;
    String owner;

    public Car(String name, int number, String color, String owner) {
        this.name = name;
        this.number = number;
        this.color = color;
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Car)) {
            return false;
        }
        Car car = (Car) o;
        return number == car.number
                && Objects.equals(name, car.name)
                && Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        System.out.println(31 + number);
        System.out.println();
        System.out.println(((31 + number) * 31 + name.hashCode()) * 31 + color.hashCode());
        return Objects.hash(number, name, color);
    }

    public static void main(String[] args) {
        Car car1 = new Car("Toyota", 123, "Red", "Вася");
        Car car2 = new Car("Toyota", 123, "Red", "Света");
        System.out.println(car1.hashCode());
        System.out.println(car2.hashCode());
        List<String> dest = new ArrayList<>();
        //  List<Object> objects = dest;
        dest.add("dfl,,d");
        System.out.println(dest.get(0));

    }
}
