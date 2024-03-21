package com.main.connect4shared.domain;

import com.main.connect4shared.domain.generic.GenericEntity;

import java.sql.ResultSet;
import java.util.List;

/**
 * <p>Java class for ClickedColumn complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ClickedColumn">
 *   &lt;complexContent>
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType">
 *       &lt;sequence>
 *         &lt;element name="column" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
public class ClickedColumn implements GenericEntity {
    private int column;

    public ClickedColumn(int column) {
        this.column = column;
    }

    /**
     * Gets the value of the column property.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Sets the value of the column property.
     */
    public void setColumn(int column) {
        this.column = column;
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
    public List<GenericEntity> getList(ResultSet resultSet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getOrderCondition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public GenericEntity getNewRecord(ResultSet resultSet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}