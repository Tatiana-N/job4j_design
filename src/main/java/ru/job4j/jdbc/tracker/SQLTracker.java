package ru.job4j.jdbc.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class SQLTracker implements Store {
    private Connection cn;

    public void init() {
        try (InputStream in = SQLTracker.class.getClassLoader().getResourceAsStream("tracker.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        int id = doExecute("insert into store (name) values (?)", item.getName());
        item.setId(id);
        return item ;
    }

    public int doExecute(String sql, String name) {
        try (PreparedStatement statement = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                      statement.setString(2, name);
            statement.execute();
            try (ResultSet genKey = statement.getGeneratedKeys()) {
                if (genKey.next()) {
                    return genKey.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int doExecuteUpdate(String sql) {
       // PreparedStatement statement = cn.prepareStatement(sql);
        return 0;
    }

    public ResultSet doExecuteQuery(String sql) {
        return null;
    }

    @Override
    public boolean replace(String id, Item item) {
        return false;
    }

    @Override
    public boolean delete(String id) {

        return false;
    }

    @Override
    public List<Item> findAll() {
        return null;
    }

    @Override
    public List<Item> findByName(String key) {
        return null;
    }

    @Override
    public Item findById(String id) {
        return null;
    }
}