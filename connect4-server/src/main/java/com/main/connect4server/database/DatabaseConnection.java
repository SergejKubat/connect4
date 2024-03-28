package com.main.connect4server.database;

import com.main.connect4server.settings.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;

    private Connection connection;

    private DatabaseConnection() {
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }

        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(
                    Constants.DATABASE_URL,
                    Constants.DATABASE_USER,
                    Constants.DATABASE_PASSWORD);

            connection.setAutoCommit(false);
        }

        return connection;
    }

    public void closeConnection() throws SQLException {
        connection.close();
        instance = null;
    }
}
