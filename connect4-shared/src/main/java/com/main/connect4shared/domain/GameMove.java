package com.main.connect4shared.domain;

import com.main.connect4shared.domain.generic.GenericEntity;

import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;

/**
 * <p>Java class for GameMove complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="GameMove">
 *   &lt;complexContent>
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType">
 *       &lt;sequence>
 *         &lt;element name="row" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="column" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
public class GameMove implements GenericEntity {
    private int row;

    private int column;

    public GameMove(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Gets the value of the row property.
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets the value of the row property.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Gets the value of the column property.
     */
    public int getCol() {
        return column;
    }

    /**
     * Sets the value of the column property.
     */
    public void setCol(int column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameMove gameMove = (GameMove) o;

        return row == gameMove.row && column == gameMove.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
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
    public List<GenericEntity> getList(ResultSet resultSet) {
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