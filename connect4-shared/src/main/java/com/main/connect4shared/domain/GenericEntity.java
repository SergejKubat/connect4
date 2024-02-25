package com.main.connect4shared.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

public interface GenericEntity extends Serializable {
    String getTableName();

    String getAtrValues();

    String getAtrNames();

    String setAtrValues();

    String getWhereCondition();

    String getUpdateQuery();

    String getIdentificator();

    List<GenericEntity> getList(ResultSet resultSet) throws Exception;

    String getOrderCondition();

    GenericEntity getNewRecord(ResultSet rs);
}