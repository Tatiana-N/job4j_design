package ru.job4j.jdbc.tracker;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.jdbc.TableEditor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class SQLTrackerTest {
    TableEditor tableEditor;
    String table_name = "store";
    Properties properties = new Properties();

    public void beforeAll() {
         properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/app.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Before
    public void before() {
       //beforeAll();
        TableEditor tableEditor = new TableEditor(properties);
        tableEditor.createTable(table_name);
        tableEditor.addColumn(table_name,"name", "varchar(50)");
    }

    @Test
    public void add() {

SQLTracker sqlTracker = new SQLTracker();
        sqlTracker.init();

        System.out.println(sqlTracker.add(new Item("Pig")));
    }

    @Test
    public void replace() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findByName() {
    }
}