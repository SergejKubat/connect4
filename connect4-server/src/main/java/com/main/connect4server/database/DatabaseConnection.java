package com.main.connect4server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DATABASE_URL = "jdbc:mysql://localhost/connect4";

    private static final String DATABASE_USER = "root";

    private static final String DATABASE_PASSWORD = "";

    private static DatabaseConnection instance;

    private Connection connection;

    private DatabaseConnection() {
    }

    public synchronized static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }

        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            connection.setAutoCommit(false);
        }

        return connection;
    }

    public void closeConnection() throws SQLException {
        connection.close();
        instance = null;
    }
}
