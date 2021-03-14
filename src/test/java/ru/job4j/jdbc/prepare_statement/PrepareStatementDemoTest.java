package ru.job4j.jdbc.prepare_statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import ru.job4j.jdbc.TableEditor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class PrepareStatementDemoTest {
    Properties properties;
    TableEditor tableEditor;
    PrepareStatementDemo prepareStatement;
    String table_name = "cities";
    {
        try {
            prepareStatement = new PrepareStatementDemo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @After
    public void afterEach(){
        System.out.println("--------------after method-----------------");
        findAll();
        tableEditor.dropTable(table_name);
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
        tableEditor.createTable(table_name);
        tableEditor.addColumn(table_name,"population", "int");
        insert();
        System.out.println("--------------insert for start-----------------");
        findAll();
    }

    @Test
    public void insert() {
        prepareStatement.insert(new City(1,"London", 100_000));
        prepareStatement.insert(new City(2,"Kazan'", 200_000));
    }

    @Test
    public void update() {
        prepareStatement.update(new City(1, "Moskva", 30_000_000));
    }

    @Test
    public void delete() {
        boolean delete = prepareStatement.delete(1);
        Assertions.assertTrue(delete);
        Assertions.assertFalse(prepareStatement.delete(3));
    }

    @Test
    public void findAll() {
        List<City> all = prepareStatement.findAll();
        for (City city : all) {
            System.out.println(city);
        }

    }
}