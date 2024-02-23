package com.main.connect4server.database;

import com.main.connect4server.models.GenericEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DatabaseBroker implements DatabaseRepository {
    @Override
    public List<GenericEntity> findAll(GenericEntity entity) throws Exception {
        List<GenericEntity> list = null;

        Connection connection = DatabaseConnection.getInstance().getConnection();

        // String query = "SELECT * FROM " + entity.getTableName() + " ORDER BY " + entity.getOrderCondition();

        String query = "SELECT * FROM ? ORDER BY ?";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, entity.getTableName());
        statement.setString(2, entity.getOrderCondition());

        ResultSet resultSet = statement.executeQuery(query);

        list = entity.getList(resultSet);

        resultSet.close();

        statement.close();

        return list;
    }

    @Override
    public GenericEntity find(GenericEntity entity) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();

        // String query = "SELECT * FROM " + entity.getTableName() + " WHERE " + entity.getWhereCondition();

        String query = "SELECT * FROM ? WHERE ?";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, entity.getTableName());
        statement.setString(2, entity.getWhereCondition());

        ResultSet resultSet = statement.executeQuery(query);

        boolean signal = resultSet.next();

        if (signal) {
            return entity.getNewRecord(resultSet);
        }

        return null;
    }

    @Override
    public Long insert(GenericEntity entity) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();

        /*String query = "INSERT INTO " + entity.getTableName() + " (" + entity.getAtrNames() + ") VALUES " +
                "(" + entity.getAtrValues() + ")";*/

        String query = "INSERT INTO ? (?) VALUES (?)";

        PreparedStatement statement = connection.prepareStatement(query,
                PreparedStatement.RETURN_GENERATED_KEYS);

        statement.setString(1, entity.getTableName());
        statement.setString(2, entity.getAtrNames());
        statement.setString(3, entity.getAtrValues());

        statement.executeUpdate();

        ResultSet resultSet = statement.getGeneratedKeys();

        if (resultSet != null && resultSet.next()) {
            return resultSet.getLong(1);
        } else {
            return 0L;
        }
    }

    @Override
    public void update(GenericEntity entity) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();

        /*String query = "UPDATE " + entity.getTableName() + " SET " + entity.getUpdateQuery()
                + " WHERE id=" + entity.getIdentificator();*/

        String query = "UPDATE ? SET ? WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, entity.getTableName());
        statement.setString(2, entity.getUpdateQuery());
        statement.setString(3, entity.getIdentificator());

        statement.executeUpdate();
    }
}
