package ru.job4j.jdbc.preparestatement;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.jdbc.TableEditor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class PrepareStatementDemoTest {
    Properties properties;
    TableEditor tableEditor;
    PrepareStatementDemo prepareStatement;
    String tableName = "cities";

    {
        try {
            prepareStatement = new PrepareStatementDemo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void afterEach() {
        System.out.println("--------------after method-----------------");
        findAll();
        tableEditor.dropTable(tableName);
    }

    @Before
    public void initConnection() throws Exception {
        prepareStatement.initConnection();
        properties = new Properties();
        FileInputStream fis;
        try {
            fis = new FileInputStream("src/main/resources/app.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableEditor = new TableEditor(properties);
        System.out.println("--------------create Table-----------------");
        tableEditor.createTable(tableName);
        tableEditor.addColumn(tableName, "name", "varchar(50)");
        tableEditor.addColumn(tableName, "population", "int");
        insert();
        System.out.println("--------------insert for start-----------------");
        findAll();
    }

    @Test
    public void insert() {
        prepareStatement.insert(new City(1, "London", 100_000));
        prepareStatement.insert(new City(2, "Kazan'", 200_000));
    }

    @Test
    public void update() {
        prepareStatement.update(new City(1, "Moskva", 30_000_000));
    }

    @Test
    public void delete() {
        boolean delete = prepareStatement.delete(1);
        Assert.assertTrue(delete);
        Assert.assertFalse(prepareStatement.delete(3));
    }

    @Test
    public void findAll() {
        List<City> all = prepareStatement.findAll();
        for (City city : all) {
            System.out.println(city);
        }

    }
}