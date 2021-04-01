package ru.job4j.spammer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.tracker.ConnectionRollback;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.SQLTracker;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDBTest {
    Properties config;
    SQLTracker sqlTracker;
    String nameForReplace = "replace_name";
    List<Item> list;

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

    @BeforeEach
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

    @Test
    public void add() {
        Item name = new Item("name");
        sqlTracker.add(name);
        list.add(name);
        Assertions.assertEquals(list, sqlTracker.findAll());
    }

    @Test
    public void replace() {
        Item first = list.get(0);
        sqlTracker.replace(String.valueOf(first.getId()), new Item(nameForReplace));
        Assertions.assertFalse(sqlTracker.findAll().contains(first));
        list.remove(first);
        first.setName(nameForReplace);
        list.add(first);
        Assertions.assertEquals(list, sqlTracker.findAll());
    }

    @Test
    public void delete() {
        Item first = list.get(0);
        Assertions.assertTrue(sqlTracker.delete(String.valueOf(first.getId())));
        Assertions.assertFalse(sqlTracker.findAll().contains(first));
        list.remove(first);
        Assertions.assertEquals(list, sqlTracker.findAll());
    }

    @Test
    public void failDelete() {
        Assertions.assertFalse(sqlTracker.delete("15"));
    }

    @Test
    public void findAll() {
        List<Item> all = sqlTracker.findAll();
        Assertions.assertEquals(all.size(), 3);
        Assertions.assertEquals(list, all);
    }

    @Test
    public void findById() {
        Item first = list.get(0);
        Assertions.assertEquals(sqlTracker.findById(String.valueOf(first.getId())), first);
    }

    @Test
    public void findByName() {
        Item first = list.get(0);
        List<Item> byName = sqlTracker.findByName(first.getName());
        Assertions.assertEquals(byName.size(), 1);
        Assertions.assertEquals(byName.get(0), first);

    }
}