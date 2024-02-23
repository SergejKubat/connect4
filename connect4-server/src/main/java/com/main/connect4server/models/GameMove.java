package com.main.connect4server.models;

import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;

public class GameMove implements GenericEntity {
    private int row;

    private int col;

    public GameMove(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameMove gameMove = (GameMove) o;

        return row == gameMove.row && col == gameMove.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public String getAtrValues() {
        return null;
    }

    @Override
    public String getAtrNames() {
        return null;
    }

    @Override
    public String setAtrValues() {
        return null;
    }

    @Override
    public String getWhereCondition() {
        return null;
    }

    @Override
    public String getUpdateQuery() {
        return null;
    }

    @Override
    public String getIdentificator() {
        return null;
    }

    @Override
    public List<GenericEntity> getList(ResultSet resultSet) throws Exception {
        return null;
    }

    @Override
    public String getOrderCondition() {
        return null;
    }

    @Override
    public GenericEntity getNewRecord(ResultSet rs) {
        return null;
    }
}