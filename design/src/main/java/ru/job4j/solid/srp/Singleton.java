package ru.job4j.solid.srp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Singleton {
	private static Connection connection;
	
	private Singleton(Properties properties) {
		String url = properties.getProperty("url");
		String login = properties.getProperty("login");
		String password = properties.getProperty("password");
		try {
			//кдасс занимается созданием соединения
			connection = DriverManager.getConnection(url, login, password);
		} catch (SQLException throwable) {
			throwable.printStackTrace();
		}
	}
	
	//класс также занимается тем, что следит, чтобы соединение было одно
	public static Connection getConnection(Properties properties) {
		if (connection == null) {
			new Singleton(properties);
		}
		return connection;
	}
	
}
