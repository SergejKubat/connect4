package com.main.connect4client.models;

import java.io.Serializable;

public interface GenericEntity extends Serializable {
    String getTableName();

    String getAtrValues();

    String getAtrNames();

    String setAtrValues();

    String getWhereCondition();

    String getUpdateQuery();

    String getIdentificator();

    //List<GenericEntity> getList(ResultSet resultSet) throws Exception;

    String getOrderCondition();

    //GenericEntity getNewRecord(ResultSet rs);
}