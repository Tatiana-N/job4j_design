package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;


public class SQLTrackerTest {
    SQLTracker sqlTracker;
    String new_item = "new_item";
    String nameForReplace = "replace_name";

    @Before
    public void before() {
        sqlTracker = new SQLTracker();
        sqlTracker.init();
        while (sqlTracker.findAll().size() > 0) {
            sqlTracker.delete(String.valueOf(findFirst().getId()));
        }
        sqlTracker.add(new Item("carrot"));
        sqlTracker.add(new Item("tomato"));
        sqlTracker.add(new Item("pepper"));
        sqlTracker.add(new Item("onion"));
    }


    @Test
    public void add() {
        Item item = new Item(new_item);
        sqlTracker.add(item);
        List<Item> all = sqlTracker.findAll();
        all.forEach(System.out::println);
        System.out.println(item);
        Assertions.assertTrue(all.contains(item));
    }

    public Item findFirst() {
        List<Item> all = sqlTracker.findAll();
        return all.stream().findFirst().get();
    }

    @Test
    public void replace() {
        Item first = findFirst();
        sqlTracker.replace(String.valueOf(first.getId()), new Item(nameForReplace));
        Assertions.assertFalse(sqlTracker.findAll().contains(first));
        first.setName(nameForReplace);
        Assertions.assertTrue(sqlTracker.findAll().contains(first));
    }

    @Test
    public void delete() {
        List<Item> all = sqlTracker.findAll();
        Item first = all.stream().findFirst().get();
        Assertions.assertTrue(sqlTracker.delete(String.valueOf(first.getId())));
        Assertions.assertFalse(sqlTracker.findAll().contains(first));
    }

    @Test
    public void findAll() {
        List<Item> byName = sqlTracker.findAll();
        Assertions.assertEquals(byName.size(), 4);
    }

    @Test
    public void findById() {
        Item first = findFirst();
        Assertions.assertEquals(sqlTracker.findById(String.valueOf(first.getId())), first);
    }

    @Test
    public void findByName() {
        List<Item> byName = sqlTracker.findByName("carrot");
        Assertions.assertEquals(byName.size(), 1);

    }
}