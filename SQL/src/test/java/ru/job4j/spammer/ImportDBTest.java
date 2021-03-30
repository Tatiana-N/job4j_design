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
import java.util.List;
import java.util.Properties;

public class ImportDBTest {
    Properties config;
    SQLTracker sqlTracker;
    String nameForReplace = "replace_name";

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
        sqlTracker = new SQLTracker((ConnectionRollback.create(this.init())));
        sqlTracker.setTable_name(config.getProperty("table_name"));
        sqlTracker.add(new Item("apple"));
        sqlTracker.add(new Item("pineapple"));
        sqlTracker.add(new Item("tomato"));
    }

    @Test
    public void createItem() {
        sqlTracker.add(new Item("name"));
    }

    public Item findFirst() {
        List<Item> all = sqlTracker.findAll();
        return all.isEmpty() ? null : all.get(0);
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
        Item first = all.isEmpty() ? null : all.get(0);
        assert first != null;
        Assertions.assertTrue(sqlTracker.delete(String.valueOf(first.getId())));
        Assertions.assertFalse(sqlTracker.findAll().contains(first));
    }

    @Test
    public void failDelete() {
        Assertions.assertFalse(sqlTracker.delete("15"));

    }

    @Test
    public void findAll() {
        List<Item> byName = sqlTracker.findAll();
        Assertions.assertEquals(byName.size(), 3);
    }

    @Test
    public void findById() {
        Item first = findFirst();
        Assertions.assertEquals(sqlTracker.findById(String.valueOf(first.getId())), first);
    }

    @Test
    public void findByName() {
        List<Item> byName = sqlTracker.findByName("tomato");
        Assertions.assertEquals(byName.size(), 1);

    }
}