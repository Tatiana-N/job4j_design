package ru.job4j.list;

import org.junit.Test;

public class SimpleSetTest {
    @Test
    public void testAdd() {
        SimpleSet<String> array = new SimpleSet<>();
        array.add("1");
        array.add("1");
        array.add("4");
        array.add("4");
        array.add("5");
        for (String a : array) {
            System.out.println(a);
        }
    }

    @Test
    public void testIterator() {
        SimpleSet<String> array = new SimpleSet<>();
        array.add("1");
        array.add("4");
        array.add("4");
        array.add("4");
        array.add("5");
        for (String s : array) {
            System.out.println(s);
        }
    }
}
