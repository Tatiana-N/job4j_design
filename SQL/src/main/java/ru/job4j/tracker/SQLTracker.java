package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SQLTracker implements Store {
    private Connection cn;
    private String table_name;

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
            table_name = config.getProperty("table_name");
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }


    }


    public boolean doExecute(String sql) {
        int i = 0;
        try (Statement statement = cn.createStatement()) {
            i = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i > 0;
    }

    public int doExecuteUpdate(String sql, String name) {
        try (PreparedStatement statement = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
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

    public ResultSet doExecuteQuery(String sql) {
        try {
            PreparedStatement statement = cn.prepareStatement(sql);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Item> fromResultSetToItem(ResultSet resultSet) {
        List<Item> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int id1 = resultSet.getInt("id");
                Item item = new Item(name);
                item.setId(id1);
                list.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        int id = doExecuteUpdate(String.format("insert into %s (name) values (?);", table_name), item.getName());
        item.setId(id);
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        String sql = String.format("update %s  set name = (?) where id = %s;", table_name, id);
        int i = doExecuteUpdate(sql, item.getName());
        return i != -1;
    }

    @Override
    public boolean delete(String id) {
        String sql = String.format("delete FROM %s WHERE id = %s;", table_name, id);
        return doExecute(sql);
    }

    @Override
    public List<Item> findAll() {
        String sql = String.format("select * from %s;", table_name);
        return fromResultSetToItem(doExecuteQuery(sql));
    }

    @Override
    public List<Item> findByName(String key) {
        String sql = String.format("select * from %s where name = '%s';", table_name, key);
        return fromResultSetToItem(doExecuteQuery(sql));
    }

    @Override
    public Item findById(String id) {
        String sql = String.format("select * from %s where id = %s;", table_name, id);
        return fromResultSetToItem(doExecuteQuery(sql)).get(0);
    }
}