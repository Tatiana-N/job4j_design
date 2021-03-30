package ru.job4j.tracker;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Item implements Comparable<Item> {
    private int id;
    private String name;

    public Item(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Item item) {
        return this.name.compareTo(item.getName());
    }
}
