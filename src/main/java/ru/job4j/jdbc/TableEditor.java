package ru.job4j.jdbc;

import java.sql.*;
import java.util.Properties;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void doExecute(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        String sql = String.format(
                "create table if not exists %s(%s);", tableName,
                "id serial primary key"
        );
        doExecute(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format("drop table if exists %s", tableName);
        doExecute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format("ALTER TABLE if exists %s ADD COLUMN IF NOT EXISTS %s %s",
                tableName,
                columnName,
                type);
        doExecute(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format("ALTER TABLE if exists %s drop COLUMN IF EXISTS  %s",
                tableName,
                columnName);
        doExecute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format("ALTER TABLE if exists %s rename COLUMN %s to %s",
                tableName,
                columnName,
                newColumnName);
        doExecute(sql);
    }

    public String getScheme(String tableName) {
        StringBuilder scheme = new StringBuilder();
        DatabaseMetaData metaData = null;
        try {
            metaData = connection.getMetaData();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scheme.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}