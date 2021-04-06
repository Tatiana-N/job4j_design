package tracker;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.tracker.ConnectionRollback;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.SQLTracker;
import ru.job4j.tracker.Store;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TestTrackerWithRollBack {

    public Connection init() {
        try (InputStream in = SQLTracker.class.getClassLoader().getResourceAsStream("tracker.properties")) {
            Properties config = new Properties();
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

    public void fillTable(Store sqlTracker, List<Item> list) {
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
        List<Item> list = new ArrayList<>();
        try (Store sqlTracker = new SQLTracker((ConnectionRollback.create(this.init())), "items")) {
            fillTable(sqlTracker, list);
            Item name = new Item("name");
            sqlTracker.add(name);
            list.add(name);
            Assert.assertEquals(list, sqlTracker.findAll());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void replace() {
        List<Item> list = new ArrayList<>();
        String nameForReplace = "new name";
        try (Store sqlTracker = new SQLTracker((ConnectionRollback.create(this.init())), "items")) {
            fillTable(sqlTracker, list);
            Item first = list.get(0);
            sqlTracker.replace(String.valueOf(first.getId()), new Item(nameForReplace));
            Assert.assertFalse(sqlTracker.findAll().contains(first));
            list.remove(first);
            first.setName(nameForReplace);
            list.add(first);
            Assert.assertEquals(list, sqlTracker.findAll());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void delete() {
        List<Item> list = new ArrayList<>();
        try (Store sqlTracker = new SQLTracker((ConnectionRollback.create(this.init())), "items")) {
            fillTable(sqlTracker, list);
            Item first = list.get(0);
            Assert.assertTrue(sqlTracker.delete(String.valueOf(first.getId())));
            Assert.assertFalse(sqlTracker.findAll().contains(first));
            list.remove(first);
            Assert.assertEquals(list, sqlTracker.findAll());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void failDelete() {
        List<Item> list = new ArrayList<>();
        try (Store sqlTracker = new SQLTracker((ConnectionRollback.create(this.init())), "items")) {
            fillTable(sqlTracker, list);
            Assert.assertFalse(sqlTracker.delete("-1"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void findAll() {
        List<Item> list = new ArrayList<>();
        try (Store sqlTracker = new SQLTracker((ConnectionRollback.create(this.init())), "items")) {
            fillTable(sqlTracker, list);
            List<Item> all = sqlTracker.findAll();
            Assert.assertEquals(all.size(), 3);
            Assert.assertEquals(list, all);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void findById() {
        List<Item> list = new ArrayList<>();
        try (Store sqlTracker = new SQLTracker((ConnectionRollback.create(this.init())), "items")) {
            fillTable(sqlTracker, list);
            Item first = list.get(0);
            Assert.assertEquals(sqlTracker.findById(String.valueOf(first.getId())), first);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void findByName() {
        List<Item> list = new ArrayList<>();
        try (Store sqlTracker = new SQLTracker((ConnectionRollback.create(this.init())), "items")) {
            fillTable(sqlTracker, list);
            Item first = list.get(0);
            List<Item> byName = sqlTracker.findByName(first.getName());
            Assert.assertEquals(byName.size(), 1);
            Assert.assertEquals(byName.get(0), first);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}