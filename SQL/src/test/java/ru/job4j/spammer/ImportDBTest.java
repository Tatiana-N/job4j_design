package ru.job4j.spammer;

import org.junit.Test;
import ru.job4j.tracker.ConnectionRollback;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.SQLTracker;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ImportDBTest {
    Properties config;
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
    @Test
    public void createItem(){
           try(SQLTracker tracker = new SQLTracker((ConnectionRollback.create(this.init())))){
              tracker.setTable_name(config.getProperty("table_name"));
              tracker.findAll();
            tracker.add(new Item("name"));
            assertThat(tracker.findByName("name").size(), is(1));
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }
}