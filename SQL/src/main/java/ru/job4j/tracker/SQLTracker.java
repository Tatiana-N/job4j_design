package ru.job4j.tracker;

import lombok.Getter;
import lombok.Setter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Getter
@Setter
public class SQLTracker implements Store {
    private Connection connection;
    private String tableName;
    private Properties config;

    public SQLTracker(Connection connection) {
        this.connection = connection;
    }

    private boolean doExecute(String sql) {
        int i = 0;
        try (Statement statement = connection.createStatement()) {
            i = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i > 0;
    }

    private int doExecuteUpdate(String sql, String name) {
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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

    private List<Item> fromResultSetToItem(String sql) {
        List<Item> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
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
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public Item add(Item item) {
        int id = doExecuteUpdate(String.format("insert into %s (name) values (?);", tableName), item.getName());
        item.setId(id);
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        String sql = String.format("update %s  set name = '%s' where id = %s;", tableName, item.getName(), id);
        return doExecute(sql);
    }

    @Override
    public boolean delete(String id) {
        String sql = String.format("delete FROM %s WHERE id = %s;", tableName, id);
        return doExecute(sql);
    }

    @Override
    public List<Item> findAll() {
        String sql = String.format("select * from %s;", tableName);
        return fromResultSetToItem(sql);
    }

    @Override
    public List<Item> findByName(String key) {
        String sql = String.format("select * from %s where name = '%s';", tableName, key);
        return fromResultSetToItem(sql);
    }

    @Override
    public Item findById(String id) {
        String sql = String.format("select * from %s where id = %s;", tableName, id);
        List<Item> items = fromResultSetToItem(sql);
        return items.isEmpty() ? null : items.get(0);
    }
}