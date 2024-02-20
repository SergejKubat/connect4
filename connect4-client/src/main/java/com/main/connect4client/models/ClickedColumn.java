package com.main.connect4client.models;

public class ClickedColumn implements GenericEntity {
    private int column;

    public ClickedColumn(int column) {
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "ChooseColumn{" + "column=" + column + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + this.column;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final ClickedColumn other = (ClickedColumn) obj;

        return this.column == other.column;
    }

    @Override
    public String getTableName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getAtrValues() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getAtrNames() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String setAtrValues() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getWhereCondition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getUpdateQuery() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getIdentificator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getOrderCondition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}