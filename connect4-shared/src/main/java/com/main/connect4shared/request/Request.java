package com.main.connect4shared.request;

import com.main.connect4shared.domain.generic.GenericEntity;
import com.main.connect4shared.enums.RequestOperation;

import java.io.Serializable;

public class Request implements Serializable {
    private RequestOperation operation;

    private GenericEntity data;

    public Request() {
    }

    public Request(RequestOperation operation, GenericEntity data) {
        this.operation = operation;
        this.data = data;
    }

    public RequestOperation getOperation() {
        return operation;
    }

    public void setOperation(RequestOperation operation) {
        this.operation = operation;
    }

    public GenericEntity getData() {
        return data;
    }

    public void setData(GenericEntity data) {
        this.data = data;
    }
}