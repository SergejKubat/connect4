package com.main.connect4server.repositories;

import com.main.connect4shared.domain.GenericEntity;

import java.sql.SQLException;
import java.util.List;

public interface CustomRepository {
    List<GenericEntity> findAll(GenericEntity entity) throws Exception;

    GenericEntity find(GenericEntity entity) throws SQLException;

    Long insert(GenericEntity entity) throws SQLException;

    void update(GenericEntity entity) throws SQLException;
}
