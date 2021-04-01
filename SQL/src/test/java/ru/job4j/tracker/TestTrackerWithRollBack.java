package ru.job4j.tracker;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TestTrackerWithRollBack {
    private Properties config;
    private SQLTracker sqlTracker;
    private String nameForReplace = "replace_name";
    private List<Item> list;

    public Connection init() {
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
    public void before() throws SQLException {
        list = new ArrayList<>();
        sqlTracker = new SQLTracker((ConnectionRollback.create(this.init())));
        sqlTracker.setTableName(config.getProperty("table_name"));
        Item apple = new Item("apple");
        Item pineapple = new Item("pineapple");
        Item tomato = new Item("tomato");
        sqlTracker.add(apple);
        sqlTracker.add(pineapple);
        sqlTracker.add(tomato);
        list.add(apple);
        list.add(pineapple);
        list.add(tomato);
    }

    @After
    public void after() throws Exception {
     sqlTracker.close();
    }

    @Test
    public void add() {
        Item name = new Item("name");
        sqlTracker.add(name);
        list.add(name);
        Assert.assertEquals(list, sqlTracker.findAll());
    }

    @Test
    public void replace() {
        Item first = list.get(0);
        sqlTracker.replace(String.valueOf(first.getId()), new Item(nameForReplace));
        Assert.assertFalse(sqlTracker.findAll().contains(first));
        list.remove(first);
        first.setName(nameForReplace);
        list.add(first);
        Assert.assertEquals(list, sqlTracker.findAll());
    }

    @Test
    public void delete() {
        Item first = list.get(0);
        Assert.assertTrue(sqlTracker.delete(String.valueOf(first.getId())));
        Assert.assertFalse(sqlTracker.findAll().contains(first));
        list.remove(first);
        Assert.assertEquals(list, sqlTracker.findAll());
    }

    @Test
    public void failDelete() {
        Assert.assertFalse(sqlTracker.delete("15"));
    }

    @Test
    public void findAll() {
        List<Item> all = sqlTracker.findAll();
        Assert.assertEquals(all.size(), 3);
        Assert.assertEquals(list, all);
    }

    @Test
    public void findById() {
        Item first = list.get(0);
        Assert.assertEquals(sqlTracker.findById(String.valueOf(first.getId())), first);
    }

    @Test
    public void findByName() {
        Item first = list.get(0);
        List<Item> byName = sqlTracker.findByName(first.getName());
        Assert.assertEquals(byName.size(), 1);
        Assert.assertEquals(byName.get(0), first);
    }
}