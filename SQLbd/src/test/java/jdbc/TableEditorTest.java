package jdbc;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableEditorTest {
    Properties properties;
    String newTable = "cities";
    String newColumn = "new_column";
    String columnName = " name ";
    String type = "varchar(50)";
    String oldName = "old_name";
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
        tableEditor.createTable(newTable);
        tableEditor.addColumn(newTable, "name", type);
    }

    @After
    public void dropTable() {
        split = new ArrayList<>();
        tableEditor.dropTable(newTable);
        Matcher matcher = pattern.matcher(tableEditor.getScheme(newTable));
        while (matcher.find()) {
            split.add(matcher.group());
        }
        Assert.assertEquals(split.size(), 2);
        Assert.assertEquals(split.get(0), "column");
        Assert.assertEquals(split.get(1), "type");
    }

    @Test
    public void createTable() {
        Matcher matcher = pattern.matcher(tableEditor.getScheme(newTable));
        while (matcher.find()) {
            split.add(matcher.group());
        }
        Assert.assertEquals(split.size(), 6);
    }

    @Test
    public void addColumn() {
        tableEditor.addColumn(newTable, newColumn, type);
        Matcher matcher = pattern.matcher(tableEditor.getScheme(newTable));
        while (matcher.find()) {
            split.add(matcher.group());
        }
        Assert.assertTrue(split.contains(newColumn));
        Assert.assertFalse(split.contains(type));
    }

    @Test
    public void dropColumn() {
        tableEditor.dropColumn(newTable, columnName);
        Matcher matcher = pattern.matcher(tableEditor.getScheme(newTable));
        while (matcher.find()) {
            split.add(matcher.group());
        }
        Assert.assertFalse(split.contains(columnName));
    }

    @Test
    public void renameColumn() {
        tableEditor.renameColumn(newTable, columnName, oldName);
        Matcher matcher = pattern.matcher(tableEditor.getScheme(newTable));
        while (matcher.find()) {
            split.add(matcher.group());
        }
        Assert.assertTrue(split.contains(oldName));
        Assert.assertFalse(split.contains(columnName));
    }
}