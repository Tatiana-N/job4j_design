package ru.job4j.tracker_new;


import java.util.Objects;

public class Item implements Comparable<Item> {
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
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
    public static void main(String[] args) {
        System.out.println("gg");
        SQLTracker sqlTracker = new SQLTracker();
        sqlTracker.init();

        System.out.println(sqlTracker.add(new Item("Pig")));
    }
    @Override
    public int compareTo(Item item) {
     return this.name.compareTo(item.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getId() == item.getId() && Objects.equals(getName(), item.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}