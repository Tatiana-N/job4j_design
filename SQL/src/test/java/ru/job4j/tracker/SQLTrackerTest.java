package ru.job4j.tracker;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

public class SQLTrackerTest {
    SQLTracker sqlTracker;
    String newItem = "new_item";
    String nameForReplace = "replace_name";
    private static Properties config;

    public static Connection init() {
        try (InputStream in = SQLTracker.class.getClassLoader().getResourceAsStream("tracker.properties")) {
            config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Before
    public void before() {
        sqlTracker = new SQLTracker(SQLTrackerTest.init());
        sqlTracker.setTableName(config.getProperty("table_name"));
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
        Item item = new Item(newItem);
        sqlTracker.add(item);
        List<Item> all = sqlTracker.findAll();
        all.forEach(System.out::println);
        System.out.println(item);
        Assert.assertTrue(all.contains(item));
    }

    public Item findFirst() {
        List<Item> all = sqlTracker.findAll();
        return all.stream().findFirst().get();
    }

    @Test
    public void replace() {
        Item first = findFirst();
        sqlTracker.replace(String.valueOf(first.getId()), new Item(nameForReplace));
        Assert.assertFalse(sqlTracker.findAll().contains(first));
        first.setName(nameForReplace);
        Assert.assertTrue(sqlTracker.findAll().contains(first));
    }

    @Test
    public void delete() {
        List<Item> all = sqlTracker.findAll();
        Item first = all.stream().findFirst().get();
        Assert.assertTrue(sqlTracker.delete(String.valueOf(first.getId())));
        Assert.assertFalse(sqlTracker.findAll().contains(first));
    }

    @Test
    public void failDelete() {
        Assert.assertFalse(sqlTracker.delete("15"));
    }

    @Test
    public void findAll() {
        List<Item> byName = sqlTracker.findAll();
        Assert.assertEquals(byName.size(), 4);
    }

    @Test
    public void findById() {
        Item first = findFirst();
        Assert.assertEquals(sqlTracker.findById(String.valueOf(first.getId())), first);
    }

    @Test
    public void findByName() {
        List<Item> byName = sqlTracker.findByName("carrot");
        Assert.assertEquals(byName.size(), 1);

    }
}