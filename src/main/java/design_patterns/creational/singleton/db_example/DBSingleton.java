package design_patterns.creational.singleton.db_example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBSingleton {

    private Connection connection = null;
    private static class LazyHolder {
        private static final DBSingleton INSTANCE = new DBSingleton();
    }

    private DBSingleton() {
        try {
            String url = "jdbc:h2:mem:test";
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static DBSingleton getInstance() {
        return LazyHolder.INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }
}
