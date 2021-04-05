package ru.job4j.tracker;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Item implements Comparable<ru.job4j.tracker.Item> {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(ru.job4j.tracker.Item item) {
        return this.name.compareTo(item.getName());
    }
}
