package com.main.connect4server.database;

import com.main.connect4server.repositories.CustomRepository;

public interface DatabaseRepository extends CustomRepository {
    default void connect() throws Exception {
        DatabaseConnection.getInstance().getConnection();
    }

    default void disconnect() throws Exception {
        DatabaseConnection.getInstance().getConnection().close();
    }

    default void commit() throws Exception {
        DatabaseConnection.getInstance().getConnection().commit();
    }

    default void rollback() throws Exception {
        DatabaseConnection.getInstance().getConnection().rollback();
    }
}
