package ru.job4j.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableEditorTest {
    Properties properties;
    String table_name = "new_table";
    String new_column = "new_column";
    String column_name = " name ";
    String type = "varchar(50)";
    String old_name = "old_name";
    TableEditor tableEditor = null;
    Pattern pattern = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS);
    List<String> split;

    @Before
    public void beforeClass() {
        properties = new Properties();
        FileInputStream fis;
        try {
            fis = new FileInputStream("src/main/resources/app.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableEditor = new TableEditor(properties);
        split = new ArrayList<>();
        tableEditor.createTable(table_name);
    }

    @After
    public void dropTable() {
        split = new ArrayList<>();
        tableEditor.dropTable(table_name);
        Matcher matcher = pattern.matcher(tableEditor.getScheme(table_name));
        while (matcher.find()) {
            split.add(matcher.group());
        }
        Assertions.assertEquals(split.size(), 2);
        Assertions.assertEquals(split.get(0), "column");
        Assertions.assertEquals(split.get(1), "type");
    }

    @Test
    public void createTable() {
        Matcher matcher = pattern.matcher(tableEditor.getScheme(table_name));
        while (matcher.find()) {
            split.add(matcher.group());
        }
        Assertions.assertEquals(split.size(), 6);
    }

    @Test
    public void addColumn() {
        tableEditor.addColumn(table_name, new_column, type);
        Matcher matcher = pattern.matcher(tableEditor.getScheme(table_name));
        while (matcher.find()) {
            split.add(matcher.group());
        }
        Assertions.assertTrue(split.contains(new_column));
        Assertions.assertFalse(split.contains(type));
    }

    @Test
    public void dropColumn() {
        tableEditor.dropColumn(table_name, column_name);
        Matcher matcher = pattern.matcher(tableEditor.getScheme(table_name));
        while (matcher.find()) {
            split.add(matcher.group());
        }
        Assertions.assertFalse(split.contains(column_name));
    }

    @Test
    public void renameColumn() {
        tableEditor.renameColumn(table_name, column_name, old_name);
        Matcher matcher = pattern.matcher(tableEditor.getScheme(table_name));
        while (matcher.find()) {
            split.add(matcher.group());
        }
        Assertions.assertTrue(split.contains(old_name));
        Assertions.assertFalse(split.contains(column_name));
    }
}